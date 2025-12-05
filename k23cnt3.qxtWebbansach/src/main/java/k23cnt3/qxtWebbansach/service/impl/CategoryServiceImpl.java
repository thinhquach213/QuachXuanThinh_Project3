package k23cnt3.qxtWebbansach.service.impl;

import k23cnt3.qxtWebbansach.model.Category;
import k23cnt3.qxtWebbansach.repository.CategoryRepository;
import k23cnt3.qxtWebbansach.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());
            return categoryRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
