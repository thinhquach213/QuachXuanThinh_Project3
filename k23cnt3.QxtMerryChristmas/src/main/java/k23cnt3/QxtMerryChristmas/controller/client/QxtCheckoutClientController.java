package k23cnt3.QxtMerryChristmas.controller.client;

import k23cnt3.QxtMerryChristmas.entity.order.QxtOrder;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.model.cart.QxtCartItem;
import k23cnt3.QxtMerryChristmas.order.QxtCheckoutForm;
import k23cnt3.QxtMerryChristmas.service.cart.QxtCartService;
import k23cnt3.QxtMerryChristmas.service.order.QxtOrderService;
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
public class QxtCheckoutClientController {

    private final QxtCartService cartService;
    private final QxtOrderService orderService;

    // ============= GET CHECKOUT =============
    @GetMapping("/checkout")
    public String showCheckoutPage(Model model, HttpSession session) {

        // ⭐ 1. Kiểm tra đăng nhập
        QxtUser user = (QxtUser) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login?redirect=/checkout";
        }

        // ⭐ 2. Kiểm tra giỏ hàng
        List<QxtCartItem> items = cartService.getItems(session);
        if (items == null || items.isEmpty()) {
            return "redirect:/cart";
        }

        // ⭐ 3. Khởi tạo form nếu chưa có
        if (!model.containsAttribute("checkoutForm")) {
            model.addAttribute("checkoutForm", new QxtCheckoutForm());
        }

        model.addAttribute("items", items);
        model.addAttribute("total", cartService.getTotal(session));

        return "order/QxtCheckout";
    }

    // ============= POST CHECKOUT =============
    @PostMapping("/checkout")
    public String handleCheckout(
            @Valid @ModelAttribute("checkoutForm") QxtCheckoutForm form,
            BindingResult bindingResult,
            HttpSession session,
            Model model) {

        // ⭐ 1. Chưa login → bắt buộc login
        QxtUser user = (QxtUser) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login?redirect=/checkout";
        }

        // ⭐ 2. Lấy giỏ hàng
        List<QxtCartItem> items = cartService.getItems(session);
        if (items == null || items.isEmpty()) {
            return "redirect:/cart";
        }

        // ⭐ 3. Validate lỗi
        if (bindingResult.hasErrors()) {
            model.addAttribute("items", items);
            model.addAttribute("total", cartService.getTotal(session));
            return "order/QxtCheckout";
        }

        // ⭐ 4. Tạo đơn hàng GẮN USER
        QxtOrder order = orderService.createOrderFromCart(form, items, user);

        // ⭐ 5. Xóa giỏ
        cartService.clear(session);

        return "redirect:/order/success?id=" + order.getId();
    }
}
