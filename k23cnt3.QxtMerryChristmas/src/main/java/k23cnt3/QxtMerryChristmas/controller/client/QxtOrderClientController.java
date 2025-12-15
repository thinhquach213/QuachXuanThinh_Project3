package k23cnt3.QxtMerryChristmas.controller.client;


import k23cnt3.QxtMerryChristmas.entity.order.QxtOrder;
import k23cnt3.QxtMerryChristmas.service.order.QxtOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class QxtOrderClientController {

    private final QxtOrderService orderService;

    // =============================================================
    //  TRANG THÔNG BÁO ĐẶT HÀNG THÀNH CÔNG
    // =============================================================
    @GetMapping("/success")
    public String orderSuccess(@RequestParam("id") Long id, Model model) {

        QxtOrder order = orderService.getById(id);

        if (order == null) {
            return "redirect:/";  // không có đơn thì về Home
        }

        model.addAttribute("order", order);
        return "order/QxtOrderSuccess";
    }
}