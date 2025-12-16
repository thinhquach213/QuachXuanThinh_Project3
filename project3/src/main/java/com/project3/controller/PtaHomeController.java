package com.project3.controller;

import com.project3.service.product.PtaProductService;
import com.project3.service.product.PtaCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PtaHomeController {

    private final PtaCategoryService categoryService;
    private final PtaProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAll());
        return "PtaHome";
    }

}