package k23cnt3.qxtWebbansach.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import k23cnt3.qxtWebbansach.entity.Review;
import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.service.ReviewService;
import k23cnt3.qxtWebbansach.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    // ================================
    // 1. Hiển thị danh sách review của 1 sản phẩm
    // ================================
    @GetMapping("/product/{productId}")
    public String getReviewsByProduct(@PathVariable Long productId, Model model) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("productId", productId);
        return "user/reviews/list";  // file: templates/user/reviews/list.html
    }

    // ================================
    // 2. Gửi đánh giá mới
    // ================================
    @PostMapping("/add")
    public String addReview(@RequestParam Long productId,
                            @RequestParam int rating,
                            @RequestParam String comment) {

        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        reviewService.addReview(productId, currentUser, rating, comment);

        return "redirect:/reviews/product/" + productId;
    }
}
