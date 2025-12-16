package com.project3.controller.auth;

import com.project3.entity.user.PtaUser;
import com.project3.entity.user.PtaUserRole;
import com.project3.service.user.PtaUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PtaAuthController {

    private final PtaUserService userService;

    // ========== LOGIN ==========

    // Hiển thị form login (GET /login)
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "redirect", required = false) String redirect,
                                Model model) {
        model.addAttribute("redirect", redirect);
        return "auth/PtaLogin";
    }

    // Xử lý submit form login (POST /login)
    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          @RequestParam(value = "redirect", required = false) String redirect,
                          HttpSession session,
                          Model model) {

        PtaUser user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Sai email hoặc mật khẩu");

            // giữ lại redirect khi login bị sai
            model.addAttribute("redirect", redirect);

            return "auth/PtaLogin";
        }

        // Save session
        session.setAttribute("currentUser", user);

        // Nếu có redirect → quay lại trang ban đầu
        if (redirect != null && !redirect.isEmpty()) {
            return "redirect:" + redirect;
        }

        // Điều hướng theo role (nếu không có redirect)
        if (user.getRole() == PtaUserRole.ADMIN) {
            return "redirect:/admin/dashboard";
        } else if (user.getRole() == PtaUserRole.SHIPPER) {
            return "redirect:/ship/orders";
        } else {
            return "redirect:/";
        }
    }

    // ========== REGISTER (CHO KHÁCH) ==========

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "auth/PtaRegister";   // file auth/PtaRegister.html
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String fullName,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String phone,
                             Model model) {

        PtaUser user = userService.registerCustomer(fullName, email, password, phone);
        if (user == null) {
            model.addAttribute("error", "Email đã tồn tại");
            return "auth/PtaRegister";
        }

        model.addAttribute("msg", "Đăng ký thành công, mời bạn đăng nhập");
        return "auth/PtaLogin";
    }

    // ========== LOGOUT ==========

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}