package k23cnt3.qxtWebbansach.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import k23cnt3.qxtWebbansach.entity.Order;
import k23cnt3.qxtWebbansach.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    // ================== DANH SÁCH ĐƠN HÀNG ==================
    @GetMapping("")
    public String list(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/order/list";
    }

    // ================== CHI TIẾT ĐƠN HÀNG ==================
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        Order order = orderService.findById(id).orElse(null);

        if (order == null) {
            return "redirect:/admin/orders";
        }

        model.addAttribute("order", order);
        return "admin/order/order-details";
    }
}
