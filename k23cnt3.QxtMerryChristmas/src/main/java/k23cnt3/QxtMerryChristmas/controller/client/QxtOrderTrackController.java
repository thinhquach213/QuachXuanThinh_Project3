package k23cnt3.QxtMerryChristmas.controller.client;

import k23cnt3.QxtMerryChristmas.order.QxtOrderTrackForm;
import k23cnt3.QxtMerryChristmas.service.order.QxtOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//“Controller này cho phép khách hàng theo dõi đơn hàng bằng cách nhập mã đơn và số điện thoại.
//Em validate dữ liệu phía server và chỉ hiển thị đơn hàng khi thông tin khớp để đảm bảo bảo mật.”
/**
 * Controller xử lý chức năng THEO DÕI ĐƠN HÀNG cho khách hàng
 * Người dùng nhập mã đơn + số điện thoại để xem trạng thái đơn
 */
@Controller
@RequiredArgsConstructor
public class QxtOrderTrackController {

    // Service xử lý nghiệp vụ liên quan đến đơn hàng
    private final QxtOrderService orderService;

    /* =================================================
     *  HIỂN THỊ FORM THEO DÕI ĐƠN HÀNG
     *  URL: GET /order/track
     * ================================================= */
    @GetMapping("/order/track")
    public String showForm(Model model) {

        // 1. Khởi tạo form theo dõi đơn hàng
        model.addAttribute("trackForm", new QxtOrderTrackForm());

        // 2. Trả về trang nhập thông tin theo dõi đơn
        return "order/QxtOrderTracking";
    }
    /* =================================================
     *  XỬ LÝ THEO DÕI ĐƠN HÀNG
     *  URL: POST /order/track
     * ================================================= */
    @PostMapping("/order/track")
    public String trackOrder(
            @Valid QxtOrderTrackForm form,   // validate dữ liệu người dùng nhập
            BindingResult result,
            Model model) {

        // 1. Nếu dữ liệu form không hợp lệ (bỏ trống, sai định dạng...)
        if (result.hasErrors()) {
            // Giữ lại dữ liệu đã nhập để hiển thị lại form
            model.addAttribute("trackForm", form);
            return "order/QxtOrderTracking";
        }

        // 2. Tìm đơn hàng theo MÃ ĐƠN + SỐ ĐIỆN THOẠI (bảo mật)
        var order = orderService.findByIdAndPhone(
                form.getOrderId(),
                form.getPhone()
        );

        // 3. Nếu không tìm thấy đơn hàng
        if (order == null) {
            model.addAttribute("notFound", true); // dùng để hiển thị thông báo lỗi
            model.addAttribute("trackForm", form);
            return "order/QxtOrderTracking";
        }

        // 4. Nếu tìm thấy → hiển thị thông tin đơn hàng
        model.addAttribute("order", order);
        model.addAttribute("trackForm", form);

        return "order/QxtOrderTracking";
    }
}
