package com.tkgroupbd.pusti.api.services.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.CategoryType;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.CategoryTypeRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface CategoryTypeService {

    MessageResponse createCategoryType(CategoryTypeRequest categoryTypeRequest);

    Optional<CategoryType> updateCategoryType(Integer id, CategoryTypeRequest categoryTypeRequest);

    void deleteCategoryType(Integer id);

    CategoryType getCategoryTypeById(Integer id);

    List<CategoryType> getAllCategoryTypes();

    Optional<CategoryType> categoryTypeStatusChange(Integer id, CategoryTypeRequest categoryTypeRequest);

    Page<CategoryType> findCategoryTypeByPagination(int offset, int pageSize);
}
