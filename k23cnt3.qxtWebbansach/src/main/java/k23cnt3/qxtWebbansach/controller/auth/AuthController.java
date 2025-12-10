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
        return "auth/login";  // Hiển thị trang login
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";  // Hiển thị trang đăng ký
    }

    // Xử lý đăng ký
    @PostMapping("/register")
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Mã hóa mật khẩu
        user.setRole(Role.USER); // Mặc định user có role USER
        userRepository.save(user);
        return "redirect:/login";  // Đăng ký xong chuyển về trang login
    }
}
