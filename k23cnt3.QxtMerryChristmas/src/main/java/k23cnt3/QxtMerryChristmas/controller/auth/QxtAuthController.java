package k23cnt3.QxtMerryChristmas.controller.auth;

import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUserRole;
import k23cnt3.QxtMerryChristmas.service.user.QxtUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//Controller này chịu trách nhiệm xác thực người dùng và quản lý session đăng nhập.
@Controller
@RequiredArgsConstructor
public class QxtAuthController {

    private final QxtUserService userService;
    //“Em tách logic xử lý đăng nhập, đăng ký sang service để controller chỉ lo điều hướng.”
    // ========== LOGIN ==========
    // Hiển thị form login (GET /login)
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "redirect", required = false) String redirect,
                                Model model) {
        model.addAttribute("redirect", redirect);
        return "auth/QxtLogin";
    }

    // Xử lý submit form login (POST /login)
    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          @RequestParam(value = "redirect", required = false) String redirect,
                          HttpSession session,
                          Model model) {

        QxtUser user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Sai email hoặc mật khẩu");

            // giữ lại redirect khi login bị sai
            model.addAttribute("redirect", redirect);

            return "auth/QxtLogin";
        }

        // Save session
        session.setAttribute("currentUser", user);
        //“Em lưu thông tin người dùng vào session để quản lý đăng nhập.”
        // Nếu có redirect → quay lại trang ban đầu
        if (redirect != null && !redirect.isEmpty()) {
            return "redirect:" + redirect;
        }

        // Điều hướng theo role (nếu không có redirect)
        if (user.getRole() == QxtUserRole.ADMIN) {
            return "redirect:/admin/dashboard";
            //“Sau khi login, hệ thống tự động điều hướng theo vai trò người dùng.”
        } else if (user.getRole() == QxtUserRole.SHIPPER) {
            return "redirect:/ship/orders";
        } else {
            return "redirect:/";
        }
    }

    // ========== REGISTER (CHO KHÁCH) ==========

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "auth/QxtRegister";   // file auth/QxtRegister.html
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String fullName,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String phone,
                             Model model) {

        QxtUser user = userService.registerCustomer(fullName, email, password, phone);
        if (user == null) {
            model.addAttribute("error", "Email đã tồn tại");
            return "auth/QxtRegister";
        }

        model.addAttribute("msg", "Đăng ký thành công, mời bạn đăng nhập");
        return "auth/QxtLogin";
    }

    // ========== LOGOUT ==========

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        //Em hủy session để đảm bảo bảo mật khi logout
        return "redirect:/";
    }
}