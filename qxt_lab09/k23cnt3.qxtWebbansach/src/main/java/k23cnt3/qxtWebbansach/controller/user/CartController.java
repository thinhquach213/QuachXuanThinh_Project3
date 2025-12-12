package k23cnt3.qxtWebbansach.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.service.CartService;
import k23cnt3.qxtWebbansach.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewCart(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("items", cartService.getItems(user));
        model.addAttribute("total", cartService.getTotal(user));
        return "user/cart";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam Long productId,
                             @RequestParam Integer quantity) {
        User user = userService.getCurrentUser();
        cartService.addProduct(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam Long cartItemId,
                             @RequestParam Integer quantity) {
        cartService.updateItem(cartItemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long cartItemId) {
        cartService.removeItem(cartItemId);
        return "redirect:/cart";
    }
}
