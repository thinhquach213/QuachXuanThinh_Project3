package project3.controller.admin;

import k23cnt3.qxtWebbansach.service.BookService;
import k23cnt3.qxtWebbansach.service.OrderService;
import k23cnt3.qxtWebbansach.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    private final UserService userService;
    private final BookService bookService;
    private final OrderService orderService;

    public AdminDashboardController(UserService userService, BookService bookService, OrderService orderService) {
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping({"", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("userCount", userService.getAllUsers().size());
        model.addAttribute("bookCount", bookService.getAllBooks().size());
        model.addAttribute("orderCount", orderService.getAllOrders().size());
        return "admin/dashboard";
    }
}
