package K23cnt3.QxtWebBanSach.controller.client;

import K23cnt3.QxtWebBanSach.service.cart.QxtCartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class QxtCartClientController {

    private final QxtCartService cartService;

    @GetMapping("/cart")
    public String cartPage(Model model, HttpSession session) {
        model.addAttribute("items", cartService.getItems(session));
        model.addAttribute("total", cartService.getTotal(session));
        return "QxtCart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId, HttpSession session) {
        cartService.addToCart(productId, session);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            HttpSession session) {
        cartService.updateQuantity(productId, quantity, session);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeItem(
            @RequestParam("productId") Long productId,
            HttpSession session) {
        cartService.removeItem(productId, session);
        return "redirect:/cart";
    }
}
