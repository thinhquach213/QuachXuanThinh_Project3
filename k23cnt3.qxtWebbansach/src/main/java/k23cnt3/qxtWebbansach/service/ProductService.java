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
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    // =============================
    public List<Product> findFeaturedProducts() {
        return productRepository.findByFeaturedTrue();
    }

    public List<Product> search(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> findRelatedProducts(Product product) {
        if (product.getCategory() == null) return List.of();
        return productRepository.findTop4ByCategoryAndIdNot(
                product.getCategory(),
                product.getId()
        );
    }

    // =============================
    public List<Product> findPromotionProducts() {
        return productRepository.findByDiscountGreaterThan(0.0);
    }

    public List<Product> findNewProducts() {
        return productRepository.findByNewProductTrue();
    }

    public List<Product> findTopNewProducts() {
        return productRepository.findTop4ByOrderByCreatedDateDesc();
    }
}
