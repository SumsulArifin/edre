package com.tkgroupbd.pusti.api.services.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Category;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.CategoryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.products.CategoryRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public MessageResponse createCategory(CategoryRequest categoryRequest) {
        try {
            Category newCategory = new Category();
            newCategory.setName(categoryRequest.getName());
            newCategory.setCategoryType(categoryRequest.getCategoryType());
            newCategory.setDescription(categoryRequest.getDescription());
            newCategory.setStatus(categoryRequest.isStatus());
            categoryRepository.save(newCategory);
            return new MessageResponse(Message.SUCCESS_CATEGORY_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CATEGORY_CREATION + e.getMessage());
        }
    }

    @Override
    public Optional<Category> updateCategory(Integer id, CategoryRequest categoryRequest) {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            Category category = result.get();

            category.setName(categoryRequest.getName());
            category.setCategoryType(categoryRequest.getCategoryType());
            category.setDescription(categoryRequest.getDescription());
            category.setStatus(categoryRequest.isStatus());
            categoryRepository.save(category);
        } else {
            throw new ResourceNotFoundException("Category", "id", id);
        }

        return result;
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    @Override
    public List<Category> getCategoryByCategoryTypeId(int categoryTypeId) {
        return categoryRepository.findCategoryByCategoryTypeId(categoryTypeId);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> categoryStatusChange(Integer id, CategoryRequest categoryRequest) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("Category", "id", id);
        } else
            category.get().setStatus(categoryRequest.isStatus());
        categoryRepository.save(category.get());
        return category;
    }

    public Page<Category> getCategoryByPagination(int offset, int pageSize) {
        Page<Category> categories = categoryRepository.findAll(PageRequest.of(offset, pageSize));
        return categories;
    }
}
