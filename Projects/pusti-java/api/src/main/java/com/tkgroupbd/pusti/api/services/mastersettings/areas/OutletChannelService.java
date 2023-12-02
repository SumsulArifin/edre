package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletChannel;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface OutletChannelService {
    public List<OutletChannel> getAllOutletChannels();
    public OutletChannel findOutletChannelById(int outletChannelId);
}
