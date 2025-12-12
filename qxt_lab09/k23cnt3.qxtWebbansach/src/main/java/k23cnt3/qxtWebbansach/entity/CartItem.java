package k23cnt3.qxtWebbansach.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "qxt_cart_items")   // Đổi tên bảng với tiền tố qxt_
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")       // Đổi tên cột
    private Long id;

    @ManyToOne
    @JoinColumn(name = "qxt_cart_id")   // FK thêm tiền tố qxt_
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "qxt_product_id") // FK thêm tiền tố qxt_
    private Product product;

    @Column(name = "qxt_quantity")      // Cột số lượng thêm tiền tố
    private Integer quantity;

    public CartItem() {}

    public CartItem(Cart cart, Product product, Integer quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    // GETTER – SETTER
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cart getCart() { return cart; }
    public void setCart(Cart cart) { this.cart = cart; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
