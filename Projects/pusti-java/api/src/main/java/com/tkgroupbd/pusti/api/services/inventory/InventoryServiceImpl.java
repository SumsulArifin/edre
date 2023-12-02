package com.tkgroupbd.pusti.api.services.inventory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.Inventory;
import com.tkgroupbd.pusti.api.data.payloads.dto.inventory.InventoryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.inventory.InventoryRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private InventoryRepository inventoryRepository;

    @Override
    public MessageResponse addNewInventory(InventoryRequest inventoryRequest) {

        try {
            Inventory inventory = new Inventory();
            inventory.setName(inventoryRequest.getName());
            inventory.setAddress(inventoryRequest.getAddress());
            inventory.setStatus(inventoryRequest.isStatus());
            inventoryRepository.save(inventory);

            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION);
        }
    }

    @Override
    public Optional<Inventory> updateInventory(int inventoryId, InventoryRequest inventoryRequest) {
        Optional<Inventory> result = Optional.ofNullable(inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", "inventoryId", inventoryId)));
        System.out.println(result + System.lineSeparator() + "---------------------------------------------");

        if (result.isPresent()) {
            Inventory inventory = result.get();

            inventory.setName(inventoryRequest.getName());
            inventory.setAddress(inventoryRequest.getAddress());
            inventory.setCreatedAt(inventoryRequest.getCreatedAt());
            inventory.setCreatedBy(inventoryRequest.getCreatedBy());
            inventory.setUpdatedAt(inventoryRequest.getUpdatedAt());
            inventory.setUpdatedBy(inventoryRequest.getUpdatedBy());
            inventory.setDeletedAt(inventoryRequest.getDeletedAt());
            inventory.setDeletedBy(inventoryRequest.getDeletedBy());
            inventory.setStatus(inventoryRequest.isStatus());
            inventory.setIp(inventoryRequest.getIp());
            inventory.setBrowser(inventoryRequest.getBrowser());

            inventoryRepository.save(inventory);

        } else {
            throw new ResourceNotFoundException("Inventory", "inventoryId", inventoryId);
        }
        return result;
    }

    @Override
    public MessageResponse deleteInventory(int inventoryId) {
        if (inventoryRepository.existsById(inventoryId)) {
            inventoryRepository.deleteById(inventoryId);
            return new MessageResponse(Message.SUCCESS_DELETED);
        } else {
            return new MessageResponse(Message.FAILED_DELETED);
        }
    }

    @Override
    public Optional<Inventory> getInventoryById(int inventoryId) {
        return Optional.ofNullable(inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", "inventoryId", inventoryId)));
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> inventoryStatusChange(Integer inventoryId, InventoryRequest inventoryRequest) {
        Optional<Inventory> result = Optional.ofNullable(inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", "inventoryId", inventoryId)));

        if (result.isPresent()) {
            Inventory inventory = result.get();
            inventory.setStatus(inventoryRequest.isStatus());
            inventoryRepository.save(inventory);

        } else {
            throw new ResourceNotFoundException("Inventory", "inventoryId", inventoryId);
        }
        return result;
    }

    @Override
    public void exportToExcel(List<Inventory> inventories, HttpServletResponse response) throws IOException {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Inventory");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Inventory Id");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Address");

            // Populate data rows
            int rowNum = 1;
            for (Inventory inventory : inventories) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(inventory.getInventoryId());
                row.createCell(1).setCellValue(inventory.getName());
                row.createCell(2).setCellValue(inventory.getAddress());
            }
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=inventory.xlsx");
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                workbook.write(outputStream);
            }

    }

    @Override
    public Page<Inventory> findInventoryByPagination(int offset, int pageSize) {
        Page<Inventory> inventories = inventoryRepository.findAll(PageRequest.of(offset, pageSize));
        return inventories;
    }

}
