package com.tkgroupbd.pusti.api.services.mastersettings.brands;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.brands.Brand;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.brands.BrandDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface BrandService {
    MessageResponse addBrand(BrandDTO brandRequest);

    Optional<Brand> updateBrand(Integer brandId, BrandDTO brandRequest);

    Optional<Brand> brandStatusChangeAPI(Integer brandId, BrandDTO brandRequest);

    void deleteBrand(Integer brandId);

    Brand getBrandById(int brandId);

    List<Brand> getAllBrand();

    List<Brand> findSortedBrandByKey(String field);

    Page<Brand> findBrandByPagination(int offset, int pageSize);

    Page<Brand> findSortedBrandByPagination(int offset, int pageSize, String field);

}
