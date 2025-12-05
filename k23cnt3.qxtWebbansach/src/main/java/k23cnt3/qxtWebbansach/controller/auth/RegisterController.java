package k23cnt3.qxtWebbansach.controller.auth;

import k23cnt3.qxtWebbansach.model.User;
import k23cnt3.qxtWebbansach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            userService.register(user);
            return "redirect:/auth/login";
        } catch(Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
    }
}
