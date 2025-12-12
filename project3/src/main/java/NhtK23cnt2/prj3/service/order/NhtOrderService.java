package NhtK23cnt2.prj3.service.order;

import NhtK23cnt2.prj3.entity.order.NhtOrder;
import NhtK23cnt2.prj3.entity.user.NhtUser;
import NhtK23cnt2.prj3.model.cart.NhtCartItem;
import NhtK23cnt2.prj3.order.NhtCheckoutForm;

import java.util.List;

public interface NhtOrderService {

    NhtOrder createOrderFromCart(NhtCheckoutForm form, List<NhtCartItem> cartItems, NhtUser user);

    NhtOrder getById(Long id);

    NhtOrder findByIdAndPhone(Long id, String phone);

    List<NhtOrder> findByPhone(String phone);

    List<NhtOrder> findByUserId(Long userId);
}
