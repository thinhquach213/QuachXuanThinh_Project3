package k23cnt3.qxtWebbansach.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import k23cnt3.qxtWebbansach.entity.Review;
import k23cnt3.qxtWebbansach.service.ReviewService;

import java.util.List;

@Controller
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    @Autowired
    private ReviewService reviewService;

    // Danh sách review
    @GetMapping
    public String list(Model model) {
        List<Review> reviews = reviewService.findAll();
        model.addAttribute("reviews", reviews);
        return "admin/review/list";
    }

    // Chi tiết review
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        Review review = reviewService.findById(id)
                .orElse(null); // Lấy từ Optional
        if (review == null) {
            return "redirect:/admin/reviews";
        }
        model.addAttribute("review", review);
        return "admin/review/review-detail";
    }

    // Xóa review
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        reviewService.deleteById(id);
        return "redirect:/admin/reviews"; // sửa đường dẫn cho đúng
    }
}
