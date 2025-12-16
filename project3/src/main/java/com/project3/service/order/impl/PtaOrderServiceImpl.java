package com.project3.service.order.impl;

import com.project3.entity.order.PtaOrder;
import com.project3.entity.order.PtaOrderItem;
import com.project3.entity.order.PtaOrderStatus;
import com.project3.entity.user.PtaUser;
import com.project3.model.cart.PtaCartItem;
import com.project3.order.PtaCheckoutForm;
import com.project3.repository.order.PtaOrderRepository;
import com.project3.service.order.PtaOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PtaOrderServiceImpl implements PtaOrderService {

    private final PtaOrderRepository orderRepository;

    @Override
    public PtaOrder createOrderFromCart(PtaCheckoutForm form, List<PtaCartItem> cartItems, PtaUser user) {

        PtaOrder order = new PtaOrder();
        order.setCustomerName(form.getCustomerName());
        order.setPhone(form.getPhone());
        order.setAddress(form.getAddress());
        order.setNote(form.getNote());

        // ⭐ Gắn USER
        order.setUser(user);

        order.setStatus(PtaOrderStatus.WAIT_CONFIRM);
        order.setCreatedAt(LocalDateTime.now());

        double total = 0;
        List<PtaOrderItem> items = new ArrayList<>();

        for (PtaCartItem c : cartItems) {
            PtaOrderItem item = new PtaOrderItem();
            item.setOrder(order);
            item.setProductId(c.getProductId());
            item.setProductName(c.getProductName());
            item.setProductPrice(c.getPrice());
            item.setQuantity(c.getQuantity());

            total += c.getPrice() * c.getQuantity();
            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    @Override
    public PtaOrder getById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public PtaOrder findByIdAndPhone(Long id, String phone) {
        return orderRepository.findByIdAndPhone(id, phone).orElse(null);
    }

    @Override
    public List<PtaOrder> findByPhone(String phone) {
        return orderRepository.findByPhone(phone);
    }

    @Override
    public List<PtaOrder> findByUserId(Long userId) {
        return orderRepository.findByUser_IdOrderByCreatedAtDesc(userId);
    }
}