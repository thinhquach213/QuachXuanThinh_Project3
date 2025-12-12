package k23cnt3.qxtWebbansach.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "qxt_carts", // thêm tiền tố qxt_
        uniqueConstraints = @UniqueConstraint(columnNames = "qxt_user_id")
)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id") // thêm tiền tố qxt_
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qxt_user_id", nullable = false, unique = true) // thêm tiền tố qxt_
    private User user;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartItem> items = new ArrayList<>();

    // --- Getter & Setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<CartItem> getItems() { return items; }

    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }
}
