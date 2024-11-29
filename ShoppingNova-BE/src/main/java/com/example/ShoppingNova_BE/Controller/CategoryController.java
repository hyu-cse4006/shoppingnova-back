package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.Category.Category;
import com.example.ShoppingNova_BE.Entity.Category.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 모든 카테고리 조회
    @GetMapping("/all")
    public List<Category> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();  // 모든 카테고리 정보 조회
        if (categories.isEmpty()) {
            throw new RuntimeException("No categories found.");
        }
        return categories;
    }

    // 특정 카테고리 조회
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        return category;
    }

    // 현재 카테고리 id의 자식 카테고리 id들 리턴하기
    @GetMapping("/child/{id}")
    public List<Category> getCategoryByChildId(@PathVariable int id) {
        List<Category> childCategories = categoryService.getCategoriesByParentId(id);
        if (childCategories.isEmpty()) {
            throw new RuntimeException("No child categories found for parent ID: " + id);
        }
        return childCategories;
    }
}

