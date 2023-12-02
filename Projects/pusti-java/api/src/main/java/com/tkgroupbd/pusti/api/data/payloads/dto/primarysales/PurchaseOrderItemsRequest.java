package com.tkgroupbd.pusti.api.data.payloads.dto.primarysales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.Product;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.PurchaseOrders;
import com.tkgroupbd.pusti.api.data.models.enums.StorageUnit;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class PurchaseOrderItemsRequest {

    private long productId;
    @Enumerated(EnumType.STRING)
    private StorageUnit storageUnit;
    private int quantity;
    private int pricePerUnit;
    private int superDistributorId;
    private String createdBy;
    private String updatedBy;
    private Product products;
    private PurchaseOrders orders;
    private boolean status;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

}
