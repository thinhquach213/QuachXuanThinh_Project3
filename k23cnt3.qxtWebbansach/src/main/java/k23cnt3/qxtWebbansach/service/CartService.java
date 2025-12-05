package k23cnt3.qxtWebbansach.service;

import k23cnt3.qxtWebbansach.dto.CartDTO;
import k23cnt3.qxtWebbansach.model.CartItem;
import java.util.List;

public interface CartService {
    List<CartDTO> getCartItems(Long userId);
    CartItem addToCart(Long userId, Long bookId, int quantity);
    CartItem updateCartItem(Long userId, Long bookId, int quantity);
    void removeCartItem(Long userId, Long bookId);
    void clearCart(Long userId);
}
