package k23cnt3.qxtWebbansach.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // bạn có thể add các thống kê: tổng user, tổng đơn hàng, tổng sách
        return "admin/dashboard";
    }
}
