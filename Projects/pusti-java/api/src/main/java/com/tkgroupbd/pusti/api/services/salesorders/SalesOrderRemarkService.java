package com.tkgroupbd.pusti.api.services.salesorders;

import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderRemark;
import com.tkgroupbd.pusti.api.data.payloads.dto.salesOrder.SalesOrderRemarkRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SalesOrderRemarkService {
    MessageResponse createSalesOrderRemark(SalesOrderRemarkRequest request);

    Optional<SalesOrderRemark> updateSalesOrderRemark(Integer id, SalesOrderRemarkRequest request);

    void deleteSalesOrderRemark(Integer id);

    SalesOrderRemark getSalesOrderRemarkById(Integer id);

    List<SalesOrderRemark> getAllSalesOrderRemark();

    Optional<SalesOrderRemark> salesOrderRemarkStatusChange(Integer id, SalesOrderRemarkRequest request);
}
