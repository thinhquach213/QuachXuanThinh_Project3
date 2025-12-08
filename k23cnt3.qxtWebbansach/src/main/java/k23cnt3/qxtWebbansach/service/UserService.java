package k23cnt3.qxtWebbansach.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Lấy user hiện tại đang đăng nhập
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        String username = authentication.getName(); // Lấy username từ Spring Security

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
    }
}
