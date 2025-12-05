package k23cnt3.qxtWebbansach.controller.auth;

import k23cnt3.qxtWebbansach.model.User;
import k23cnt3.qxtWebbansach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.login(email, password);
        if (user != null) {
            // lưu session, redirect dựa vào role
            if(user.getRole().name().equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            }
            return "redirect:/user/home";
        }
        model.addAttribute("error", "Email hoặc mật khẩu không đúng");
        return "auth/login";
    }
}
