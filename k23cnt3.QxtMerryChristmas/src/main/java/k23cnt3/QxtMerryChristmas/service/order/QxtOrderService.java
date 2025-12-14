package k23cnt3.QxtMerryChristmas.service.order;

import k23cnt3.QxtMerryChristmas.entity.order.QxtOrder;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.model.cart.QxtCartItem;
import k23cnt3.QxtMerryChristmas.order.QxtCheckoutForm;

import java.util.List;

public interface QxtOrderService {

    QxtOrder createOrderFromCart(QxtCheckoutForm form, List<QxtCartItem> cartItems, QxtUser user);

    QxtOrder getById(Long id);

    QxtOrder findByIdAndPhone(Long id, String phone);

    List<QxtOrder> findByPhone(String phone);

    List<QxtOrder> findByUserId(Long userId);
}
