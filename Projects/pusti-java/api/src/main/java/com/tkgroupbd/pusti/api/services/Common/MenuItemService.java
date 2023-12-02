package com.tkgroupbd.pusti.api.services.Common;
import com.tkgroupbd.pusti.api.data.models.common.MenuItem;
import com.tkgroupbd.pusti.api.data.payloads.dto.common.MenuItemDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MenuItemService {
    MessageResponse saveMenuItem(MenuItemDTO menuItemsDTO);
    public Optional<MenuItem> updateMenuItem(int menuItemsId, MenuItemDTO menuItemsDTO);
    public List<MenuItem> getAllMenuItem();
}
