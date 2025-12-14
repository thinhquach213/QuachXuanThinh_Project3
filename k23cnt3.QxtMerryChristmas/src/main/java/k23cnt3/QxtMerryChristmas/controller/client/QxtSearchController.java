package k23cnt3.QxtMerryChristmas.controller.client;

import k23cnt3.QxtMerryChristmas.entity.product.QxtProduct;
import k23cnt3.QxtMerryChristmas.service.product.QxtProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QxtSearchController {

    private final QxtProductService productService;

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {

        List<QxtProduct> results = productService.searchByKeyword(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("products", results);

        return "product/QxtSearchResult";   // View kết quả
    }
}
