package com.project3.controller.client;


import com.project3.entity.order.PtaOrder;
import com.project3.service.order.PtaOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class PtaOrderClientController {

    private final PtaOrderService orderService;

    // =============================================================
    //  TRANG THÔNG BÁO ĐẶT HÀNG THÀNH CÔNG
    // =============================================================
    @GetMapping("/success")
    public String orderSuccess(@RequestParam("id") Long id, Model model) {

        PtaOrder order = orderService.getById(id);

        if (order == null) {
            return "redirect:/";  // không có đơn thì về Home
        }

        model.addAttribute("order", order);
        return "order/PtaOrderSuccess";
    }
}