package com.tkgroupbd.pusti.api.services.mastersettings.brands;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.brands.Brand;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.brands.BrandDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.brands.BrandRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public MessageResponse addBrand(BrandDTO brandRequest) {
        Brand newBrand = new Brand();
        newBrand.setBrandName(brandRequest.getBrandName());
        newBrand.setBrandType(brandRequest.getBrandType());
        newBrand.setStatus(brandRequest.isStatus());
        brandRepository.save(newBrand);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<Brand> updateBrand(Integer brandId, BrandDTO brandRequest) {
        Optional<Brand> result = brandRepository.findById(brandId);
        if (result.isPresent()) {
            Brand brand = result.get();
            brand.setBrandName(brandRequest.getBrandName());
            brand.setBrandType(brandRequest.getBrandType());
            brand.setStatus(brandRequest.isStatus());
            brandRepository.save(brand);
        } else {
            throw new ResourceNotFoundException("Brand", "brandId", brandId);
        }

        return result;
    }

    @Override
    public Optional<Brand> brandStatusChangeAPI(Integer brandId, BrandDTO brandRequest) {
        Optional<Brand> brand = brandRepository.findById(brandId);
        if (brand.isEmpty()) {
            throw new ResourceNotFoundException("Brand", "brandId", brandId);
        } else
            brand.get().setStatus(brandRequest.isStatus());
        brandRepository.save(brand.get());
        return brand;
    }

    @Override
    public void deleteBrand(Integer brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public Brand getBrandById(int brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "brandId", brandId));
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> findSortedBrandByKey(String field) {
        return brandRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Brand> findBrandByPagination(int offset, int pageSize) {
        Page<Brand> brands = brandRepository.findAll(PageRequest.of(offset, pageSize));
        return brands;
    }

    @Override
    public Page<Brand> findSortedBrandByPagination(int offset, int pageSize, String field) {
        Page<Brand> brands = brandRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return brands;
    }

}
