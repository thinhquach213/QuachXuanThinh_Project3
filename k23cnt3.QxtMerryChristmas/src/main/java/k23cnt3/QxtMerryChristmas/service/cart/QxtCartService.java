package k23cnt3.QxtMerryChristmas.service.cart;

import k23cnt3.QxtMerryChristmas.entity.product.QxtProduct;
import k23cnt3.QxtMerryChristmas.model.cart.QxtCartItem;
import k23cnt3.QxtMerryChristmas.service.product.QxtProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QxtCartService {

    private final QxtProductService productService;

    private static final String CART_SESSION_KEY = "cart";

    /** Lấy Map trong session */
    @SuppressWarnings("unchecked")
    private Map<Long, QxtCartItem> getCartMap(HttpSession session) {
        Object obj = session.getAttribute(CART_SESSION_KEY);
        if (obj == null) {
            Map<Long, QxtCartItem> cart = new LinkedHashMap<>();
            session.setAttribute(CART_SESSION_KEY, cart);
            return cart;
        }
        return (Map<Long, QxtCartItem>) obj;
    }

    /* ================== API CHÍNH ================== */

    /** Lấy danh sách item trong giỏ */
    public List<QxtCartItem> getItems(HttpSession session) {
        return new ArrayList<>(getCartMap(session).values());
    }

    /** Thêm 1 sp vào giỏ */
    public void addToCart(Long productId, HttpSession session) {
        Map<Long, QxtCartItem> cart = getCartMap(session);
        QxtCartItem item = cart.get(productId);

        if (item == null) {
            QxtProduct p = productService.getById(productId);
            if (p == null) return;

            item = QxtCartItem.builder()
                    .productId(p.getId())
                    .productName(p.getName())
                    .productImage(p.getImage())
                    .price(p.getPrice())
                    .quantity(1)
                    .build();
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
        cart.put(productId, item);
    }

    /** Cập nhật số lượng */
    public void updateQuantity(Long productId, int quantity, HttpSession session) {
        Map<Long, QxtCartItem> cart = getCartMap(session);
        QxtCartItem item = cart.get(productId);
        if (item != null) {
            if (quantity <= 0) {
                cart.remove(productId);
            } else {
                item.setQuantity(quantity);
            }
        }
    }

    /** Xoá 1 item */
    public void removeItem(Long productId, HttpSession session) {
        Map<Long, QxtCartItem> cart = getCartMap(session);
        cart.remove(productId);
    }

    /** Tính tổng tiền */
    public double getTotal(HttpSession session) {
        return getCartMap(session).values().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }

    /** Xoá giỏ hàng */
    public void clear(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    /* ========= giữ lại tên cũ (nếu view / code khác đang dùng) ========= */

    public List<QxtCartItem> getCart(HttpSession session) {
        return getItems(session);
    }

    public double calculateTotal(List<QxtCartItem> items) {
        return items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }

    public void clearCart(HttpSession session) {
        clear(session);
    }
}
