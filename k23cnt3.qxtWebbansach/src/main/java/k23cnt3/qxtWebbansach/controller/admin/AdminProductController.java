package k23cnt3.qxtWebbansach.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import k23cnt3.qxtWebbansach.entity.Product;
import k23cnt3.qxtWebbansach.repository.ProductRepository;
import k23cnt3.qxtWebbansach.repository.CategoryRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public AdminProductController(ProductRepository productRepository,
                                  CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // ===========================
    // LIST
    // ===========================
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/products/list";
    }

    // ===========================
    // ADD FORM
    // ===========================
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("images", getImageList());
        return "admin/products/form";
    }

    // ===========================
    // EDIT FORM
    // ===========================
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            return "redirect:/admin/products";
        }

        model.addAttribute("product", productOpt.get());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("images", getImageList());
        return "admin/products/form";
    }

    // ===========================
    // SAVE
    // ===========================
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    // ===========================
    // DELETE
    // ===========================
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }

    // ===========================
    // LẤY DANH SÁCH ẢNH STATIC
    // ===========================
    private String[] getImageList() {
        File folder = new File("src/main/resources/static/images");
        return folder.exists() ? folder.list() : new String[0];
    }
}
