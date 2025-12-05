package k23cnt3.qxtWebbansach.controller.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import k23cnt3.qxtWebbansach.entity.Product;
import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.repository.ProductRepository;

import java.util.List;

@Controller
public class HomeController {

    private final ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal User user) {
        List<Product> featuredProducts = productRepository.findByFeaturedTrue();

        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("user", user);

        return "user/home";
    }
}
