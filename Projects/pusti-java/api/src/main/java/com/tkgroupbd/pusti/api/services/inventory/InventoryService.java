package com.tkgroupbd.pusti.api.services.inventory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.Inventory;
import com.tkgroupbd.pusti.api.data.payloads.dto.inventory.InventoryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface InventoryService {
    public MessageResponse addNewInventory(InventoryRequest inventoryRequest);

    public Optional<Inventory> updateInventory(int inventoryId, InventoryRequest inventoryRequest);

    public MessageResponse deleteInventory(int inventoryId);

    public Optional<Inventory> getInventoryById(int inventoryId);

    public List<Inventory> getAllInventories();

    public Optional<Inventory> inventoryStatusChange(Integer inventoryId, InventoryRequest inventoryRequest);
    public void exportToExcel(List<Inventory> inventories, HttpServletResponse response) throws IOException;

    Page<Inventory> findInventoryByPagination(int offset, int pageSize);
}
