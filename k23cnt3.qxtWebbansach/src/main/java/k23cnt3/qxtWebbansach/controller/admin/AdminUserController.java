package k23cnt3.qxtWebbansach.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.entity.Role;
import k23cnt3.qxtWebbansach.entity.Cart;
import k23cnt3.qxtWebbansach.repository.UserRepository;
import k23cnt3.qxtWebbansach.repository.CartRepository;

@Controller
@RequestMapping("/admin/user")   // ★ DÙNG ĐÚNG ĐƯỜNG DẪN BẠN MUỐN
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ⭐ LIST USER
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/user/list";
    }

    // ⭐ FORM ADD
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "admin/user/form";
    }

    // ⭐ LƯU USER MỚI
    @PostMapping("/save")
    public String save(@ModelAttribute User user) {

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        // Tự tạo giỏ hàng cho user (nếu chưa có)
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        return "redirect:/admin/user";
    }

    // ⭐ FORM EDIT
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/user/form";
    }

    // ⭐ XÓA
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/user";
    }
}
