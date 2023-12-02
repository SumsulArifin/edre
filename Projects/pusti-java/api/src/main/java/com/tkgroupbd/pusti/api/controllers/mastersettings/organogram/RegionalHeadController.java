package com.tkgroupbd.pusti.api.controllers.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.RegionalHead;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.RegionalHeadRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.organogram.RegionalHeadService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Regional Head Area")
@RestController
@RequestMapping("/regionalhead")
public class RegionalHeadController {
    @Autowired
    RegionalHeadService regionalHeadService;

    @PostMapping("/addNewRegionalHead")
    public ResponseEntity<MessageResponse> addNewRegionalHead(
            @RequestBody @Valid RegionalHeadRequest regionalHeadRequest) {

        MessageResponse newRegionalHead = regionalHeadService.saveRegionalHead(regionalHeadRequest);
        return new ResponseEntity<>(newRegionalHead, HttpStatus.CREATED);
    }

    @GetMapping("/getAllRegionalHeads")
    public ResponseEntity<List<RegionalHead>> getRegionalHead() {
        List<RegionalHead> regionalHeads = regionalHeadService.getAllRegionalHeads();
        return new ResponseEntity<>(regionalHeads, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRegionalHeadById/{regionalHeadId}")
    public ResponseEntity<?> deleteRegionalHeadById(@PathVariable("regionalHeadId") Integer regionalHeadId) {
        regionalHeadService.deleteRegionalHeadById(regionalHeadId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getRegionalHeadById/{regionalHeadId}")
    public ResponseEntity<RegionalHead> getRegionalHeadById(@PathVariable("regionalHeadId") Integer regionalHeadId) {
        RegionalHead regionalHead = regionalHeadService.findRegionalHeadById(regionalHeadId);
        return new ResponseEntity<>(regionalHead, HttpStatus.OK);
    }

    @PutMapping("/updateRegionalHead/{regionalHeadId}")
    public ResponseEntity<Optional<RegionalHead>> updateRegionalHead(@PathVariable Integer regionalHeadId,
            @RequestBody @Valid RegionalHeadRequest regionalHeadRequest) {
        Optional<RegionalHead> regionalHead = regionalHeadService.updateRegionalHead(regionalHeadId,
                regionalHeadRequest);
        return new ResponseEntity<Optional<RegionalHead>>(regionalHead, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{regionalHeadId}")
    public ResponseEntity<Optional<RegionalHead>> updateRegionalHeadStatus(@PathVariable Integer regionalHeadId,
            @RequestBody RegionalHeadRequest regionalHeadRequest) {
        Optional<RegionalHead> regionalHead = regionalHeadService.updateRegionalHeadStatus(regionalHeadId,
                regionalHeadRequest);
        return new ResponseEntity<Optional<RegionalHead>>(regionalHead, HttpStatus.OK);
    }

    @GetMapping("/getSortedRegionalHeadByKey/{field}")
    private ApiResponse<List<RegionalHead>> getSortedRegionalHeadByKey(@PathVariable String field) {
        List<RegionalHead> regionalHeads = regionalHeadService.findSortedRegionalHeadByKey(field);
        return new ApiResponse(regionalHeads.size(), regionalHeads);
    }

    @GetMapping("/getPaginatedRegionalHeads")
    private ResponseEntity<Page<RegionalHead>> getPaginatedRegionalHeads(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<RegionalHead> paginatedRegionalHeads = regionalHeadService.findRegionalHeadsByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedRegionalHeads,HttpStatus.OK);
    }
}
