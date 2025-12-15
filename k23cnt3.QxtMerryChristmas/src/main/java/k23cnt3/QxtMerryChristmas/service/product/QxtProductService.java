package k23cnt3.QxtMerryChristmas.service.product;


import k23cnt3.QxtMerryChristmas.entity.product.QxtProduct;
import k23cnt3.QxtMerryChristmas.repository.product.QxtProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QxtProductService {

    private final QxtProductRepository productRepository;

    public List<QxtProduct> getAll() {
        return productRepository.findAll();
    }

    public List<QxtProduct> getByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public QxtProduct getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public List<QxtProduct> searchByKeyword(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<QxtProduct> findByTag(String tag) {
        return productRepository.findByTag(tag);
    }
}