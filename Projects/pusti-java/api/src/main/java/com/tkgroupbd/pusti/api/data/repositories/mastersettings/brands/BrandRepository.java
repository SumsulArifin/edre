package com.tkgroupbd.pusti.api.data.repositories.mastersettings.brands;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.brands.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
