package com.tkgroupbd.pusti.api.services.orders.primary;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.ApprovePrimaryOrders;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.ApprovePrimaryOrdersRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.ApprovePrimaryOrderRepository;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApprovePrimaryOrdersServiceImpl implements ApprovePrimaryOrdersService {

    @Autowired
    ApprovePrimaryOrderRepository approvePrimaryOrderRepository;

    @Override
    public MessageResponse createApprovePrimaryOrders(ApprovePrimaryOrdersRequest request) {
        ApprovePrimaryOrders approvePrimaryOrders = new ApprovePrimaryOrders();
        approvePrimaryOrders.setPrimaryOrderApproval(request.getPrimaryOrderApproval());
        approvePrimaryOrders.setComments(request.getComments());
        approvePrimaryOrderRepository.save(approvePrimaryOrders);

        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public List<ApprovePrimaryOrders> getAllApprovePrimaryOrders() {
        return approvePrimaryOrderRepository.findAll();
    }

    @Override
    public ApprovePrimaryOrders getApprovePrimaryOrdersById(int id) {
        return approvePrimaryOrderRepository.findById(id).get();
    }

    @Override
    public List<ApprovePrimaryOrders> getApprovePrimaryOrdersByPrimaryApproveId(int id) {
        return approvePrimaryOrderRepository.findApprovePrimaryOrdersByPrimaryOrderApprovalId(id);
    }

    @Override
    public List<Object[]> getApprovePrimaryOrdersByCurrentDate(LocalDate startDate, LocalDate endDate) {
        return approvePrimaryOrderRepository.findApprovePrimaryOrdersByCurrentDate(startDate, endDate);
    }
}
