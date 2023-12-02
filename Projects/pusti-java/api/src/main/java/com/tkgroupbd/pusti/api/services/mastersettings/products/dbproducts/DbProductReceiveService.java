package com.tkgroupbd.pusti.api.services.mastersettings.products.dbproducts;

import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.products.dbproducts.DbProductReceiveRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

@Component
public interface DbProductReceiveService {
    MessageResponse createDbProductReceive(DbProductReceiveRequest dbProductReceiveRequest);

}
