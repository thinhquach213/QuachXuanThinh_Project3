package k23cnt3.QxtMerryChristmas.controller.client;

import k23cnt3.QxtMerryChristmas.order.QxtOrderTrackForm;
import k23cnt3.QxtMerryChristmas.service.order.QxtOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class QxtOrderTrackController {

    private final QxtOrderService orderService;

    @GetMapping("/order/track")
    public String showForm(Model model) {
        model.addAttribute("trackForm", new QxtOrderTrackForm());
        return "order/QxtOrderTracking";
    }

    @PostMapping("/order/track")
    public String trackOrder(
            @Valid QxtOrderTrackForm form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("trackForm", form);
            return "order/QxtOrderTracking";
        }

        var order = orderService.findByIdAndPhone(form.getOrderId(), form.getPhone());

        if (order == null) {
            model.addAttribute("notFound", true);
            model.addAttribute("trackForm", form);
            return "order/QxtOrderTracking";
        }

        model.addAttribute("order", order);
        model.addAttribute("trackForm", form);
        return "order/QxtOrderTracking";
    }
}
