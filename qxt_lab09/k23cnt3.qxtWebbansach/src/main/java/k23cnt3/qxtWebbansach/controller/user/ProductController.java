package k23cnt3.qxtWebbansach.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import k23cnt3.qxtWebbansach.entity.Product;
import k23cnt3.qxtWebbansach.entity.Category;
import k23cnt3.qxtWebbansach.service.ProductService;
import k23cnt3.qxtWebbansach.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listProducts(Model model) {

        model.addAttribute("products", productService.findAll());
        model.addAttribute("featuredProducts", productService.findFeaturedProducts());
        model.addAttribute("promotions", productService.findPromotionProducts());
        model.addAttribute("newProducts", productService.findNewProducts());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("selectedCategory", "Tất cả");

        return "user/products";
    }

    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) return "redirect:/products";

        model.addAttribute("product", product);
        return "user/product-detail";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("products", productService.search(keyword));
        model.addAttribute("featuredProducts", productService.findFeaturedProducts());
        model.addAttribute("promotions", productService.findPromotionProducts());
        model.addAttribute("newProducts", productService.findNewProducts());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedCategory", "Kết quả tìm kiếm");

        return "user/products";
    }

    @GetMapping("/category/{id}")
    public String filterByCategory(@PathVariable Long id, Model model) {

        List<Product> products = (id == 0)
                ? productService.findAll()
                : productService.findByCategory(id);

        Category selectedCategory = categoryService.findById(id);
        String categoryName = (id == 0)
                ? "Tất cả"
                : (selectedCategory != null ? selectedCategory.getName() : "Danh mục không tồn tại");

        model.addAttribute("products", products);
        model.addAttribute("featuredProducts", productService.findFeaturedProducts());
        model.addAttribute("promotions", productService.findPromotionProducts());
        model.addAttribute("newProducts", productService.findNewProducts());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("selectedCategory", categoryName);

        return "user/products";
    }
}
