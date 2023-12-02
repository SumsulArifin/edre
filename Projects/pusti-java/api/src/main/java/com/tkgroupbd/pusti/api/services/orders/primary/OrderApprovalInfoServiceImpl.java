package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.OrderApprovalInfo;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.OrderApprovalInfoRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.OrderApprovalInfoRepository;
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
public class OrderApprovalInfoServiceImpl implements OrderApprovalInfoService {

    @Autowired
    OrderApprovalInfoRepository repository;

    @Override
    public MessageResponse createOrderApprovalInfo(OrderApprovalInfoRequest request) {

        OrderApprovalInfo orderApprovalInfo = new OrderApprovalInfo();

        orderApprovalInfo.setComments(request.getComments());
        orderApprovalInfo.setCurrentStatus(request.isCurrentStatus());
        orderApprovalInfo.setCreatedBy(request.getCreatedBy());
        orderApprovalInfo.setUpdatedBy(request.getUpdatedBy());
        orderApprovalInfo.setCreatedAt(request.getCreatedAt());
        orderApprovalInfo.setUpdatedAt(request.getUpdatedAt());
        orderApprovalInfo.setDeletedAt(request.getDeletedAt());
        orderApprovalInfo.setOrders(request.getOrders());
        orderApprovalInfo.setEmployee(request.getEmployee());

        repository.save(orderApprovalInfo);

        return new MessageResponse(Message.SUCCESS_ORDER_APPROVAL_INFO_CREATION);
    }

    @Override
    public Optional<OrderApprovalInfo> updateOrderApprovalInfo(int orderApprovalId, OrderApprovalInfoRequest request) {
        Optional<OrderApprovalInfo> result = repository.findById(orderApprovalId);

        if (result.isPresent()) {
            OrderApprovalInfo orderApprovalInfo = result.get();

            orderApprovalInfo.setComments(request.getComments());
            orderApprovalInfo.setCurrentStatus(request.isCurrentStatus());
            orderApprovalInfo.setCreatedBy(request.getCreatedBy());
            orderApprovalInfo.setUpdatedBy(request.getUpdatedBy());
            orderApprovalInfo.setCreatedAt(request.getCreatedAt());
            orderApprovalInfo.setUpdatedAt(request.getUpdatedAt());
            orderApprovalInfo.setDeletedAt(request.getDeletedAt());
            orderApprovalInfo.setOrders(request.getOrders());
            orderApprovalInfo.setEmployee(request.getEmployee());

            repository.save(orderApprovalInfo);

        } else {
            throw new ResourceNotFoundException("OrderApprovalInfo", "orderApprovalId", orderApprovalId);
        }

        return result;
    }

    @Override
    public void deleteOrderApprovalInfoById(int orderApprovalId) {
        repository.deleteById(orderApprovalId);
    }

    @Override
    public Optional<OrderApprovalInfo> updateOrderApprovalInfoStatus(int orderApprovalId,
            OrderApprovalInfoRequest request) {
        Optional<OrderApprovalInfo> result = repository.findById(orderApprovalId);

        if (result.isPresent()) {
            OrderApprovalInfo orderApprovalInfo = result.get();

            orderApprovalInfo.setCurrentStatus(request.isCurrentStatus());
            repository.save(orderApprovalInfo);

        } else {
            throw new ResourceNotFoundException("OrderApprovalInfo", "orderApprovalId", orderApprovalId);
        }

        return result;
    }

    @Override
    public List<OrderApprovalInfo> getAllOrderApprovalInfo() {
        return repository.findAll();
    }

    @Override
    public OrderApprovalInfo findOrderApprovalInfoById(int orderApprovalId) {
        return repository.findById(orderApprovalId)
                .orElseThrow(() -> new ResourceNotFoundException("DistributorReceivedProduct", "orderApprovalId",
                        orderApprovalId));
    }

    @Override
    public Page<OrderApprovalInfo> findOrderApprovalInfoByPagination(int offset, int pageSize) {
        Page<OrderApprovalInfo> approvalInfos = repository.findAll(PageRequest.of(offset, pageSize));
        return approvalInfos;
    }

    @Override
    public Page<OrderApprovalInfo> findSortedOrderApprovalInfoByPagination(int offset, int pageSize, String field) {
        Page<OrderApprovalInfo> approvalInfos = repository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return approvalInfos;
    }
}
