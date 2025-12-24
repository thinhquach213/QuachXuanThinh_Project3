package k23cnt3.QxtMerryChristmas.controller.client;

import k23cnt3.QxtMerryChristmas.entity.product.QxtProduct;
import k23cnt3.QxtMerryChristmas.service.product.QxtProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller xử lý chức năng TÌM KIẾM sản phẩm phía CLIENT
 * Người dùng nhập từ khóa → hệ thống trả về danh sách sản phẩm phù hợp
 */
@Controller
@RequiredArgsConstructor
public class QxtSearchController {

    // Service xử lý logic tìm kiếm sản phẩm
    private final QxtProductService productService;

    /* =================================================
     *  TÌM KIẾM SẢN PHẨM
     *  URL: GET /search?keyword=abc
     * ================================================= */
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword,
                                 Model model) {

        // 1. Gọi service để tìm sản phẩm theo từ khóa
        // (có thể tìm theo tên, mô tả, tag...)
        List<QxtProduct> results = productService.searchByKeyword(keyword);

        // 2. Truyền lại từ khóa để hiển thị trên giao diện
        model.addAttribute("keyword", keyword);

        // 3. Truyền danh sách sản phẩm tìm được sang view
        model.addAttribute("products", results);

        // 4. Trả về trang hiển thị kết quả tìm kiếm
        return "product/QxtSearchResult";
    }
}
