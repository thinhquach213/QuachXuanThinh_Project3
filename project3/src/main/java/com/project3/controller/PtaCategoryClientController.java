package com.project3.controller;

import com.project3.entity.product.PtaCategory;
import com.project3.service.product.PtaProductService;
import com.project3.service.product.PtaCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PtaCategoryClientController {

    private final PtaCategoryService categoryService;
    private final PtaProductService productService;

    @GetMapping("/category/{id}")
    public String categoryPage(@PathVariable Long id, Model model) {

        // Lấy category hiện tại
        PtaCategory cat = categoryService.getById(id);
        if (cat == null) {
            // Nếu category không tồn tại → chuyển về trang chủ hoặc trang 404
            return "redirect:/?error=category_not_found";
        }

        // Danh sách category để hiển thị menu
        model.addAttribute("categories", categoryService.getAll());

        // Danh sách sản phẩm thuộc category
        model.addAttribute("products", productService.getByCategory(id));

        // Active styling cho category đang chọn
        model.addAttribute("activeCategoryId", id);

        // Để hiển thị tên category trên tiêu đề trang
        model.addAttribute("currentCategory", cat);

        return "PtaCategory";
    }
}