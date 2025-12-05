package k23cnt3.qxtWebbansach.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import k23cnt3.qxtWebbansach.entity.Role;
import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Trang login
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";  // Trả về file templates/auth/login.html
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";  // Trả về file templates/auth/register.html
    }

    // Xử lý gửi form đăng ký
    @PostMapping("/register")
    public String register(User user) {

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Gán vai trò mặc định USER
        user.setRole(Role.USER);

        // Lưu user vào Database
        userRepository.save(user);

        // Chuyển hướng đến trang login
        return "redirect:/login";
    }
}
