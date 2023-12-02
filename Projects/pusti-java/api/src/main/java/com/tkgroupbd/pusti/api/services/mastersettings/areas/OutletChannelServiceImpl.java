package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletChannel;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.OutletChannelRepository;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OutletChannelServiceImpl implements OutletChannelService {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private OutletChannelRepository channelRepository;

    @Override
    public List<OutletChannel> getAllOutletChannels() {
        return channelRepository.findAll();
    }

    @Override
    public OutletChannel findOutletChannelById(int outletChannelId) {
        return channelRepository.findById(outletChannelId).get();
    }
}
