package com.tkgroupbd.pusti.api.services.depot;

import com.tkgroupbd.pusti.api.data.models.entity.depot.ReceivingEntry;
import com.tkgroupbd.pusti.api.data.payloads.dto.depot.ReceivingEntryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ReceivingEntryService {

    MessageResponse addReceivingEntry(ReceivingEntryRequest receivingEntryRequest);

    Optional<ReceivingEntry> updateReceivingEntry(int receivingEntryId, ReceivingEntryRequest receivingEntryRequest);

    void deleteReceivingEntry(int receivingEntryId);

    ReceivingEntry getReceivingEntryById(int receivingEntryId);

    List<ReceivingEntry> getAllReceivingEntry();
}
