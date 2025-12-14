package k23cnt3.QxtMerryChristmas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QxtCartController {

    @GetMapping("/admin/cart")
    public String cartPage() {
        return "QxtCart";  // QxtCart.html
    }
}
