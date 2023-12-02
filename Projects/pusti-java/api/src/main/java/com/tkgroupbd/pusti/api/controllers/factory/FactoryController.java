package com.tkgroupbd.pusti.api.controllers.factory;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.factory.Factory;
import com.tkgroupbd.pusti.api.data.payloads.dto.factory.FactoryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.depot.DepotService;
import com.tkgroupbd.pusti.api.services.factory.FactoryService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Factory")
@RestController
@RequestMapping("/factory")
public class FactoryController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private FactoryService factoryService;

    @Autowired
    private DepotService depotService;

    @PostMapping("/addNewFactory")
    public ResponseEntity<MessageResponse> factorySave(@RequestBody @Valid FactoryRequest factoryRequest) {
        MessageResponse newFactory = factoryService.createNewFactory(factoryRequest);
        return new ResponseEntity<>(newFactory, HttpStatus.CREATED);
    }

    @GetMapping("/getAllFactories")
    public ResponseEntity<List<Factory>> getFactory() {
        List<Factory> factory = factoryService.getAllFactory();
        return new ResponseEntity<>(factory, HttpStatus.OK);
    }

    @GetMapping("/searchFactoriesOrDepotsName")
    public ResponseEntity<List<Factory>> search(@RequestParam("field") String field) {
        List<Factory> result = factoryService.searchFactoriesAndDepots(field);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/deleteFactoryById/{factoryId}")
    public ResponseEntity<?> deleteFactory(@PathVariable("factoryId") Integer factoryId) {
        factoryService.deleteFactory(factoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getFactoryById/{factoryId}")
    public ResponseEntity<Factory> getFactoryById(@PathVariable("factoryId") Integer factoryId) {
        Factory factory = factoryService.factoryById(factoryId);
        return new ResponseEntity<>(factory, HttpStatus.OK);
    }


    @GetMapping("/getFactoryByDepotId/{id}")
    private ApiResponse<List<Factory>> getByFactoryByDepotId(@PathVariable("id") int id) {
        List<Factory> factoryList = factoryService.getFactoriesByDepotId(id);
        return new ApiResponse(factoryList.size(), factoryList);
    }

    @PutMapping("/updateFactory/{factoryId}")
    public ResponseEntity<Optional<Factory>> updateFactory(@PathVariable Integer factoryId,
                                                           @RequestBody @Valid FactoryRequest factoryRequest) {
        Optional<Factory> factory = factoryService.updateFactory(factoryId, factoryRequest);
        return new ResponseEntity<Optional<Factory>>(factory, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{factoryId}")
    public ResponseEntity<Optional<Factory>> updateFactoryStatus(@PathVariable Integer factoryId,
                                                                 @RequestBody FactoryRequest factoryRequest) {
        Optional<Factory> factory = factoryService.changeFactoryStatus(factoryId, factoryRequest);
        return new ResponseEntity<Optional<Factory>>(factory, HttpStatus.OK);
    }

    @PostMapping("/uploadCSVWithFactoryAndDepot")
    public ResponseEntity<MessageResponse> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        MessageResponse newRoute = factoryService.uploadCSVWithFactoryAndDepot(file);
        return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
    }


    @GetMapping("/excelDownload")
    public ResponseEntity<?> downloadExcel(HttpServletResponse response) {
        try {
            factoryService.downloadExcel(response);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to download Excel file: " + e.getMessage());
        }
    }

    @GetMapping("/getPaginatedFactories")
    private Page<Factory> getPaginatedBrands(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Factory> paginatedFactories = factoryService.getFactoryByPagination(offset, pageSize);
        return paginatedFactories;
    }



}
