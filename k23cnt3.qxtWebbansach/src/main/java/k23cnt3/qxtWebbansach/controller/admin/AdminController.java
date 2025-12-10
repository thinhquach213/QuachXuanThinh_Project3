package k23cnt3.qxtWebbansach.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import k23cnt3.qxtWebbansach.repository.OrderRepository;
import k23cnt3.qxtWebbansach.repository.UserRepository;
import k23cnt3.qxtWebbansach.repository.ReviewRepository;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping({"", "/", "/index"})
    public String adminHome(Model model) {

        // ===== DASHBOARD =====
        Double totalRevenue = orderRepository.getTotalRevenue();
        if (totalRevenue == null) totalRevenue = 0.0;

        long userCount = userRepository.count();
        long purchaseCount = orderRepository.count();

        Double averageRating = reviewRepository.getAverageRating();
        if (averageRating == null) averageRating = 0.0;

        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("userCount", userCount);
        model.addAttribute("purchaseCount", purchaseCount);
        model.addAttribute("averageRating", String.format("%.1f/5", averageRating));

        // ===== BIỂU ĐỒ DOANH THU =====
        List<String> months = new ArrayList<>();
        List<Double> revenues = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            months.add(Month.of(i).getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
            Double revenue = orderRepository.getRevenueByMonth(i);
            revenues.add(revenue == null ? 0.0 : revenue);
        }

        model.addAttribute("months", months);
        model.addAttribute("revenues", revenues);

        return "admin/index";
    }
}
