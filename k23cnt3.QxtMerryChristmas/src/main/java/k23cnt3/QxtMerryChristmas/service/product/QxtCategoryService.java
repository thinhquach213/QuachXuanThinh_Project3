package k23cnt3.QxtMerryChristmas.service.product;

import k23cnt3.QxtMerryChristmas.entity.product.QxtCategory;
import k23cnt3.QxtMerryChristmas.repository.product.QxtCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QxtCategoryService {

    private final QxtCategoryRepository categoryRepository;

    public List<QxtCategory> getAll() {
        return categoryRepository.findAll();
    }

    public QxtCategory getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}