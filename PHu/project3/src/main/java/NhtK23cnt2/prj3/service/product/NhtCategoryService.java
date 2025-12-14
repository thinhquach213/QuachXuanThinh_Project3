package NhtK23cnt2.prj3.service.product;

import NhtK23cnt2.prj3.entity.product.NhtCategory;
import NhtK23cnt2.prj3.repository.product.NhtCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhtCategoryService {

    private final NhtCategoryRepository categoryRepository;

    public List<NhtCategory> getAll() {
        return categoryRepository.findAll();
    }

    public NhtCategory getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
