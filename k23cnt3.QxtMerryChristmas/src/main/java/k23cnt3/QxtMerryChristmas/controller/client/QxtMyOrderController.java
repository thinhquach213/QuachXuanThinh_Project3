package k23cnt3.QxtMerryChristmas.controller.client;

import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.service.order.QxtOrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class QxtMyOrderController {

    private final QxtOrderService orderService;

    @GetMapping("/my-orders")
    public String viewMyOrders(HttpSession session, Model model) {

        // Check login
        QxtUser user = (QxtUser) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login?redirect=/my-orders";
        }

        model.addAttribute(
                "orders",
                orderService.findByUserId(user.getId())
        );

        return "order/QxtMyOrders";
    }
}
