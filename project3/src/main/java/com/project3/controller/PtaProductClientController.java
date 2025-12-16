package com.project3.controller;

import com.project3.entity.product.PtaProductComment;
import com.project3.entity.user.PtaUser;
import com.project3.repository.product.PtaProductCommentRepository;
import com.project3.service.product.PtaProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PtaProductClientController {

    private final PtaProductService productService;
    private final PtaProductCommentRepository commentRepository;

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
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");

        // danh sách comment
        List<PtaProductComment> comments =
                commentRepository.findByProductIdOrderByCreatedAtDesc(id);

        model.addAttribute("product", product);
        model.addAttribute("comments", comments);
        model.addAttribute("currentUser", currentUser);

        return "product/PtaProductDetail";
    }

    /* ========== GỬI BÌNH LUẬN + SAO ========== */
    @PostMapping("/product/{id}/comment")
    public String postComment(@PathVariable Long id,
                              @RequestParam("content") String content,
                              @RequestParam("star") int star,
                              HttpSession session) {

        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        var product = productService.getById(id);
        if (product == null) {
            return "redirect:/";
        }

        PtaProductComment comment = PtaProductComment.builder()
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