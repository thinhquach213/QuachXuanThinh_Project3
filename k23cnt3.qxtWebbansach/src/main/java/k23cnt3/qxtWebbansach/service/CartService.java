package k23cnt3.qxtWebbansach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import k23cnt3.qxtWebbansach.entity.Cart;
import k23cnt3.qxtWebbansach.entity.CartItem;
import k23cnt3.qxtWebbansach.entity.Product;
import k23cnt3.qxtWebbansach.entity.User;
import k23cnt3.qxtWebbansach.repository.CartItemRepository;
import k23cnt3.qxtWebbansach.repository.CartRepository;
import k23cnt3.qxtWebbansach.repository.ProductRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCartByUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    public List<CartItem> getItems(User user) {
        return getCartByUser(user).getItems();
    }

    public void addProduct(User user, Long productId, Integer quantity) {

        Cart cart = getCartByUser(user);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        CartItem existingItem = null;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem item = new CartItem(cart, product, quantity);
            cart.addItem(item);
            cartItemRepository.save(item);
        }
        cartRepository.save(cart);
    }

    public void updateItem(Long cartItemId, Integer quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item không tồn tại"));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public void removeItem(Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item không tồn tại"));
        Cart cart = item.getCart();
        cart.removeItem(item);
        cartItemRepository.delete(item);
        cartRepository.save(cart);
    }

    public double getTotal(User user) {
        double total = 0;
        for (CartItem item : getItems(user)) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void clearCart(User user) {
        Cart cart = getCartByUser(user);

        // Xóa toàn bộ cart_items trong DB
        cart.getItems().clear();

        // Lưu lại giỏ trống
        cartRepository.save(cart);
    }
}
