package NhtK23cnt2.prj3.controller;

import NhtK23cnt2.prj3.entity.product.NhtProduct;
import NhtK23cnt2.prj3.service.product.NhtProductService;
import NhtK23cnt2.prj3.service.product.NhtCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NhtHomeController {

    private final NhtCategoryService categoryService;
    private final NhtProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAll());
        return "NhtHome";
    }

    @GetMapping("/tag/{tagName}")
    public String productByTag(@PathVariable String tagName, Model model) {

        List<NhtProduct> products = productService.findByTag(tagName);

        model.addAttribute("products", products);
        model.addAttribute("title", "Sản phẩm thuộc nhóm: " + tagName);

        return "NhtTagProducts";
    }
}
