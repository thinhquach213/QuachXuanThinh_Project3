package K23cnt3.QxtWebBanSach.controller.auth;

import K23cnt3.QxtWebBanSach.entity.user.QxtUser;
import K23cnt3.QxtWebBanSach.entity.user.QxtUserRole;
import K23cnt3.QxtWebBanSach.service.user.QxtUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class QxtAuthController {

    private final QxtUserService userService;

    // ========== LOGIN ==========

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "redirect", required = false) String redirect,
                                Model model) {
        model.addAttribute("redirect", redirect);
        return "auth/QxtLogin";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          @RequestParam(value = "redirect", required = false) String redirect,
                          HttpSession session,
                          Model model) {

        QxtUser user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Sai email hoặc mật khẩu");
            model.addAttribute("redirect", redirect);
            return "auth/QxtLogin";
        }

        session.setAttribute("currentUser", user);

        if (redirect != null && !redirect.isEmpty()) {
            return "redirect:" + redirect;
        }

        if (user.getRole() == QxtUserRole.ADMIN) {
            return "redirect:/admin/dashboard";
        } else if (user.getRole() == QxtUserRole.SHIPPER) {
            return "redirect:/ship/orders";
        } else {
            return "redirect:/";
        }
    }

    // ========== REGISTER ==========

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "auth/QxtRegister";
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
        return "redirect:/";
    }
}
