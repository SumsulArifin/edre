package com.tkgroupbd.pusti.api.controllers.factory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.Inventory;
import com.tkgroupbd.pusti.api.data.payloads.dto.inventory.InventoryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.inventory.InventoryRepository;
import com.tkgroupbd.pusti.api.services.inventory.InventoryService;
import com.tkgroupbd.pusti.api.services.inventory.InventoryServiceImpl;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Inventory")
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private InventoryService inventoryService;
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private InventoryRepository inventoryRepository;


    @PostMapping("/addNewInventory")
    public ResponseEntity<MessageResponse> saveNewInventory(@RequestBody @Valid InventoryRequest inventoryRequest) {
        MessageResponse addInventory = inventoryService.addNewInventory(inventoryRequest);
        return new ResponseEntity<>(addInventory, HttpStatus.CREATED);
    }

    @GetMapping("/getAllInventories")
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFactoryById/{inventoryId}")
    public ResponseEntity<?> deleteInventory(@PathVariable("inventoryId") Integer inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getInventoryById/{inventoryId}")
    public ResponseEntity<Optional<Inventory>> getFactoryById(@PathVariable("inventoryId") Integer inventoryId) {
        Optional<Inventory> inventory = inventoryService.getInventoryById(inventoryId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PutMapping("/updateInventory/{inventoryId}")
    public ResponseEntity<Optional<Inventory>> updateInventory(@PathVariable("inventoryId") Integer inventoryId,
            @RequestBody @Valid InventoryRequest inventoryRequest) {
        Optional<Inventory> inventory = inventoryService.updateInventory(inventoryId, inventoryRequest);
        return new ResponseEntity<Optional<Inventory>>(inventory, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{inventoryId}")
    public ResponseEntity<Optional<Inventory>> inventoryStatusChange(@PathVariable("inventoryId") Integer inventoryId,
            @RequestBody InventoryRequest inventoryRequest) {
        Optional<Inventory> inventory = inventoryService.inventoryStatusChange(inventoryId, inventoryRequest);
        return new ResponseEntity<Optional<Inventory>>(inventory, HttpStatus.OK);
    }

    @GetMapping("/excelDownload")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<Inventory> inventories = inventoryRepository.findAll();
        inventoryService.exportToExcel(inventories, response);
    }

    @GetMapping("/getPaginatedBrands")
    private Page<Inventory> getPaginatedBrands(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Inventory> inventories = inventoryService.findInventoryByPagination(offset, pageSize);
        return inventories;
    }

}
