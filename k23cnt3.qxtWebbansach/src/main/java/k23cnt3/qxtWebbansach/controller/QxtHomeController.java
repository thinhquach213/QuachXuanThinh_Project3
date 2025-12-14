package K23cnt3.QxtWebBanSach.controller;

import K23cnt3.QxtWebBanSach.service.product.QxtProductService;
import K23cnt3.QxtWebBanSach.service.product.QxtCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class QxtHomeController {

    private final QxtCategoryService categoryService;
    private final QxtProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAll());
        return "QxtHome";
    }

}
