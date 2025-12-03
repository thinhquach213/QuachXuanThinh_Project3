package project3.controller.admin;

import k23cnt3.qxtWebbansach.model.Order;
import k23cnt3.qxtWebbansach.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String list(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id).orElse(null));
        return "admin/orders/detail";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Order order) {
        orderService.updateOrder(order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }
}
