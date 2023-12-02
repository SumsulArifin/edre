package com.tkgroupbd.pusti.api.controllers.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesTarget;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.places.SalesTargetRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.sales.SalesTargetServices;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Sales Target")
@RestController
@RequestMapping("/salesTarget")
public class SalesTargetController {
    @Autowired
    SalesTargetServices salesTargetServices;

    @PostMapping("/addNewSalesTarget")
    public ResponseEntity<MessageResponse> salesTargetSave(@RequestBody @Valid SalesTargetRequest sales_targetRequest) {
        MessageResponse newSales_Target = salesTargetServices.addSalesTarget(sales_targetRequest);
        return new ResponseEntity<>(newSales_Target, HttpStatus.CREATED);
    }

    @GetMapping("/getAllSalesTargets")
    public ResponseEntity<List<SalesTarget>> getSalesTarget() {
        List<SalesTarget> salesTargets = salesTargetServices.getAllSalesTarget();
        return new ResponseEntity<>(salesTargets, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSalesTargetById/{id}")
    public ResponseEntity<?> deleteSalesTarget(@PathVariable("id") Integer id) {
        salesTargetServices.deleteSalesTarget(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getSalesTargetById/{id}")
    public ResponseEntity<SalesTarget> getSalesTargetById(@PathVariable("id") Integer id) {
        SalesTarget salesTarget = salesTargetServices.getSalesTargetById(id);
        return new ResponseEntity<>(salesTarget, HttpStatus.OK);
    }

    @PutMapping("/updateSalesTarget/{id}")
    public ResponseEntity<Optional<SalesTarget>> updateSalesTarget(@PathVariable Integer id,
            @RequestBody SalesTargetRequest sales_targetRequest) {
        Optional<SalesTarget> salesTarget = salesTargetServices.statusChangeAPI(id, sales_targetRequest);
        return new ResponseEntity<Optional<SalesTarget>>(salesTarget, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{id}")
    public ResponseEntity<Optional<SalesTarget>> updateSalesTargetStatus(@PathVariable Integer id,
            @RequestBody SalesTargetRequest sales_targetRequest) {
        Optional<SalesTarget> salesTarget = salesTargetServices.statusChangeAPI(id, sales_targetRequest);
        return new ResponseEntity<Optional<SalesTarget>>(salesTarget, HttpStatus.OK);

    }

    @GetMapping("/getPaginatedSalesTargets")
    private Page<SalesTarget> getPaginatedSalesTarget(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<SalesTarget> salesTargets = salesTargetServices.findSalesTargetByPagination(offset, pageSize);
        return salesTargets;
    }




//    @GetMapping("/getSortedSalesTargetByKey/{field}")
//    private ApiResponse<List<SalesTarget>> getSales_TargetSortedByKey(@PathVariable String field) {
//        List<SalesTarget> salesTargets = salesTargetServices.findSortedSalesTargetByKey(field);
//        return new ApiResponse(salesTargets.size(), salesTargets);
//    }

//    @GetMapping("/getPaginatedSalesTargets/{offset}/{pageSize}")
//    private ApiResponse<Page<SalesTarget>> getPaginatedSalesTarget(@PathVariable int offset,
//            @PathVariable int pageSize) {
//        Page<SalesTarget> salesTargets = salesTargetServices.findSalesTargetByPagination(offset, pageSize);
//        return new ApiResponse(salesTargets.getSize(), salesTargets);
//    }


//    @GetMapping("/getSortedPaginatedSalesTargets/{offset}/{pageSize}/{field}")
//    private ApiResponse<Page<SalesTarget>> getPaginatedSalesTarget(@PathVariable int offset,
//            @PathVariable int pageSize, @PathVariable String field) {
//        Page<SalesTarget> salesTargets = salesTargetServices.findSortedSalesTargetByPagination(offset, pageSize,
//                field);
//        return new ApiResponse(salesTargets.getSize(), salesTargets);
//    }
}
