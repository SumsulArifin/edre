package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;
import java.util.List;

import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletType;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.OutletTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Outlet Type")
@RestController
@RequestMapping("/outletType")
public class OutletTypeController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    OutletTypeService outletTypeService;

    @GetMapping("/getAllOutletTypes")
    public ResponseEntity<List<OutletType>> getAllOutletTypes() {
        List<OutletType> outletTypes = outletTypeService.getAllOutletTypes();
        return new ResponseEntity<>(outletTypes, HttpStatus.OK);
    }

    @GetMapping("/getOutletTypeById/{outletTypeId}")
    public ResponseEntity<OutletType> getOutletTypeById(@PathVariable("outletTypeId") Integer outletTypeId) {
        OutletType outletType = outletTypeService.findOutletTypeById(outletTypeId);
        return new ResponseEntity<>(outletType, HttpStatus.OK);
    }
}
