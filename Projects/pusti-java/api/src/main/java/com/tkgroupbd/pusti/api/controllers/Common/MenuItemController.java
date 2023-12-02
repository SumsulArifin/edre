package com.tkgroupbd.pusti.api.controllers.Common;
import com.tkgroupbd.pusti.api.data.models.common.MenuItem;
import com.tkgroupbd.pusti.api.data.payloads.dto.common.MenuItemDTO;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.DivisionRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.Common.MenuItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@Tag(name = "MenuItem")
@RestController
@RequestMapping("/menuItem")
public class MenuItemController {
    @Autowired
    MenuItemService menuItemService;

    @PostMapping("/addNewMenuItem")
    public ResponseEntity<MessageResponse> menuItemSave(@RequestBody @Valid MenuItemDTO menuItemDTO) {
        MessageResponse menuItem = menuItemService.saveMenuItem(menuItemDTO);
        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);
    }

    @GetMapping("/getAllMenuItem")
    @ResponseBody
    public ResponseEntity<List<MenuItem>> getAllMenuItem() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItem();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @PutMapping("/updateMenuItem/{menuItemsId}")
    public ResponseEntity<Optional<MenuItem>> updateMenuItem(@PathVariable Integer menuItemsId,
                                                             @RequestBody @Valid MenuItemDTO menuItemDTO) {
        Optional<MenuItem> updateMenuItem = menuItemService.updateMenuItem(menuItemsId, menuItemDTO);
        return new ResponseEntity<Optional<MenuItem>>(updateMenuItem, HttpStatus.OK);
    }
}
