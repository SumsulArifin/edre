package com.tkgroupbd.pusti.api.services.Common;
import com.tkgroupbd.pusti.api.data.models.common.MenuItem;
import com.tkgroupbd.pusti.api.data.payloads.dto.common.MenuItemDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.common.MenuItemsRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    MenuItemsRepository menuItemsRepository;


    @Override
    public MessageResponse saveMenuItem(MenuItemDTO menuItemsDTO) {
        try {

            MenuItem menuItems = new MenuItem();
            menuItems.setGroupId(menuItemsDTO.getGroupId());
            menuItems.setTitle(menuItemsDTO.getTitle());
            menuItems.setUsersMenuPermissions(menuItemsDTO.getUsersMenuPermissions());
            menuItemsRepository.save(menuItems);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }
    }

    @Override
    public Optional<MenuItem> updateMenuItem(int menuItemsId, MenuItemDTO menuItemsDTO) {
        Optional<MenuItem> result = menuItemsRepository.findById(menuItemsId);
        if (result.isPresent()) {
            MenuItem menuItems=result.get();
            menuItems.setTitle(menuItemsDTO.getTitle());
            menuItems.setGroupId(menuItemsDTO.getGroupId());
            menuItems.setUsersMenuPermissions(menuItemsDTO.getUsersMenuPermissions());
            menuItemsRepository.save(menuItems);
        } else {
            throw new ResourceNotFoundException("MenuItems", "menuItemsId", menuItemsId);
        }
        return result;
    }

    @Override
    public List<MenuItem> getAllMenuItem() {
        return menuItemsRepository.findAll();
    }
}
