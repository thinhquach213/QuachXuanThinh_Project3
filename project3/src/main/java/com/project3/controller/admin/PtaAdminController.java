package com.project3.controller.admin;

import com.project3.entity.order.PtaOrder;
import com.project3.entity.order.PtaOrderStatus;
import com.project3.entity.user.PtaUser;
import com.project3.entity.user.PtaUserRole;
import com.project3.repository.order.PtaOrderRepository;
import com.project3.repository.user.PtaUserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class PtaAdminController {

    private final PtaOrderRepository orderRepository;
    private final PtaUserRepository userRepository;

    /* ==== CHECK ADMIN ==== */
    private boolean isAdmin(PtaUser user) {
        return user != null && user.getRole() == PtaUserRole.ADMIN;
    }

    /* ==== LABEL TRẠNG THÁI ĐƠN ==== */
    private Map<String, String> buildStatusLabels() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(PtaOrderStatus.WAIT_CONFIRM.name(), "Chờ xác nhận");
        map.put(PtaOrderStatus.PREPARING.name(),   "Đang chuẩn bị hàng");
        map.put(PtaOrderStatus.SHIPPING.name(),    "Đang giao");
        map.put(PtaOrderStatus.COMPLETED.name(),   "Đã giao thành công");
        map.put(PtaOrderStatus.CANCELLED.name(),   "Đã hủy");
        return map;
    }

    /** Redirect về đúng list theo role */
    private String redirectByRole(PtaUserRole role) {
        if (role == PtaUserRole.ADMIN) {
            return "redirect:/admin/admin-users";
        }
        return "redirect:/admin/customers";
    }

    /* ========== DASHBOARD ========== */
    @GetMapping("/dashboard")
    public String adminHome(HttpSession session, Model model) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }
        model.addAttribute("adminName", currentUser.getFullName());
        return "admin/PtaAdminDashboard";
    }

    /* ========== QUẢN LÝ ĐƠN HÀNG ========== */

    @GetMapping("/orders")
    public String adminOrders(HttpSession session, Model model) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        List<PtaOrder> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("statusLabels", buildStatusLabels());

        return "admin/PtaAdminOrders";
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable Long id,
                            HttpSession session,
                            Model model) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        PtaOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        model.addAttribute("order", order);
        model.addAttribute("statusLabels", buildStatusLabels());
        return "admin/PtaAdminOrderEdit";
    }

    @PostMapping("/orders/update")
    public String updateOrder(@RequestParam Long id,
                              @RequestParam PtaOrderStatus status,
                              HttpSession session) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        PtaOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);

        return "redirect:/admin/orders?updated";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id,
                              HttpSession session) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        orderRepository.deleteById(id);
        return "redirect:/admin/orders?deleted";
    }

    /* ========== QUẢN LÝ ADMIN (ROLE = ADMIN) ========== */

    @GetMapping("/admin-users")
    public String listAdminUsers(HttpSession session, Model model) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        List<PtaUser> admins = userRepository.findByRole(PtaUserRole.ADMIN);
        model.addAttribute("admins", admins);
        return "admin/PtaAdminUserList";
    }

    /* ========== QUẢN LÝ KHÁCH HÀNG (ROLE = CUSTOMER) ========== */

    @GetMapping("/customers")
    public String listCustomers(HttpSession session, Model model) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        List<PtaUser> customers = userRepository.findByRole(PtaUserRole.CUSTOMER);
        model.addAttribute("customers", customers);
        return "admin/PtaCustomerList";
    }

    /* ========== FORM THÊM USER (ADMIN / CUSTOMER) ========== */

    @GetMapping("/users/create")
    public String showCreateUserForm(@RequestParam("role") PtaUserRole role,
                                     HttpSession session,
                                     Model model) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        PtaUser user = new PtaUser();
        user.setRole(role);

        model.addAttribute("user", user);
        model.addAttribute("formMode", "CREATE");
        return "admin/PtaUserForm";
    }

    @PostMapping("/users/create")
    public String createUser(@RequestParam String fullName,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam(required = false) String phone,
                             @RequestParam PtaUserRole role,
                             HttpSession session,
                             Model model) {

        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        // Check trùng email
        Optional<PtaUser> opt = userRepository.findByEmail(email);
        if (opt.isPresent()) {
            model.addAttribute("error", "Email đã tồn tại");
            PtaUser u = new PtaUser();
            u.setFullName(fullName);
            u.setEmail(email);
            u.setPhone(phone);
            u.setRole(role);

            model.addAttribute("user", u);
            model.addAttribute("formMode", "CREATE");
            return "admin/PtaUserForm";
        }

        PtaUser user = new PtaUser();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);   // demo: chưa mã hóa
        user.setPhone(phone);
        user.setRole(role);

        userRepository.save(user);

        return redirectByRole(role);
    }

    /* ========== SỬA USER ========== */

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id,
                                   HttpSession session,
                                   Model model) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        PtaUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("formMode", "EDIT");
        return "admin/PtaUserForm";
    }

    @PostMapping("/users/update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String fullName,
                             @RequestParam String email,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) String phone,
                             @RequestParam PtaUserRole role,
                             HttpSession session,
                             Model model) {

        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        PtaUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check email trùng với người khác
        Optional<PtaUser> opt = userRepository.findByEmail(email);
        if (opt.isPresent() && !opt.get().getId().equals(id)) {
            model.addAttribute("error", "Email đã được sử dụng bởi tài khoản khác");
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setRole(role);

            model.addAttribute("user", user);
            model.addAttribute("formMode", "EDIT");
            return "admin/PtaUserForm";
        }

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);

        if (password != null && !password.isBlank()) {
            user.setPassword(password);
        }

        userRepository.save(user);

        return redirectByRole(user.getRole());
    }

    /* ========== XOÁ USER ========== */

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id,
                             HttpSession session) {
        PtaUser currentUser = (PtaUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        PtaUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // optional: không cho tự xoá chính mình
        if (currentUser.getId().equals(user.getId())) {
            return redirectByRole(user.getRole()) + "?error=cannot_delete_yourself";
        }
        PtaUserRole role = user.getRole();
        userRepository.delete(user);

        return redirectByRole(role) + "?deleted";
    }
}