package k23cnt3.qxtWebbansach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import k23cnt3.qxtWebbansach.entity.Category;
import k23cnt3.qxtWebbansach.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Sản phẩm nổi bật
    List<Product> findByFeaturedTrue();

    // Tìm kiếm theo tên
    List<Product> findByNameContainingIgnoreCase(String name);

    // Lọc theo danh mục
    List<Product> findByCategoryId(Long categoryId);

    // Top 4 sản phẩm cùng danh mục, bỏ qua sản phẩm hiện tại
    List<Product> findTop4ByCategoryAndIdNot(Category category, Long id);

    // Khuyến mãi
    List<Product> findByDiscountGreaterThan(Double discount);

    // Hàng mới
    List<Product> findByIsNewTrue();

    // Top 4 sản phẩm mới theo ngày tạo
    List<Product> findTop4ByOrderByCreatedDateDesc();
}
