package com.example.ShoppingNova_BE.Entity.Category;

import com.example.ShoppingNova_BE.S3.S3Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final S3Service s3Service;
    private final ObjectMapper objectMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, S3Service s3Service,
            ObjectMapper objectMapper) {
        this.categoryRepository = categoryRepository;
        this.s3Service = s3Service;
        this.objectMapper = objectMapper;
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

    public List<Category> getCategoriesByParentId(int parentId) {
        return categoryRepository.findByParentId(parentId);
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

    public void saveCategoryJson(String fileName) {
        try {
            // S3에서 JSON 파일 읽기
            String jsonData = s3Service.readJsonFile(fileName);

            // JSON 데이터를 List<Category>로 변환
            List<Category> categories = objectMapper.readValue(jsonData, new TypeReference<List<Category>>() {});

            // DB에 저장
            categoryRepository.saveAll(categories);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while saving category data from file: " + fileName, e);
        }
    }
}
