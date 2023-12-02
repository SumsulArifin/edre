package com.tkgroupbd.pusti.api.services.salesorders;

import org.springframework.stereotype.Component;

import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderDetails;

import java.time.LocalDate;
import java.util.List;

@Component
public interface SalesOrderDetailsService {
    public List<SalesOrderDetails> salesOrderDetails();

    public List<SalesOrderDetails> salesOrderDetailsBetween(LocalDate startDate, LocalDate endDate);

    public List<SalesOrderDetails> salesOrderDetailsAllByAssignedSalesOfficerId(int id);

    public List<Object[]> getAssignedSalesOfficerOrderedMost();

    public List<Object[]> getAssignedSalesOfficerOrderedLeast();

    public List<Object[]> findSKUByRoute_ID(int routeId);

    public List<Object[]> findSKUByDistributor_id(int distributor_id);

    public List<Object[]> findCategorySalesReportByRouteId(int routeId);

    public List<SalesOrderDetails> salesOrderDetailsBySku(String sku);

    public List<SalesOrderDetails> salesOrderDetailsBySku(String sku, int year, int month);

    public List<SalesOrderDetails> salesOrderDetailsByRouteLastManualDays(int routeId);

    public List<SalesOrderDetails> getSalesOrderDetailsByCategoryIdAndDate(int categoryId, LocalDate startDate,
            LocalDate endDate);
}
