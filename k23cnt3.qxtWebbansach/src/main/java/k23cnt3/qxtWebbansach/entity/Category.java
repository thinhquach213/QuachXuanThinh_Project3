package k23cnt3.qxtWebbansach.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "qxt_categories")   // Đổi tên bảng
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")      // Đổi tên cột id
    private Long id;

    @Column(name = "qxt_name")    // Đổi tên cột name
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    // Getter – Setter nếu bạn muốn giữ kiểu viết tay
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}
