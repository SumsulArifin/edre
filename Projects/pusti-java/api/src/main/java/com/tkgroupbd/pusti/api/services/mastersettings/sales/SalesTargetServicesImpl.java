package com.tkgroupbd.pusti.api.services.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesTarget;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.SalesTargetRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales.SalesTargetRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalesTargetServicesImpl implements SalesTargetServices {
    @Autowired
    SalesTargetRepository salesTargetRepository;

    @Override
    public MessageResponse addSalesTarget(SalesTargetRequest sales_targetRequest) {
        SalesTarget salesTarget = new SalesTarget();
        salesTarget.setTargetType(sales_targetRequest.getTargetType());
        salesTarget.setAudienceType(sales_targetRequest.getAudienceType());
        salesTarget.setAudienceId(sales_targetRequest.getAudienceId());
        salesTarget.setYearmonth(sales_targetRequest.getYearmonth());
        salesTarget.setQuantity(sales_targetRequest.getQuantity());
        salesTarget.setTp(sales_targetRequest.getTp());
        salesTarget.setDp(sales_targetRequest.getDp());
        salesTarget.setDealerId(sales_targetRequest.getDealerId());
        salesTarget.setTsoId(sales_targetRequest.getTsoId());
        salesTarget.setRsmId(sales_targetRequest.getRsmId());
        salesTarget.setAsmId(sales_targetRequest.getAsmId());
        salesTarget.setCreatedBy(sales_targetRequest.getCreatedBy());
        salesTarget.setRegion(sales_targetRequest.getRegion());
        salesTarget.setProductItem(sales_targetRequest.getProductItem());
        salesTarget.setAssignedSalesOfficer(sales_targetRequest.getAssignedSalesOfficer());
        salesTarget.setCreatedAt(sales_targetRequest.getCreatedAt());
        salesTarget.setCreatedBy(sales_targetRequest.getCreatedBy());
        salesTarget.setUpdatedAt(sales_targetRequest.getUpdatedAt());
        salesTarget.setUpdatedBy(sales_targetRequest.getUpdatedBy());
        salesTarget.setDeletedAt(sales_targetRequest.getDeletedAt());
        salesTarget.setDeletedBy(sales_targetRequest.getDeletedBy());
        salesTarget.setIp(sales_targetRequest.getIp());
        salesTarget.setBrowser(sales_targetRequest.getBrowser());

        salesTargetRepository.save(salesTarget);
        return new MessageResponse(Message.SUCCESS_DEPOT_CREATION);
    }

    @Override
    public Optional<SalesTarget> updateSalesTarget(Integer id, SalesTargetRequest sales_targetRequest) {
        Optional<SalesTarget> result = salesTargetRepository.findById(id);

        if (result.isPresent()) {
            SalesTarget salesTarget = result.get();
            salesTarget.setTargetType(sales_targetRequest.getTargetType());
            salesTarget.setAudienceType(sales_targetRequest.getAudienceType());
            salesTarget.setAudienceId(sales_targetRequest.getAudienceId());
            salesTarget.setYearmonth(sales_targetRequest.getYearmonth());
            salesTarget.setProductItem(sales_targetRequest.getProductItem());
            salesTarget.setQuantity(sales_targetRequest.getQuantity());
            salesTarget.setTp(sales_targetRequest.getTp());
            salesTarget.setDp(sales_targetRequest.getDp());
            salesTarget.setDealerId(sales_targetRequest.getDealerId());
            salesTarget.setTsoId(sales_targetRequest.getTsoId());
            salesTarget.setRsmId(sales_targetRequest.getRsmId());
            salesTarget.setAsmId(sales_targetRequest.getAsmId());
            salesTarget.setCreatedBy(sales_targetRequest.getCreatedBy());
            salesTarget.setRegion(sales_targetRequest.getRegion());
            salesTarget.setProductItem(sales_targetRequest.getProductItem());
            salesTarget.setAssignedSalesOfficer(sales_targetRequest.getAssignedSalesOfficer());
            salesTarget.setCreatedAt(sales_targetRequest.getCreatedAt());
            salesTarget.setCreatedBy(sales_targetRequest.getCreatedBy());
            salesTarget.setUpdatedAt(sales_targetRequest.getUpdatedAt());
            salesTarget.setUpdatedBy(sales_targetRequest.getUpdatedBy());
            salesTarget.setDeletedAt(sales_targetRequest.getDeletedAt());
            salesTarget.setDeletedBy(sales_targetRequest.getDeletedBy());
            salesTarget.setIp(sales_targetRequest.getIp());
            salesTarget.setBrowser(sales_targetRequest.getBrowser());

            salesTargetRepository.save(salesTarget);
        } else {
            throw new ResourceNotFoundException("salesTarget", "id", id);
        }

        return result;
    }

    @Override
    public Optional<SalesTarget> statusChangeAPI(Integer id, SalesTargetRequest sales_targetRequest) {
        Optional<SalesTarget> result = salesTargetRepository.findById(id);
        if (result.isPresent()) {
            SalesTarget salesTarget = result.get();
            salesTarget.setStatus(sales_targetRequest.isStatus());
            salesTargetRepository.save(salesTarget);
        } else {
            throw new ResourceNotFoundException("salesTarget", "id", id);
        }

        return result;
    }

    @Override
    public void deleteSalesTarget(Integer id) {
        salesTargetRepository.deleteById(id);

    }

    @Override
    public SalesTarget getSalesTargetById(Integer id) {
        return salesTargetRepository.findById(id).get();
    }

    @Override
    public List<SalesTarget> getAllSalesTarget() {
        return salesTargetRepository.findAll();
    }

    @Override
    public List<SalesTarget> findSortedSalesTargetByKey(String field) {
        return salesTargetRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<SalesTarget> findSalesTargetByPagination(int offset, int pageSize) {
        Page<SalesTarget> salesTargets = salesTargetRepository.findAll(PageRequest.of(offset, pageSize));
        return salesTargets;
    }

    @Override
    public Page<SalesTarget> findSortedSalesTargetByPagination(int offset, int pageSize, String field) {
        Page<SalesTarget> salesTargets = salesTargetRepository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return salesTargets;
    }

    @Override
    public List<SalesTarget> salesOrderDetailsBySku(String sku, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return salesTargetRepository.findTargetSalesBySkuAndCreatedAtBetween(sku, startDate, endDate);
    }

    @Override
    public List<SalesTarget> salesOrderDetailsBySku(String sku) {
        String sku1 = sku.trim().toLowerCase();
        System.out.println("Input SKU: " + sku);
        System.out.println("Processed SKU: " + sku1);
        return salesTargetRepository.findTargetSalesBySku(sku1);
    }

    @Override
    public List<SalesTarget> getKPIByRouteId(int routeId) {
        return salesTargetRepository.findSalesTargetByAndAssignedSalesOfficer_Route_RouteId(routeId);
    }
}
