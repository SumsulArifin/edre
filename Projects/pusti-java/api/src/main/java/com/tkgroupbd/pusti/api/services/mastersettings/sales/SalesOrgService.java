package com.tkgroupbd.pusti.api.services.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOrganization;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.SalesOrganizationRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface SalesOrgService {
    MessageResponse createSalesOrg(SalesOrganizationRequest salesOrgRequest);

    Optional<SalesOrganization> updateSalesOrg(Integer id, SalesOrganizationRequest salesOrgRequest);

    void deleteSalesOrg(Integer id);

    SalesOrganization getSalesOrgById(Integer id);

    List<SalesOrganization> getAllSalesOrg();

    List<SalesOrganization> findSalesOrgBySalesOrgName(String name);

    Optional<SalesOrganization> SalesOrgStatusChange(Integer id, SalesOrganizationRequest salesOrgRequest);

    List<SalesOrganization> findSortedSalesOrgByKey(String field);

    Page<SalesOrganization> findSalesOrgByPagination(int offset, int pageSize);

    Page<SalesOrganization> findSortedSalesOrgByPagination(int offset, int pageSize, String field);

}
