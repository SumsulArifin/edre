package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;

import java.util.List;

import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletChannel;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.OutletChannelService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Outlet Channel")
@RestController
@RequestMapping("/outletChannel")
public class OutletChannelController {

    @Qualifier("outletChannelServiceImpl")
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    OutletChannelService outletChannelService;

    @GetMapping("/getAllOutletChannels")
    public ResponseEntity<List<OutletChannel>> getOutletChannels() {
        List<OutletChannel> distributors = outletChannelService.getAllOutletChannels();
        return new ResponseEntity<>(distributors, HttpStatus.OK);
    }

    @GetMapping("/getOutletChannelById/{outletChannelId}")
    public ResponseEntity<OutletChannel> getOutletChannelById(
            @PathVariable("outletChannelId") Integer outletChannelId) {
        OutletChannel distributor = outletChannelService.findOutletChannelById(outletChannelId);
        return new ResponseEntity<>(distributor, HttpStatus.OK);
    }
}
