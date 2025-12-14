package k23cnt3.QxtMerryChristmas.controller.admin;

import k23cnt3.QxtMerryChristmas.entity.product.QxtCategory;
import k23cnt3.QxtMerryChristmas.entity.product.QxtProduct;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUserRole;
import k23cnt3.QxtMerryChristmas.repository.product.QxtCategoryRepository;
import k23cnt3.QxtMerryChristmas.repository.product.QxtProductRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class QxtAdminProductController {

    private final QxtProductRepository productRepository;
    private final QxtCategoryRepository categoryRepository;

    private final String uploadDir = "uploads/product-images";

    private boolean isAdmin(HttpSession session) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        return currentUser != null && currentUser.getRole() == QxtUserRole.ADMIN;
    }

    @GetMapping
    public String listProducts(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        List<QxtProduct> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "admin/QxtProductList";
    }

    @GetMapping("/create")
    public String showCreateForm(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        QxtProduct product = new QxtProduct();
        List<QxtCategory> categories = categoryRepository.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("formMode", "CREATE");

        return "admin/QxtProductForm";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") QxtProduct product,
                                @RequestParam("imageFile") MultipartFile imageFile,
                                HttpSession session,
                                Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = saveImage(imageFile);
                product.setImage(fileName);
            } catch (IOException e) {
                model.addAttribute("error", "Upload ảnh thất bại, vui lòng thử lại!");
                model.addAttribute("categories", categoryRepository.findAll());
                model.addAttribute("formMode", "CREATE");
                return "admin/QxtProductForm";
            }
        }

        productRepository.save(product);
        return "redirect:/admin/products?created";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               HttpSession session,
                               Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        QxtProduct product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        List<QxtCategory> categories = categoryRepository.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("formMode", "EDIT");

        return "admin/QxtProductForm";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") QxtProduct formProduct,
                                @RequestParam("imageFile") MultipartFile imageFile,
                                HttpSession session,
                                Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        QxtProduct product = productRepository.findById(formProduct.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.setName(formProduct.getName());
        product.setPrice(formProduct.getPrice());
        product.setDescription(formProduct.getDescription());
        product.setCategory(formProduct.getCategory());
        product.setTag(formProduct.getTag());

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = saveImage(imageFile);
                product.setImage(fileName);
            } catch (IOException e) {
                model.addAttribute("error", "Upload ảnh thất bại, vui lòng thử lại!");
                model.addAttribute("categories", categoryRepository.findAll());
                model.addAttribute("formMode", "EDIT");
                model.addAttribute("product", formProduct);
                return "admin/QxtProductForm";
            }
        }

        productRepository.save(product);
        return "redirect:/admin/products?updated";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,
                                HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        productRepository.deleteById(id);
        return "redirect:/admin/products?deleted";
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        String originalName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        String ext = "";

        int dotIndex = originalName.lastIndexOf('.');
        if (dotIndex >= 0) {
            ext = originalName.substring(dotIndex);
        }

        String fileName = System.currentTimeMillis() + ext;

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        imageFile.transferTo(filePath.toFile());

        return fileName;
    }
}
