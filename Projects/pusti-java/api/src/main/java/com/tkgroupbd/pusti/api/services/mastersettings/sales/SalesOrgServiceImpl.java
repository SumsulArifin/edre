package com.tkgroupbd.pusti.api.services.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOrganization;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.SalesOrganizationRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales.SalesOrganizationRepository;
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
public class SalesOrgServiceImpl implements SalesOrgService {
    @Autowired
    SalesOrganizationRepository salesOrgRepository;

    @Override
    public MessageResponse createSalesOrg(SalesOrganizationRequest salesOrgRequest) {

        try {
            SalesOrganization newSalesOrg = new SalesOrganization();

            newSalesOrg.setSalesOrgName(salesOrgRequest.getSalesOrgName());
            newSalesOrg.setStatus(salesOrgRequest.isStatus());
            newSalesOrg.setCreatedAt(salesOrgRequest.getCreatedAt());
            newSalesOrg.setDeletedAt(salesOrgRequest.getDeletedAt());
            newSalesOrg.setUpdatedAt(salesOrgRequest.getUpdatedAt());
            newSalesOrg.setBrowser(salesOrgRequest.getBrowser());
            newSalesOrg.setIp(salesOrgRequest.getIp());

            salesOrgRepository.save(newSalesOrg);

            return new MessageResponse(Message.SUCCESS_SALESORGANIZATION_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_SALESORGANIZATION_CREATION);
        }

    }

    @Override
    public Optional<SalesOrganization> updateSalesOrg(Integer id, SalesOrganizationRequest salesOrgRequest) {

        Optional<SalesOrganization> result = salesOrgRepository.findById(id);

        if (result.isPresent()) {
            SalesOrganization salesOrg = result.get();

            salesOrg.setSalesOrgName(salesOrgRequest.getSalesOrgName());
            salesOrg.setStatus(salesOrgRequest.isStatus());
            salesOrg.setCreatedAt(salesOrgRequest.getCreatedAt());
            salesOrg.setDeletedAt(salesOrgRequest.getDeletedAt());
            salesOrg.setUpdatedAt(salesOrgRequest.getUpdatedAt());
            salesOrg.setBrowser(salesOrgRequest.getBrowser());
            salesOrg.setIp(salesOrgRequest.getIp());
            salesOrgRepository.save(salesOrg);
        } else {
            throw new ResourceNotFoundException("SalesOrganization", "id", id);
        }
        return result;
    }

    @Override
    public void deleteSalesOrg(Integer id) {
        salesOrgRepository.deleteById(id);
    }

    @Override
    public SalesOrganization getSalesOrgById(Integer id) {
        return salesOrgRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales Organization", "id", id));
    }

    @Override
    public List<SalesOrganization> getAllSalesOrg() {
        return salesOrgRepository.findAll();
    }

    @Override
    public List<SalesOrganization> findSalesOrgBySalesOrgName(String name) {
        List<SalesOrganization> salesOrganizations = salesOrgRepository.findBySalesOrgNameContaining(name);
        return salesOrganizations;
    }

    @Override
    public Optional<SalesOrganization> SalesOrgStatusChange(Integer id, SalesOrganizationRequest salesOrgRequest) {
        Optional<SalesOrganization> salesOrganization = salesOrgRepository.findById(id);
        if (salesOrganization.isEmpty()) {
            throw new ResourceNotFoundException("salesOrganization", "id", id);
        } else
            salesOrganization.get().setStatus(salesOrgRequest.isStatus());
        salesOrgRepository.save(salesOrganization.get());
        return salesOrganization;

    }

    @Override
    public List<SalesOrganization> findSortedSalesOrgByKey(String field) {
        return salesOrgRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<SalesOrganization> findSalesOrgByPagination(int offset, int pageSize) {
        Page<SalesOrganization> salesOrganizations = salesOrgRepository.findAll(PageRequest.of(offset, pageSize));
        return salesOrganizations;
    }

    @Override
    public Page<SalesOrganization> findSortedSalesOrgByPagination(int offset, int pageSize, String field) {
        Page<SalesOrganization> salesOrganizations = salesOrgRepository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return salesOrganizations;
    }

}
