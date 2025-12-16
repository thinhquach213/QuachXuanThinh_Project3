package com.project3.controller.client;

import com.project3.entity.order.PtaOrder;
import com.project3.entity.user.PtaUser;
import com.project3.model.cart.PtaCartItem;
import com.project3.order.PtaCheckoutForm;
import com.project3.service.cart.PtaCartService;
import com.project3.service.order.PtaOrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PtaCheckoutClientController {

    private final PtaCartService cartService;
    private final PtaOrderService orderService;

    // ============= GET CHECKOUT =============
    @GetMapping("/checkout")
    public String showCheckoutPage(Model model, HttpSession session) {

        // ⭐ 1. Kiểm tra đăng nhập
        PtaUser user = (PtaUser) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login?redirect=/checkout";
        }

        // ⭐ 2. Kiểm tra giỏ hàng
        List<PtaCartItem> items = cartService.getItems(session);
        if (items == null || items.isEmpty()) {
            return "redirect:/cart";
        }

        // ⭐ 3. Khởi tạo form nếu chưa có
        if (!model.containsAttribute("checkoutForm")) {
            model.addAttribute("checkoutForm", new PtaCheckoutForm());
        }

        model.addAttribute("items", items);
        model.addAttribute("total", cartService.getTotal(session));

        return "order/PtaCheckout";
    }

    // ============= POST CHECKOUT =============
    @PostMapping("/checkout")
    public String handleCheckout(@Valid @ModelAttribute("checkoutForm") PtaCheckoutForm form,
                                 BindingResult bindingResult,
                                 HttpSession session,
                                 Model model) {

        // ⭐ 1. Chưa login → bắt buộc login
        PtaUser user = (PtaUser) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login?redirect=/checkout";
        }

        // ⭐ 2. Lấy giỏ hàng
        List<PtaCartItem> items = cartService.getItems(session);
        if (items == null || items.isEmpty()) {
            return "redirect:/cart";
        }

        // ⭐ 3. Validate lỗi
        if (bindingResult.hasErrors()) {
            model.addAttribute("items", items);
            model.addAttribute("total", cartService.getTotal(session));
            return "order/PtaCheckout";
        }

        // ⭐ 4. Tạo đơn hàng GẮN USER
        PtaOrder order = orderService.createOrderFromCart(form, items, user);

        // ⭐ 5. Xóa giỏ
        cartService.clear(session);

        return "redirect:/order/success?id=" + order.getId();
    }
}