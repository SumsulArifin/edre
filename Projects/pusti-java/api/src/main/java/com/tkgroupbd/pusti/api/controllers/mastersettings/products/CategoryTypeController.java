package com.tkgroupbd.pusti.api.controllers.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.CategoryType;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.CategoryTypeRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.products.CategoryTypeService;
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

@Tag(name = "Category Type")
@RestController
@RequestMapping("/categoryType")
@CacheConfig(cacheNames = "categoryType")
public class CategoryTypeController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    CategoryTypeService categoryTypeService;

    @GetMapping("/getAllCategoryTypes")
    public ResponseEntity<List<CategoryType>> getAllCategoryTypes() {
        List<CategoryType> categories = categoryTypeService.getAllCategoryTypes();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/addNewCategoryType")
    public ResponseEntity<MessageResponse> addCategoryType(
            @RequestBody @Valid CategoryTypeRequest categoryTypeRequest) {
        MessageResponse newCategoryType = categoryTypeService.createCategoryType(categoryTypeRequest);
        return new ResponseEntity<>(newCategoryType, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBy/{id}")
    public ResponseEntity<?> deleteCategoryType(@PathVariable("id") Integer id) {
        categoryTypeService.deleteCategoryType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getCategoryTypeById/{id}")
    public ResponseEntity<CategoryType> getCategoryTypeById(@PathVariable("id") Integer id) {
        CategoryType categoryType = categoryTypeService.getCategoryTypeById(id);
        return new ResponseEntity<>(categoryType, HttpStatus.OK);
    }

    @PutMapping("/updateCategoryType/{id}")
    public ResponseEntity<Optional<CategoryType>> updateCategoryType(@PathVariable Integer id,
            @RequestBody CategoryTypeRequest categoryTypeRequest) {
        Optional<CategoryType> updateCategoryType = categoryTypeService.updateCategoryType(id, categoryTypeRequest);
        return new ResponseEntity<Optional<CategoryType>>(updateCategoryType, HttpStatus.OK);
    }

    @PutMapping("/updateCategoryTypeStatus/{id}")
    public ResponseEntity<Optional<CategoryType>> updateCategoryTypeStatus(@PathVariable Integer id,
            @RequestBody CategoryTypeRequest categoryTypeRequest) {
        Optional<CategoryType> updateCategoryType = categoryTypeService.categoryTypeStatusChange(id,
                categoryTypeRequest);
        return new ResponseEntity<Optional<CategoryType>>(updateCategoryType, HttpStatus.OK);
    }

    @GetMapping("/getPaginatedCategoryType")
    private Page<CategoryType> getPaginatedBrands(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<CategoryType> paginatedBrands = categoryTypeService.findCategoryTypeByPagination(offset, pageSize);
        return paginatedBrands;
    }

}
