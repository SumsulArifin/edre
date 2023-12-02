package com.tkgroupbd.pusti.api.services.depot;

import com.tkgroupbd.pusti.api.data.models.entity.depot.ReceivingEntry;
import com.tkgroupbd.pusti.api.data.payloads.dto.depot.ReceivingEntryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.depot.ReceivingEntryRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceivingEntryServiceImpl implements ReceivingEntryService {
    @Autowired
    ReceivingEntryRepository receivingEntryRepository;

    @Override
    public MessageResponse addReceivingEntry(ReceivingEntryRequest receivingEntryRequest) {
        try {
            ReceivingEntry receivingEntry = new ReceivingEntry();
            receivingEntry.setReceivingTime(receivingEntryRequest.getReceivingTime());
            receivingEntry.setQuantity(receivingEntryRequest.getQuantity());
            receivingEntry.setInCharge(receivingEntryRequest.getInCharge());
            receivingEntry.setFactory(receivingEntryRequest.getFactory());
            receivingEntry.setCategory(receivingEntryRequest.getCategory());
            receivingEntry.setDepot(receivingEntryRequest.getDepot());
            receivingEntryRepository.save(receivingEntry);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION);
        }
    }

    @Override
    public Optional<ReceivingEntry> updateReceivingEntry(int receivingEntryId,
            ReceivingEntryRequest receivingEntryRequest) {
        Optional<ReceivingEntry> result = Optional.ofNullable(receivingEntryRepository.findById(receivingEntryId)
                .orElseThrow(() -> new ResourceNotFoundException("ReceivingEntry", "id", receivingEntryId)));

        if (result.isPresent()) {
            ReceivingEntry receivingEntry = result.get();

            receivingEntry.setReceivingTime(receivingEntryRequest.getReceivingTime());
            receivingEntry.setQuantity(receivingEntryRequest.getQuantity());
            receivingEntry.setInCharge(receivingEntryRequest.getInCharge());
            receivingEntry.setFactory(receivingEntryRequest.getFactory());
            receivingEntry.setCategory(receivingEntryRequest.getCategory());
            receivingEntry.setDepot(receivingEntryRequest.getDepot());
            receivingEntryRepository.save(receivingEntry);
        } else {
            throw new ResourceNotFoundException("DeliveredProduct", "receivingEntryId", receivingEntryId);
        }
        return result;
    }

    @Override
    public void deleteReceivingEntry(int receivingEntryId) {
        receivingEntryRepository.deleteById(receivingEntryId);
    }

    @Override
    public ReceivingEntry getReceivingEntryById(int receivingEntryId) {
        return receivingEntryRepository.findById(receivingEntryId).get();
    }

    @Override
    public List<ReceivingEntry> getAllReceivingEntry() {
        return receivingEntryRepository.findAll();
    }
}
