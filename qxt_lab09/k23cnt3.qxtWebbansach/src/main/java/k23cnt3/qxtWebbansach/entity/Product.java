package k23cnt3.qxtWebbansach.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "qxt_products") // đổi tên bảng
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id") // thêm tiền tố qxt_
    private Long id;

    @Column(name = "qxt_name")
    private String name;

    @Column(name = "qxt_description")
    private String description;

    @Column(name = "qxt_price")
    private Double price;

    @Column(name = "qxt_quantity")
    private Integer quantity;

    @Column(name = "qxt_image_url")
    private String imageUrl;

    @Column(name = "qxt_featured", nullable = false)
    private boolean featured = false;

    @Column(name = "qxt_is_new", nullable = false)
    private boolean newProduct = false;

    @Column(name = "qxt_discount")
    private Double discount;

    @Column(name = "qxt_sale_price")
    private Double salePrice;

    @ManyToOne
    @JoinColumn(name = "qxt_category_id") // đổi tên cột
    @ToString.Exclude
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(name = "qxt_created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    // --- Getter & Setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }

    public boolean isNewProduct() { return newProduct; }
    public void setNewProduct(boolean newProduct) { this.newProduct = newProduct; }

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }

    public Double getSalePrice() { return salePrice; }
    public void setSalePrice(Double salePrice) { this.salePrice = salePrice; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}
