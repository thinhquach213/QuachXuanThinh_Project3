package k23cnt3.QxtMerryChristmas.service.order.impl;

import k23cnt3.QxtMerryChristmas.entity.order.QxtOrder;
import k23cnt3.QxtMerryChristmas.entity.order.QxtOrderItem;
import k23cnt3.QxtMerryChristmas.entity.order.QxtOrderStatus;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.model.cart.QxtCartItem;
import k23cnt3.QxtMerryChristmas.order.QxtCheckoutForm;
import k23cnt3.QxtMerryChristmas.repository.order.QxtOrderRepository;
import k23cnt3.QxtMerryChristmas.service.order.QxtOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QxtOrderServiceImpl implements QxtOrderService {

    private final QxtOrderRepository orderRepository;

    @Override
    public QxtOrder createOrderFromCart(QxtCheckoutForm form,
                                        List<QxtCartItem> cartItems,
                                        QxtUser user) {

        QxtOrder order = new QxtOrder();
        order.setCustomerName(form.getCustomerName());
        order.setPhone(form.getPhone());
        order.setAddress(form.getAddress());
        order.setNote(form.getNote());

        // ⭐ Gắn USER
        order.setUser(user);

        order.setStatus(QxtOrderStatus.WAIT_CONFIRM);
        order.setCreatedAt(LocalDateTime.now());

        double total = 0;
        List<QxtOrderItem> items = new ArrayList<>();

        for (QxtCartItem c : cartItems) {
            QxtOrderItem item = new QxtOrderItem();
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
    public QxtOrder getById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public QxtOrder findByIdAndPhone(Long id, String phone) {
        return orderRepository.findByIdAndPhone(id, phone).orElse(null);
    }

    @Override
    public List<QxtOrder> findByPhone(String phone) {
        return orderRepository.findByPhone(phone);
    }

    @Override
    public List<QxtOrder> findByUserId(Long userId) {
        return orderRepository.findByUser_IdOrderByCreatedAtDesc(userId);
    }
}
