package com.project3.controller.client;

import com.project3.entity.user.PtaUser;
import com.project3.service.order.PtaOrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PtaMyOrderController {

    private final PtaOrderService orderService;

    @GetMapping("/my-orders")
    public String viewMyOrders(HttpSession session, Model model) {

        // Check login
        PtaUser user = (PtaUser) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login?redirect=/my-orders";
        }

        model.addAttribute("orders",
                orderService.findByUserId(user.getId())
        );

        return "order/PtaMyOrders";
    }
}