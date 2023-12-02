package com.tkgroupbd.pusti.api.services.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PurchaseOrderItems;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.PurchaseOrdersRequest;
import com.tkgroupbd.pusti.api.data.payloads.dto.primarysales.PurchaseOrderItemsRequest;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales.PurchaseOrdersRepository;
import com.tkgroupbd.pusti.api.data.repositories.primarysales.PurchaseOrderItemsRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrdersServiceImpl implements PurchaseOrdersService {
    @Autowired
    PurchaseOrdersRepository ordersRepository;

    @Autowired
    PurchaseOrderItemsRepository orderDetailsRepository;

    @Override
    public List<PurchaseOrders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public PurchaseOrders findOrdersById(int orderId) {
        return ordersRepository.findById(orderId).get();
    }

    @Override
    public PurchaseOrders saveOrder(PurchaseOrdersRequest orderRequest) {
        PurchaseOrders orders = new PurchaseOrders();
        orders.setName(orderRequest.getName());
        orders.setSuperDistributorId(orderRequest.getSuperDistributorId());
        orders.setOrderType(orderRequest.getOrderType());
        orders.setIsApprovalRequired(orderRequest.getIsApprovalRequired());
        orders.setApprovalStatus(orderRequest.getApprovalStatus());
        orders.setCreatedBy(orderRequest.getCreatedBy());
        orders.setUpdatedBy(orderRequest.getUpdatedBy());
        orders = ordersRepository.save(orders);

        for (PurchaseOrderItemsRequest purchaseOrderItemsRequest : orderRequest.getPurchaseOrderItemsRequests()) {
            PurchaseOrderItems orderDetails = new PurchaseOrderItems();
            orderDetails.setOrders(orders);
            orderDetails.setProductId(purchaseOrderItemsRequest.getProductId());
            orderDetails.setStorageUnit(purchaseOrderItemsRequest.getStorageUnit());
            orderDetails.setQuantity(purchaseOrderItemsRequest.getQuantity());
            orderDetails.setPricePerUnit(purchaseOrderItemsRequest.getPricePerUnit());
            orderDetails.setSuperDistributorId(purchaseOrderItemsRequest.getSuperDistributorId());
            orderDetails.setCreatedBy(purchaseOrderItemsRequest.getCreatedBy());
            orderDetails.setUpdatedBy(purchaseOrderItemsRequest.getUpdatedBy());
            orderDetails.setProducts(purchaseOrderItemsRequest.getProducts());
            orderDetails.setOrders(purchaseOrderItemsRequest.getOrders());

            // Save the DiscountDetail entity
            orderDetailsRepository.save(orderDetails);

        }
        return orders;
    }

    @Override
    public Optional<PurchaseOrders> updateOrders(int ordersId, PurchaseOrdersRequest orderRequest) {
        Optional<PurchaseOrders> result = ordersRepository.findById(ordersId);
        if (result.isPresent()) {
            PurchaseOrders orders = result.get();
            orders.setName(orderRequest.getName());
            orders.setSuperDistributorId(orderRequest.getSuperDistributorId());
            orders.setOrderType(orderRequest.getOrderType());
            orders.setIsApprovalRequired(orderRequest.getIsApprovalRequired());
            orders.setApprovalStatus(orderRequest.getApprovalStatus());
            orders.setCreatedBy(orderRequest.getCreatedBy());
            orders.setUpdatedBy(orderRequest.getUpdatedBy());
            ordersRepository.save(orders);
        } else {
            throw new ResourceNotFoundException("PurchaseOrders", "ordersId", ordersId);
        }
        return result;
    }

    @Override
    public Optional<PurchaseOrders> updateOrdersStatus(int ordersId, PurchaseOrdersRequest orderRequest) {
        Optional<PurchaseOrders> orders = ordersRepository.findById(ordersId);
        if (orders.isPresent()) {
            orders.get().setStatus(orderRequest.isStatus());
            ordersRepository.save(orders.get());
            return orders;

        } else
            throw new ResourceNotFoundException("PurchaseOrders", "ordersId", ordersId);
    }

    @Override
    public Page<PurchaseOrders> findPurchaseOrdersByPagination(int offset, int pageSize) {
        Page<PurchaseOrders> brands = ordersRepository.findAll(PageRequest.of(offset, pageSize));
        return brands;
    }

}