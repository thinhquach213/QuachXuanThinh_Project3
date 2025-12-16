package com.project3.model.cart;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PtaCartItem {

    Long productId;
    String productName;
    String productImage;
    Double price;
    Integer quantity;
}