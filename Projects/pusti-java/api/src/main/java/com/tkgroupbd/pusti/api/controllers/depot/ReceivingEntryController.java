package com.tkgroupbd.pusti.api.controllers.depot;

import com.tkgroupbd.pusti.api.data.models.entity.depot.ReceivingEntry;
import com.tkgroupbd.pusti.api.data.payloads.dto.depot.ReceivingEntryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.depot.ReceivingEntryService;

import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Receiving Entry")
@RequestMapping("/receivingEntry")
@CacheConfig(cacheNames = "receivingEntry")
public class ReceivingEntryController {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    ReceivingEntryService receivingEntryService;

    @PostMapping("/addReceivedEntry")
    public ResponseEntity<MessageResponse> addReceivingEntry(@RequestBody ReceivingEntryRequest request) {
        MessageResponse response = receivingEntryService.addReceivingEntry(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateReceivedEntry/{receivingEntryId}")
    public ResponseEntity<Optional<ReceivingEntry>> updateReceivingEntry(@PathVariable int receivingEntryId,
            @RequestBody ReceivingEntryRequest request) {
        Optional<ReceivingEntry> receivingEntry = receivingEntryService.updateReceivingEntry(receivingEntryId, request);
        return new ResponseEntity<Optional<ReceivingEntry>>(receivingEntry, HttpStatus.OK);
    }

    @GetMapping("/getAllReceivedEntries")
    public ResponseEntity<List<ReceivingEntry>> getAllReceivingEntry() {
        List<ReceivingEntry> receivingEntryList = receivingEntryService.getAllReceivingEntry();
        return new ResponseEntity<>(receivingEntryList, HttpStatus.OK);
    }

    @GetMapping("/getReceivedEntryById/{receivingEntryId}")
    public ResponseEntity<ReceivingEntry> getReceivingEntryById(
            @PathVariable("receivingEntryId") Integer receivingEntryId) {
        ReceivingEntry receivingEntry = receivingEntryService.getReceivingEntryById(receivingEntryId);
        return new ResponseEntity<>(receivingEntry, HttpStatus.OK);
    }
}
