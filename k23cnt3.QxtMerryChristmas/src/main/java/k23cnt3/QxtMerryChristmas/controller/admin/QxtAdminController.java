package k23cnt3.QxtMerryChristmas.controller.admin;

import k23cnt3.QxtMerryChristmas.entity.order.QxtOrder;
import k23cnt3.QxtMerryChristmas.entity.order.QxtOrderStatus;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUserRole;
import k23cnt3.QxtMerryChristmas.repository.order.QxtOrderRepository;
import k23cnt3.QxtMerryChristmas.repository.user.QxtUserRepository;
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
//“Controller này xử lý toàn bộ chức năng quản trị,
// các URL đều bắt đầu bằng /admin và chỉ cho phép tài khoản có quyền ADMIN truy cập.”
@RequestMapping("/admin")
@RequiredArgsConstructor
public class QxtAdminController {

    private final QxtOrderRepository orderRepository;
    private final QxtUserRepository userRepository;

    /* ==== CHECK ADMIN ==== */
    private boolean isAdmin(QxtUser user) {
        return user != null && user.getRole() == QxtUserRole.ADMIN;
    }
    //Lấy currentUser từ HttpSession Kiểm tra role có phải ADMIN không Nếu không → redirect về login
    /* ==== LABEL TRẠNG THÁI ĐƠN ==== */
    private Map<String, String> buildStatusLabels() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(QxtOrderStatus.WAIT_CONFIRM.name(), "Chờ xác nhận");
        map.put(QxtOrderStatus.PREPARING.name(),   "Đang chuẩn bị hàng");
        map.put(QxtOrderStatus.SHIPPING.name(),    "Đang giao");
        map.put(QxtOrderStatus.COMPLETED.name(),   "Đã giao thành công");
        map.put(QxtOrderStatus.CANCELLED.name(),   "Đã hủy");
        return map;
    }
//Kiểm tra admin, Lấy tất cả đơn hàng từ DB, Đổ ra view QxtAdminOrders
    /** Redirect về đúng list theo role */
    private String redirectByRole(QxtUserRole role) {
        if (role == QxtUserRole.ADMIN) {
            return "redirect:/admin/admin-users";
        }
        return "redirect:/admin/customers";
    }

    /* ========== DASHBOARD ========== */
    @GetMapping("/dashboard")
    public String adminHome(HttpSession session, Model model) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }
        model.addAttribute("adminName", currentUser.getFullName());
        return "admin/QxtAdminDashboard";
    }
 // trạng thái sửa đơn hàng Admin chọn sửa, load đơn theo id, chọn trạng thái đơn mới,
    //Admin chỉ được cập nhật trạng thái đơn, không sửa dữ liệu gốc của khách.”
    /* ========== QUẢN LÝ ĐƠN HÀNG ========== */

    @GetMapping("/orders")
    public String adminOrders(HttpSession session, Model model) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        List<QxtOrder> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("statusLabels", buildStatusLabels());

        return "admin/QxtAdminOrders";
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable Long id,
                            HttpSession session,
                            Model model) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        QxtOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        model.addAttribute("order", order);
        model.addAttribute("statusLabels", buildStatusLabels());
        return "admin/QxtAdminOrderEdit";
    }

    @PostMapping("/orders/update")
    public String updateOrder(@RequestParam Long id,
                              @RequestParam QxtOrderStatus status,
                              HttpSession session) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        QxtOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);

        return "redirect:/admin/orders?updated";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id,
                              HttpSession session) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }
//Chỉ admin → xóa theo ID
        orderRepository.deleteById(id);
        return "redirect:/admin/orders?deleted";
    }

    /* ========== QUẢN LÝ ADMIN (ROLE = ADMIN) ========== */

    @GetMapping("/admin-users")
    public String listAdminUsers(HttpSession session, Model model) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        List<QxtUser> admins = userRepository.findByRole(QxtUserRole.ADMIN);
        model.addAttribute("admins", admins);
        return "admin/QxtAdminUserList";
    }
// Lấy user có role ADMIN
    /* ========== QUẢN LÝ KHÁCH HÀNG (ROLE = CUSTOMER) ========== */

    @GetMapping("/customers")
    public String listCustomers(HttpSession session, Model model) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        List<QxtUser> customers = userRepository.findByRole(QxtUserRole.CUSTOMER);
        model.addAttribute("customers", customers);
        return "admin/QxtCustomerList";
    }
//Lấy user có role CUSTOMER Em phân biệt admin và khách hàng bằng role trong DB.”
    /* ========== FORM THÊM USER (ADMIN / CUSTOMER) ========== */

    @GetMapping("/users/create")
    public String showCreateUserForm(@RequestParam("role") QxtUserRole role,
                                     HttpSession session,
                                     Model model) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }
//Truyền role từ URL → form dùng chung
        QxtUser user = new QxtUser();
        user.setRole(role);

        model.addAttribute("user", user);
        model.addAttribute("formMode", "CREATE");
        return "admin/QxtUserForm";
    }

    @PostMapping("/users/create")
    public String createUser(@RequestParam String fullName,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam(required = false) String phone,
                             @RequestParam QxtUserRole role,
                             HttpSession session,
                             Model model) {

        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        // Check trùng email
        Optional<QxtUser> opt = userRepository.findByEmail(email);
        if (opt.isPresent()) {
            model.addAttribute("error", "Email đã tồn tại");
            QxtUser u = new QxtUser();
            u.setFullName(fullName);
            u.setEmail(email);
            u.setPhone(phone);
            u.setRole(role);

            model.addAttribute("user", u);
            model.addAttribute("formMode", "CREATE");
            return "admin/QxtUserForm";
        }

        QxtUser user = new QxtUser();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);   // demo: chưa mã hóa
        user.setPhone(phone);
        user.setRole(role);

        userRepository.save(user);

        return redirectByRole(role);
    }
//Kiểm tra admin,Check trùng email,Lưu user mới,Redirect theo role
    /* ========== SỬA USER ========== */

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id,
                                   HttpSession session,
                                   Model model) {
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        QxtUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("formMode", "EDIT");
        return "admin/QxtUserForm";
    }
    //Load user, Check email trùng, Cho phép đổi mật khẩu nếu nhập mới
    @PostMapping("/users/update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String fullName,
                             @RequestParam String email,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) String phone,
                             @RequestParam QxtUserRole role,
                             HttpSession session,
                             Model model) {

        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        QxtUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check email trùng với người khác
        Optional<QxtUser> opt = userRepository.findByEmail(email);
        if (opt.isPresent() && !opt.get().getId().equals(id)) {
            model.addAttribute("error", "Email đã được sử dụng bởi tài khoản khác");
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setRole(role);

            model.addAttribute("user", user);
            model.addAttribute("formMode", "EDIT");
            return "admin/QxtUserForm";
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
        QxtUser currentUser = (QxtUser) session.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            return "redirect:/login";
        }

        QxtUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // optional: không cho tự xoá chính mình
        if (currentUser.getId().equals(user.getId())) {
            return redirectByRole(user.getRole()) + "?error=cannot_delete_yourself";
        }
        QxtUserRole role = user.getRole();
        userRepository.delete(user);

        return redirectByRole(role) + "?deleted";
    }
}
//“Em không cho admin tự xóa chính mình để tránh lỗi hệ thống.”