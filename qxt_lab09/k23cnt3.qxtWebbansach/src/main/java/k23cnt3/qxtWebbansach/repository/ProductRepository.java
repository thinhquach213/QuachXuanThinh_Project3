package k23cnt3.qxtWebbansach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import k23cnt3.qxtWebbansach.entity.Category;
import k23cnt3.qxtWebbansach.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByFeaturedTrue();

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findTop4ByCategoryAndIdNot(Category category, Long id);

    // ✅ Hàng mới
    List<Product> findByNewProductTrue();

    // ✅ Top sản phẩm mới
    List<Product> findTop4ByOrderByCreatedDateDesc();

    // ✅ Khuyến mãi
    List<Product> findByDiscountGreaterThan(Double discount);
}
