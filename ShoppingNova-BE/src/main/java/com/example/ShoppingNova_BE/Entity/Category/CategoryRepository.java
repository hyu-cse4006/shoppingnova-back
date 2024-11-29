package com.example.ShoppingNova_BE.Entity.Category;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // parentCategoryId가 특정 ID인 Category 목록 조회
    List<Category> findByParentId(Integer parentId);
}
