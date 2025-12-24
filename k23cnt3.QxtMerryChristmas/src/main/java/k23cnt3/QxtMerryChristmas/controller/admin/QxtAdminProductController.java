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
//Controller này xử lý toàn bộ chức năng quản lý sản phẩm phía admin.
    private final QxtProductRepository productRepository;
    private final QxtCategoryRepository categoryRepository;

    /** Thư mục lưu ảnh **/
    private final String uploadDir = "E:/Project3/k23cnt3.QxtMerryChristmas/uploads/product-images";


    /** Check user trong session có phải ADMIN không */
    private boolean isAdmin(HttpSession session) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        return currentUser != null && currentUser.getRole() == QxtUserRole.ADMIN;
    }
    //Em kiểm tra quyền admin ngay trong controller bằng session và role enu
    /* ========== DANH SÁCH SẢN PHẨM ========== */

    @GetMapping
    public String listProducts(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        //danh sach san pham
        List<QxtProduct> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "admin/QxtProductList";   // view list sản phẩm
    }

    /* ========== FORM THÊM MỚI ========== */

    @GetMapping("/create")
    public String showCreateForm(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        //Nếu upload lỗi → quay lại form + báo lỗi
        QxtProduct product = new QxtProduct();
        List<QxtCategory> categories = categoryRepository.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("formMode", "CREATE");

        return "admin/QxtProductForm";
    }
                                //Nhận product từ form (@ModelAttribute) Nhận file ảnh (MultipartFile) Nếu upload lỗi → quay lại form + báo lỗi
    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") QxtProduct product,
                                @RequestParam("imageFile") MultipartFile imageFile,
                                HttpSession session,
                                Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        // xử lý upload ảnh
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

    /* ========== FORM SỬA ========== */
       //Load sản phẩm theo ID + category
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
    //Lấy product gốc từ DB Cập nhật từng field
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
        //“Nếu không upload ảnh mới thì giữ nguyên ảnh cũ
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

    /* ========== XOÁ ========== */
    //Admin → xóa theo ID
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,
                                HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        productRepository.deleteById(id);
        return "redirect:/admin/products?deleted";
    }

    /* ========== HÀM LƯU ẢNH ========== */

    private String saveImage(MultipartFile imageFile) throws IOException {
        String originalName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        String ext = "";

        int dotIndex = originalName.lastIndexOf('.');
        if (dotIndex >= 0) {
            ext = originalName.substring(dotIndex);
        }

        String fileName = System.currentTimeMillis() + ext;
        //“Tránh trùng tên file khi upload.”
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        imageFile.transferTo(filePath.toFile());

        return fileName;
    }
}
//“Admin đăng nhập → vào quản lý sản phẩm → thêm hoặc sửa sản phẩm → upload ảnh →
// ảnh được lưu ngoài project, database chỉ lưu tên ảnh → client load ảnh thông qua resource handler.”