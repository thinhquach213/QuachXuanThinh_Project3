package k23cnt3.qxtWebbansach.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import k23cnt3.qxtWebbansach.entity.Order;
import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.service.OrderService;
import k23cnt3.qxtWebbansach.service.UserService;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // Trang danh sách đơn hàng
    @GetMapping("/user/orders")
    public String listUserOrders(Model model) {

        User currentUser = userService.getCurrentUser();   // ⭐ Lấy user hiện tại

        List<Order> orders = orderService.findByUser(currentUser);
        model.addAttribute("orders", orders);

        return "user/orders";
    }
}
