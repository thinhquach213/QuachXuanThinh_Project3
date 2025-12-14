package k23cnt3.QxtMerryChristmas.model.cart;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QxtCartItem {

    Long productId;
    String productName;
    String productImage;
    Double price;
    Integer quantity;
}
