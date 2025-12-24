package k23cnt3.QxtMerryChristmas.controller;

import k23cnt3.QxtMerryChristmas.entity.product.QxtCategory;
import k23cnt3.QxtMerryChristmas.service.product.QxtCategoryService;
import k23cnt3.QxtMerryChristmas.service.product.QxtProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class QxtCategoryClientController {
//Controller này xử lý chức năng xem sản phẩm theo danh mục.”
    private final QxtCategoryService categoryService;
    private final QxtProductService productService;

    @GetMapping("/category/{id}")
    public String categoryPage(@PathVariable Long id, Model model) {

        // Lấy category hiện tại
        QxtCategory cat = categoryService.getById(id);

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

        return "QxtCategory";
    }
}