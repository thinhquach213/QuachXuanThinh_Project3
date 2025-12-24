package k23cnt3.QxtMerryChristmas.controller;

import k23cnt3.QxtMerryChristmas.entity.product.QxtProductComment;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.repository.product.QxtProductCommentRepository;
import k23cnt3.QxtMerryChristmas.service.product.QxtProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller xử lý phía CLIENT cho:
 * - Xem chi tiết sản phẩm
 * - Xem và gửi bình luận (comment + đánh giá sao)
 */
@Controller
@RequiredArgsConstructor
public class QxtProductClientController {

    // Service lấy thông tin sản phẩm
    private final QxtProductService productService;

    // Repository thao tác với bảng comment sản phẩm
    private final QxtProductCommentRepository commentRepository;

    /* =================================================
     *  CHI TIẾT SẢN PHẨM + DANH SÁCH COMMENT
     *  URL: GET /product/{id}
     * ================================================= */
    @GetMapping("/product/{id}")
    public String getDetail(@PathVariable Long id,
                            Model model,
                            HttpSession session) {

        // 1. Lấy sản phẩm theo id
        var product = productService.getById(id);

        // Nếu sản phẩm không tồn tại → quay về trang chủ
        if (product == null) {
            return "redirect:/";
        }

        // 2. Lấy user đang đăng nhập từ session (có thể null)
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");

        // 3. Lấy danh sách comment của sản phẩm (mới nhất lên trước)
        List<QxtProductComment> comments =
                commentRepository.findByProductIdOrderByCreatedAtDesc(id);

        // 4. Đưa dữ liệu sang view
        model.addAttribute("product", product);         // thông tin sản phẩm
        model.addAttribute("comments", comments);       // danh sách bình luận
        model.addAttribute("currentUser", currentUser); // user đang login (để kiểm tra quyền comment)

        // 5. Trả về trang chi tiết sản phẩm
        return "product/QxtProductDetail";
    }

    /* =================================================
     *  GỬI BÌNH LUẬN + ĐÁNH GIÁ SAO
     *  URL: POST /product/{id}/comment
     * ================================================= */
    @PostMapping("/product/{id}/comment")
    public String postComment(@PathVariable Long id,
                              @RequestParam("content") String content,
                              @RequestParam("star") int star,
                              HttpSession session) {

        // 1. Kiểm tra đăng nhập
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (currentUser == null) {
            // Chưa đăng nhập → bắt buộc login
            return "redirect:/login";
        }

        // 2. Kiểm tra sản phẩm tồn tại
        var product = productService.getById(id);
        if (product == null) {
            return "redirect:/";
        }

        // 3. Tạo đối tượng comment mới
        QxtProductComment comment = QxtProductComment.builder()
                .content(content)                 // nội dung bình luận
                .star(star)                       // số sao đánh giá
                .product(product)                 // sản phẩm được bình luận
                .user(currentUser)                // user bình luận
                .createdAt(LocalDateTime.now())   // thời gian tạo
                .build();

        // 4. Lưu comment vào database
        commentRepository.save(comment);

        // 5. Quay lại trang chi tiết sản phẩm
        return "redirect:/product/" + id;
    }
}
//“Controller này xử lý hiển thị chi tiết sản phẩm và chức năng bình luận.
//Người dùng đăng nhập mới được comment, mỗi comment gắn với user, sản phẩm và thời gian tạo.”