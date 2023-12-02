package com.tkgroupbd.pusti.api.controllers.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.notices.Notice;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.ZonalHead;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.ZonalHeadRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.organogram.ZonalHeadservice;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Zonal Head Area")
@RestController
@RequestMapping("/zonalhead")
public class ZonalHeadController {
    @Autowired
    ZonalHeadservice zonalHeadservice;

    @PostMapping("/addNewZonalHead")
    public ResponseEntity<MessageResponse> addNewZonalHead(@RequestBody @Valid ZonalHeadRequest zonalHeadRequest) {
        MessageResponse newzonalHead = zonalHeadservice.saveZonalHead(zonalHeadRequest);
        return new ResponseEntity<>(newzonalHead, HttpStatus.CREATED);
    }

    @GetMapping("/getAllZonalHeads")
    public ResponseEntity<List<ZonalHead>> getZonalHeads() {
        List<ZonalHead> zonalHeads = zonalHeadservice.getAllZonalHeads();
        return new ResponseEntity<>(zonalHeads, HttpStatus.OK);
    }

    @DeleteMapping("/deleteZonalHeadById/{zonalHeadId}")
    public ResponseEntity<?> deleteZonalHeadById(@PathVariable("zonalHeadId") Integer zonalHeadId) {
        zonalHeadservice.deleteZonalHeadById(zonalHeadId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getZonalHeadById/{zonalHeadId}")
    public ResponseEntity<ZonalHead> getZonalHeadById(@PathVariable("zonalHeadId") Integer zonalHeadId) {
        ZonalHead zonalHead = zonalHeadservice.findZonalHeadById(zonalHeadId);
        return new ResponseEntity<>(zonalHead, HttpStatus.OK);
    }

    @PutMapping("/updateZonalHead/{zonalHeadId}")
    public ResponseEntity<Optional<ZonalHead>> updateZonalHead(@PathVariable Integer zonalHeadId,
            @RequestBody @Valid ZonalHeadRequest zonalHeadRequest) {
        Optional<ZonalHead> zonalHead = zonalHeadservice.updateZonalHead(zonalHeadId, zonalHeadRequest);
        return new ResponseEntity<Optional<ZonalHead>>(zonalHead, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{zonalHeadId}")
    public ResponseEntity<Optional<ZonalHead>> updateZonalHeadStatus(@PathVariable Integer zonalHeadId,
            @RequestBody ZonalHeadRequest zonalHeadRequest) {
        Optional<ZonalHead> zonalHead = zonalHeadservice.updateZonalHeadStatus(zonalHeadId,
                zonalHeadRequest);
        return new ResponseEntity<Optional<ZonalHead>>(zonalHead, HttpStatus.OK);
    }

    @GetMapping("/getSortedZonalHeadsByKey/{field}")
    private ApiResponse<List<ZonalHead>> getSortedZonalHeadByKey(@PathVariable String field) {
        List<ZonalHead> zonalHeads = zonalHeadservice.findSortedZonalHeadByKey(field);
        return new ApiResponse(zonalHeads.size(), zonalHeads);
    }

    @GetMapping("/getPaginatedZonalHeads")
    private ResponseEntity<Page<ZonalHead>> getPaginatedZonalHeads(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<ZonalHead> paginatedZonalHeads = zonalHeadservice.findZonalHeadsByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedZonalHeads,HttpStatus.OK);
    }
}
