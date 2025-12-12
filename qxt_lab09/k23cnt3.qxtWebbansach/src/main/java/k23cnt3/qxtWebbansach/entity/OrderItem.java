package k23cnt3.qxtWebbansach.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "qxt_order_items")   // Đổi tên bảng
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")      // Đổi tên cột id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "qxt_order_id")   // Khóa ngoại → bảng qxt_orders
    private Order order;

    @ManyToOne
    @JoinColumn(name = "qxt_product_id") // Khóa ngoại → bảng qxt_products (nếu bạn đổi tên Product)
    private Product product;

    @Column(name = "qxt_quantity")
    private Integer quantity;

    @Column(name = "qxt_price")
    private Double price;

    // Getter – Setter thủ công giữ nguyên
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
