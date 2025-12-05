package k23cnt3.qxtWebbansach.service.impl;

import k23cnt3.qxtWebbansach.dto.CartDTO;
import k23cnt3.qxtWebbansach.model.CartItem;
import k23cnt3.qxtWebbansach.model.Book;
import k23cnt3.qxtWebbansach.repository.CartItemRepository;
import k23cnt3.qxtWebbansach.repository.BookRepository;
import k23cnt3.qxtWebbansach.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    public CartServiceImpl(CartItemRepository cartItemRepository, BookRepository bookRepository){
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<CartDTO> getCartItems(Long userId) {
        return cartItemRepository.findByUserId(userId).stream().map(item -> {
            CartDTO dto = new CartDTO();
            dto.setId(item.getId());
            dto.setBookId(item.getBook().getId());
            dto.setBookTitle(item.getBook().getTitle());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getBook().getPrice());
            dto.setTotal(item.getBook().getPrice().multiply(java.math.BigDecimal.valueOf(item.getQuantity())));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CartItem addToCart(Long userId, Long bookId, int quantity) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        return cartItemRepository.findByUserIdAndBookId(userId, bookId).map(item -> {
            item.setQuantity(item.getQuantity() + quantity);
            return cartItemRepository.save(item);
        }).orElseGet(() -> {
            CartItem item = new CartItem();
            item.setUserId(userId);
            item.setBook(book);
            item.setQuantity(quantity);
            return cartItemRepository.save(item);
        });
    }

    @Override
    public CartItem updateCartItem(Long userId, Long bookId, int quantity) {
        CartItem item = cartItemRepository.findByUserIdAndBookId(userId, bookId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public void removeCartItem(Long userId, Long bookId) {
        cartItemRepository.findByUserIdAndBookId(userId, bookId).ifPresent(cartItemRepository::delete);
    }

    @Override
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
