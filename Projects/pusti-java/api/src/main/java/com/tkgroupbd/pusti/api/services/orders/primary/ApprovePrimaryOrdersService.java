package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ApprovePrimaryOrders;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.ApprovePrimaryOrdersRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface ApprovePrimaryOrdersService {
    MessageResponse createApprovePrimaryOrders(ApprovePrimaryOrdersRequest request);

    public List<ApprovePrimaryOrders> getAllApprovePrimaryOrders();

    public ApprovePrimaryOrders getApprovePrimaryOrdersById(int id);

    public List<ApprovePrimaryOrders> getApprovePrimaryOrdersByPrimaryApproveId(int id);

    public List<Object[]> getApprovePrimaryOrdersByCurrentDate(LocalDate startDate, LocalDate endDate);
}
