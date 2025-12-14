package NhtK23cnt2.prj3.controller;

import NhtK23cnt2.prj3.service.product.NhtProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class NhtProductClientController {

    private final NhtProductService productService;

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "NhtProductDetail"; // resources/templates/NhtProductDetail.html
    }
}
