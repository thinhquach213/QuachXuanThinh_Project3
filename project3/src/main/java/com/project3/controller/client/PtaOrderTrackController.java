package com.project3.controller.client;

import com.project3.order.PtaOrderTrackForm;
import com.project3.service.order.PtaOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PtaOrderTrackController {

    private final PtaOrderService orderService;

    @GetMapping("/order/track")
    public String showForm(Model model) {
        model.addAttribute("trackForm", new PtaOrderTrackForm());
        return "order/PtaOrderTracking";
    }

    @PostMapping("/order/track")
    public String trackOrder(
            @Valid PtaOrderTrackForm form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("trackForm", form);
            return "order/PtaOrderTracking";
        }

        var order = orderService.findByIdAndPhone(form.getOrderId(), form.getPhone());

        if (order == null) {
            model.addAttribute("notFound", true);
            model.addAttribute("trackForm", form);
            return "order/PtaOrderTracking";
        }

        model.addAttribute("order", order);
        model.addAttribute("trackForm", form);
        return "order/PtaOrderTracking";
    }
}