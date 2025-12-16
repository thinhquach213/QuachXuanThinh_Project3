package com.project3.service.order;

import com.project3.entity.order.PtaOrder;
import com.project3.entity.user.PtaUser;
import com.project3.model.cart.PtaCartItem;
import com.project3.order.PtaCheckoutForm;

import java.util.List;

public interface PtaOrderService {

    PtaOrder createOrderFromCart(PtaCheckoutForm form, List<PtaCartItem> cartItems, PtaUser user);

    PtaOrder getById(Long id);

    PtaOrder findByIdAndPhone(Long id, String phone);

    List<PtaOrder> findByPhone(String phone);

    List<PtaOrder> findByUserId(Long userId);
}