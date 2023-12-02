package com.tkgroupbd.pusti.api.services.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.CategoryType;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.CategoryTypeRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.products.CategoryTypeRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryTypeServiceImpl implements CategoryTypeService {

    @Autowired
    CategoryTypeRepository categoryTypeRepository;

    @Override
    public MessageResponse createCategoryType(CategoryTypeRequest categoryTypeRequest) {
        try {
            CategoryType categoryType = new CategoryType();
            categoryType.setName(categoryTypeRequest.getName().trim());
            categoryType.setDescription(categoryTypeRequest.getDescription());
            categoryType.setStatus(categoryTypeRequest.isStatus());
            categoryTypeRepository.save(categoryType);

            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CATEGORY_CREATION + e.getMessage());
        }

    }

    @Override
    public Optional<CategoryType> updateCategoryType(Integer id, CategoryTypeRequest categoryTypeRequest) {
        Optional<CategoryType> result = categoryTypeRepository.findById(id);
        if (result.isPresent()) {
            CategoryType categoryType = result.get();

            categoryType.setName(categoryTypeRequest.getName());
            categoryType.setDescription(categoryTypeRequest.getDescription());
            categoryType.setStatus(categoryTypeRequest.isStatus());

            categoryTypeRepository.save(categoryType);
        } else {
            throw new ResourceNotFoundException("CategoryType", "id", id);
        }

        return result;
    }

    @Override
    public void deleteCategoryType(Integer id) {
        categoryTypeRepository.deleteById(id);
    }

    @Override
    public CategoryType getCategoryTypeById(Integer id) {
        return categoryTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryType", "id", id));
    }

    @Override
    public List<CategoryType> getAllCategoryTypes() {
        return categoryTypeRepository.findAll();
    }

    @Override
    public Optional<CategoryType> categoryTypeStatusChange(Integer id, CategoryTypeRequest categoryTypeRequest) {
        Optional<CategoryType> categoryType = categoryTypeRepository.findById(id);
        if (categoryType.isEmpty()) {
            throw new ResourceNotFoundException("CategoryType", "id", id);
        } else
            categoryType.get().setStatus(categoryTypeRequest.isStatus());
        categoryTypeRepository.save(categoryType.get());
        return categoryType;
    }

    @Override
    public Page<CategoryType> findCategoryTypeByPagination(int offset, int pageSize) {
        Page<CategoryType> categoryTypes = categoryTypeRepository.findAll(PageRequest.of(offset, pageSize));
        return categoryTypes;
    }
}
