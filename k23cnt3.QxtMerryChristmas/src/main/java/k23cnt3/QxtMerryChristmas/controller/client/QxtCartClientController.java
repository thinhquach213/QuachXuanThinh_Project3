package k23cnt3.QxtMerryChristmas.controller.client;

import k23cnt3.QxtMerryChristmas.service.cart.QxtCartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class QxtCartClientController {
//“Controller này xử lý toàn bộ chức năng giỏ hàng phía client.”
    private final QxtCartService cartService;
//“Em tách logic giỏ hàng sang service để controller chỉ xử lý request và response.”
    @GetMapping("/cart")
    public String cartPage(Model model, HttpSession session) {
        model.addAttribute("items", cartService.getItems(session));
        model.addAttribute("total", cartService.getTotal(session));
        return "QxtCart";
    }
    //“Giỏ hàng được lưu trong session, không lưu DB.”

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
    // Xóa item khỏi session Em cho phép xóa từng sản phẩm khỏi giỏ hàng.”
    //dùng Session cho giỏ hàng “Giỏ hàng chỉ là dữ liệu tạm thời, chưa phát sinh
    // giao dịch nên em lưu trong session để đơn giản và hiệu quả.”
    @PostMapping("/cart/remove")
    public String removeItem(
            @RequestParam("productId") Long productId,
            HttpSession session) {
        cartService.removeItem(productId, session);
        return "redirect:/cart";
    }
}