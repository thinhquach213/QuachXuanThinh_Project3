package k23cnt3.qxtWebbansach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import k23cnt3.qxtWebbansach.entity.Category;
import k23cnt3.qxtWebbansach.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // =============================
    // Lấy tất cả danh mục
    // =============================
    public List<Category> findAll() {
        return categoryRepository.findAllWithProducts();
    }

    // =============================
    // Lấy danh mục theo id
    // =============================
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // =============================
    // Lưu hoặc cập nhật danh mục
    // =============================
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    // =============================
    // Xóa danh mục theo id
    // =============================
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
