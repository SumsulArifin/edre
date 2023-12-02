package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.dbproducts;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.ProductItem;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import lombok.Data;

@Data
public class DbProductReceiveRequest {
    private double salesQty;
    private double rateEDP;
    private double qytAdjust;
    private double blockedStock;
    private Distributor distributor;
    private ProductItem productItem;
}
