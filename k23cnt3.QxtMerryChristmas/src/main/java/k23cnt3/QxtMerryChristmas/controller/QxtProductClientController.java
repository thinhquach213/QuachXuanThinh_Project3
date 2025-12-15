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

@Controller
@RequiredArgsConstructor
public class QxtProductClientController {

    private final QxtProductService productService;
    private final QxtProductCommentRepository commentRepository;

    /* ========== CHI TIẾT SẢN PHẨM + COMMENT ========== */
    @GetMapping("/product/{id}")
    public String getDetail(@PathVariable Long id,
                            Model model,
                            HttpSession session) {

        var product = productService.getById(id);
        if (product == null) {
            return "redirect:/";
        }

        // user đăng nhập
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");

        // danh sách comment
        List<QxtProductComment> comments =
                commentRepository.findByProductIdOrderByCreatedAtDesc(id);

        model.addAttribute("product", product);
        model.addAttribute("comments", comments);
        model.addAttribute("currentUser", currentUser);

        return "product/QxtProductDetail";
    }

    /* ========== GỬI BÌNH LUẬN + SAO ========== */
    @PostMapping("/product/{id}/comment")
    public String postComment(@PathVariable Long id,
                              @RequestParam("content") String content,
                              @RequestParam("star") int star,
                              HttpSession session) {

        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        var product = productService.getById(id);
        if (product == null) {
            return "redirect:/";
        }

        QxtProductComment comment = QxtProductComment.builder()
                .content(content)
                .star(star)
                .product(product)
                .user(currentUser)
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

        return "redirect:/product/" + id;
    }

}