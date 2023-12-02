package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.OrderApprovalInfo;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.OrderApprovalInfoRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderApprovalInfoService {
    MessageResponse createOrderApprovalInfo(OrderApprovalInfoRequest request);

    public Optional<OrderApprovalInfo> updateOrderApprovalInfo(int orderApprovalId, OrderApprovalInfoRequest request);

    public void deleteOrderApprovalInfoById(int orderApprovalId);

    public Optional<OrderApprovalInfo> updateOrderApprovalInfoStatus(int orderApprovalId,
            OrderApprovalInfoRequest request);

    public List<OrderApprovalInfo> getAllOrderApprovalInfo();

    public OrderApprovalInfo findOrderApprovalInfoById(int orderApprovalId);

    Page<OrderApprovalInfo> findOrderApprovalInfoByPagination(int offset, int pageSize);

    Page<OrderApprovalInfo> findSortedOrderApprovalInfoByPagination(int offset, int pageSize, String field);

}
