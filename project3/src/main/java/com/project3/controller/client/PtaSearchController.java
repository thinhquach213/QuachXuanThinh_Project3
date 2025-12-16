package com.project3.controller.client;

import com.project3.entity.product.PtaProduct;
import com.project3.service.product.PtaProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PtaSearchController {

    private final PtaProductService productService;

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {

        List<PtaProduct> results = productService.searchByKeyword(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("products", results);

        return "product/PtaSearchResult";   // View kết quả
    }
}