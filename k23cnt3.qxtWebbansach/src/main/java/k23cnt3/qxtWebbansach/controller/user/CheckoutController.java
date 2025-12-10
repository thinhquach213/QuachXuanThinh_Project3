package k23cnt3.qxtWebbansach.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import k23cnt3.qxtWebbansach.entity.Cart;
import k23cnt3.qxtWebbansach.entity.Order;
import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.service.CartService;
import k23cnt3.qxtWebbansach.service.OrderService;
import k23cnt3.qxtWebbansach.service.UserService;

@Controller
@RequestMapping("/user")
public class CheckoutController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    // ================================
    // HIỂN THỊ TRANG CHECKOUT
    // ================================
    @GetMapping("/checkout")
    public String checkoutPage(Model model) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return "redirect:/login";
        }

        Cart cart = cartService.getCartByUser(user);

        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cartService.getTotal(user));

        return "user/checkout";
    }

    // ================================
    // XỬ LÝ THANH TOÁN
    // ================================
    @PostMapping("/checkout")
    public String processCheckout(@RequestParam String fullName,
                                  @RequestParam String phone,
                                  @RequestParam String address,
                                  @RequestParam String paymentMethod,
                                  Model model) {

        User user = userService.getCurrentUser();
        if (user == null) {
            return "redirect:/login";
        }

        Cart cart = cartService.getCartByUser(user);

        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        // 🔥 Tạo đơn hàng từ giỏ hàng
        Order order = orderService.createOrderFromCart(
                cart, fullName, phone, address, paymentMethod
        );

        // 🔥 Xóa giỏ hàng sau khi thanh toán
        cartService.clearCart(user);

        // Truyền thông tin đơn hàng sang trang success
        model.addAttribute("order", order);

        return "user/orders-success";   // ❤️ TRANG THÔNG BÁO THÀNH CÔNG
    }
}
