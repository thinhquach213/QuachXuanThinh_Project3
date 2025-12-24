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
//“Controller này chịu trách nhiệm hiển thị trang chủ cho người dùng.”
    private final QxtCategoryService categoryService;
    private final QxtProductService productService;
//dùng Service thay vì Repository Em tách business logic sang
// service để controller chỉ lo điều hướng và truyền dữ liệu cho view
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAll());
        return "QxtHome";
    }

}