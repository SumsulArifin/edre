package com.tkgroupbd.pusti.api.services.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.PurchaseOrdersRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
@Component
public interface PurchaseOrdersService {
    public List<PurchaseOrders> getAllOrders();

    public PurchaseOrders findOrdersById(int ordersId);

    PurchaseOrders saveOrder(PurchaseOrdersRequest orderRequest);

    public Optional<PurchaseOrders> updateOrders(int ordersId, PurchaseOrdersRequest orderRequest);

    public Optional<PurchaseOrders> updateOrdersStatus(int ordersId, PurchaseOrdersRequest orderRequest);

    Page<PurchaseOrders> findPurchaseOrdersByPagination(int offset, int pageSize);
}
