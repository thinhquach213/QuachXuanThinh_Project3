package project3.service;

import k23cnt3.qxtWebbansach.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}
