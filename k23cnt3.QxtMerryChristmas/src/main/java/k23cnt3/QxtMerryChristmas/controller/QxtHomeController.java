package k23cnt3.QxtMerryChristmas.controller;

import k23cnt3.QxtMerryChristmas.service.product.QxtCategoryService;
import k23cnt3.QxtMerryChristmas.service.product.QxtProductService;
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