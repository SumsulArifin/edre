package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOfficer;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.sales.SalesOfficerRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface SalesOfficerService {
    MessageResponse createSalesOfficer(SalesOfficerRequest salesOfficerRequest);

    public List<SalesOfficer> getAllSalesOfficers();

    public SalesOfficer findSalesOfficerById(int salesOfficerId);

    public Optional<SalesOfficer> updateSalesOfficer(int salesOfficerId, SalesOfficerRequest salesOfficerequest);

    public void deleteSalesOfficerById(int salesOfficerId);

    public Optional<SalesOfficer> updateSalesOfficerStatus(int salesOfficerId, SalesOfficerRequest salesOfficerequest);

    public Page<SalesOfficer> findSalesOfficersByPagination(int offset, int pageSize);
}
