package k23cnt3.QxtMerryChristmas.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QxtOrderTrackForm {

    @NotNull(message = "Vui lòng nhập mã đơn hàng")
    private Long orderId;

    @NotBlank(message = "Vui lòng nhập số điện thoại")
    private String phone;
}