package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.Category.Category;
import com.example.ShoppingNova_BE.Entity.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 모든 카테고리 조회
    @GetMapping("/all")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();  // 모든 카테고리 정보 조회
        if (categories.isEmpty()) {
            throw new RuntimeException("No categories found.");
        }
        model.addAttribute("categories", categories);
        return "categoryListShow";  // 카테고리 리스트를 표시할 HTML 템플릿
    }

    // 특정 카테고리 조회
    @GetMapping("/{id}")
    public String getCategory(@PathVariable int id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        model.addAttribute("category", category);
        return "testCategoryShow";  // 특정 카테고리의 상세 정보를 표시할 HTML 템플릿
    }

}

