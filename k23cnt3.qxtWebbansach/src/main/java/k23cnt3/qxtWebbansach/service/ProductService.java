package k23cnt3.qxtWebbansach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import k23cnt3.qxtWebbansach.entity.Product;
import k23cnt3.qxtWebbansach.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // =============================
    // Lấy tất cả sản phẩm
    // =============================
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // =============================
    // Lấy sản phẩm theo id
    // =============================
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // =============================
    // Lưu hoặc cập nhật sản phẩm
    // =============================
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // =============================
    // Xóa sản phẩm theo id
    // =============================
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    // =============================
    // Lấy sản phẩm nổi bật
    // =============================
    public List<Product> findFeaturedProducts() {
        return productRepository.findByFeaturedTrue();
    }

    // =============================
    // Tìm kiếm sản phẩm theo từ khóa
    // =============================
    public List<Product> search(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    // =============================
    // Lọc sản phẩm theo danh mục
    // =============================
    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // =============================
    // Lấy top 4 sản phẩm liên quan
    // =============================
    public List<Product> findRelatedProducts(Product product) {
        if (product.getCategory() == null) return List.of();
        return productRepository.findTop4ByCategoryAndIdNot(product.getCategory(), product.getId());
    }

    // =============================
    // Khuyến mãi & Hàng mới
    // =============================
    public List<Product> findPromotionProducts() {
        return productRepository.findByDiscountGreaterThan(0.0);
    }

    public List<Product> findNewProducts() {
        return productRepository.findByIsNewTrue();
    }

    public List<Product> findTopNewProducts() {
        return productRepository.findTop4ByOrderByCreatedDateDesc();
    }
}
