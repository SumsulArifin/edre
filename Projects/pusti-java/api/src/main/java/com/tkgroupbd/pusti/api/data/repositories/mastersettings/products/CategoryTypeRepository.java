package com.tkgroupbd.pusti.api.data.repositories.mastersettings.products;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.CategoryType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Integer> {
}
