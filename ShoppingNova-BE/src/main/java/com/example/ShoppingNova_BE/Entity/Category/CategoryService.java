package com.example.ShoppingNova_BE.Entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 모든 카테고리 조회
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // ID로 카테고리 조회
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    // 새로운 카테고리 추가
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // 카테고리 업데이트
    public Category updateCategory(int id, Category categoryDetails) {
        Category category = getCategoryById(id);

        // 수정할 필드들을 새로운 필드명으로 설정
        category.setName(categoryDetails.getName());

        return categoryRepository.save(category);
    }

    // 카테고리 삭제
    public void deleteCategory(int id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
