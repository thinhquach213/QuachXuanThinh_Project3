package k23cnt3.QxtMerryChristmas.controller;

import k23cnt3.QxtMerryChristmas.service.product.QxtProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class QxtProductClientController {

    private final QxtProductService productService;

    @GetMapping("/product/{id}")
    public String getDetail(@PathVariable Long id, Model model) {

        var product = productService.getById(id);

        if (product == null) {
            return "redirect:/";
        }

        model.addAttribute("product", product);
        return "product/QxtProductDetail";
    }

    @GetMapping("/tag/{tag}")
    public String getByTag(@PathVariable String tag, Model model) {

        // Map tag -> tên hiển thị đẹp
        Map<String, String> tagDisplay = Map.of(
                "tangbanhai", "Tặng bạn gái",
                "bigsize", "Gấu size lớn",
                "intenn", "Gấu bông in tên"
        );

        String displayName = tagDisplay.getOrDefault(tag, tag);

        model.addAttribute("displayName", displayName);
        model.addAttribute("products", productService.findByTag(tag));

        return "product/QxtTagProducts";
    }
}
