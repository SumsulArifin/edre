package com.tkgroupbd.pusti.api.controllers.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Category;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.CategoryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.products.CategoryService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Category")
@RestController
@RequestMapping("/category")
@CacheConfig(cacheNames = "category")
public class CategoryController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/addNewCategory")
    public ResponseEntity<MessageResponse> addCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        MessageResponse newCategory = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCategoryById/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getByCategoryId/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/getByCategoryTypeId/{categoryTypeId}")
    public ResponseEntity<List<Category>> getCategoryByCategoryTypeId(
            @PathVariable("categoryTypeId") Integer categoryTypeId) {
        List<Category> category = categoryService.getCategoryByCategoryTypeId(categoryTypeId);
        return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Optional<Category>> updateCategory(@PathVariable Integer id,
            @RequestBody CategoryRequest categoryRequest) {
        Optional<Category> updateCategory = categoryService.updateCategory(id, categoryRequest);
        return new ResponseEntity<Optional<Category>>(updateCategory, HttpStatus.OK);
    }

    @PutMapping("/updateCategoryStatus/{id}")
    public ResponseEntity<Optional<Category>> updateCategoryStatus(@PathVariable Integer id,
            @RequestBody CategoryRequest categoryRequest) {
        Optional<Category> updateCategory = categoryService.categoryStatusChange(id, categoryRequest);
        return new ResponseEntity<Optional<Category>>(updateCategory, HttpStatus.OK);
    }

    @GetMapping("/getPaginatedCategory")
    private Page<Category> getPaginatedCategory(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Category>  paginatedCategory= categoryService.getCategoryByPagination(offset, pageSize);
        return paginatedCategory;
    }



}
