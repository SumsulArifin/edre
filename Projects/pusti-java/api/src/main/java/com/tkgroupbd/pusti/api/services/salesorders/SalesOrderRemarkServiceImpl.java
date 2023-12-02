package com.tkgroupbd.pusti.api.services.salesorders;

import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderRemark;
import com.tkgroupbd.pusti.api.data.payloads.dto.salesOrder.SalesOrderRemarkRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.salesOrder.SalesOrderRemarkRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderRemarkServiceImpl implements SalesOrderRemarkService {
    @Autowired
    private SalesOrderRemarkRepository repository;

    @Override
    public MessageResponse createSalesOrderRemark(SalesOrderRemarkRequest request) {
        try {
            SalesOrderRemark salesOrderRemark = new SalesOrderRemark();
            salesOrderRemark.setRemark(request.getRemark());
            salesOrderRemark.setCreatedBy(request.getCreatedBy());
            salesOrderRemark.setUpdatedBy(request.getUpdatedBy());
            salesOrderRemark.setDeletedBy(request.getDeletedBy());
            salesOrderRemark.setCreatedAt(request.getCreatedAt());
            repository.save(salesOrderRemark);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION);
        }
    }

    @Override
    public Optional<SalesOrderRemark> updateSalesOrderRemark(Integer id, SalesOrderRemarkRequest request) {
        Optional<SalesOrderRemark> result = repository.findById(id);
        if (result.isPresent()) {
            SalesOrderRemark salesOrderRemark = result.get();

            salesOrderRemark.setRemark(request.getRemark());
            salesOrderRemark.setCreatedBy(request.getCreatedBy());
            salesOrderRemark.setUpdatedBy(request.getUpdatedBy());
            salesOrderRemark.setDeletedBy(request.getDeletedBy());
            salesOrderRemark.setCreatedAt(request.getCreatedAt());
            repository.save(salesOrderRemark);
        } else {
            throw new ResourceNotFoundException("SalesOrderRemark", "id", id);
        }

        return result;
    }

    @Override
    public void deleteSalesOrderRemark(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("SalesOrderRemark", "id", id);
        }
    }

    @Override
    public SalesOrderRemark getSalesOrderRemarkById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sales order remark not found with id: " + id));
    }

    @Override
    public List<SalesOrderRemark> getAllSalesOrderRemark() {
        return repository.findAll();
    }

    @Override
    public Optional<SalesOrderRemark> salesOrderRemarkStatusChange(Integer id, SalesOrderRemarkRequest request) {
        Optional<SalesOrderRemark> result = repository.findById(id);
        if (result.isPresent()) {
            SalesOrderRemark salesOrderRemark = result.get();

            salesOrderRemark.setStatus(request.isStatus());
            repository.save(salesOrderRemark);
        } else {
            throw new ResourceNotFoundException("SalesOrderRemark", "id", id);
        }
        return result;
    }
}
