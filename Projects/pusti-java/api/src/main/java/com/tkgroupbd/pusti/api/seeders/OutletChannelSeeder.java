package com.tkgroupbd.pusti.api.seeders;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletChannel;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.OutletChannelRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OutletChannelSeeder {

    private OutletChannelRepository outletChannelRepository;

    @Autowired
    public OutletChannelSeeder(OutletChannelRepository outletChannelRepo) {
        this.outletChannelRepository = outletChannelRepo;
    }

    public void seedOutletChannelTable() {

        List<OutletChannel> outletChannelList = new ArrayList();

        OutletChannel outletChannel = new OutletChannel();
        outletChannel.setChannelName("NA");
        outletChannel.setStatus(true);
        outletChannelList.add(outletChannel);

        outletChannel = new OutletChannel();
        outletChannel.setChannelName("Urban");
        outletChannel.setStatus(true);
        outletChannelList.add(outletChannel);

        outletChannel = new OutletChannel();
        outletChannel.setChannelName("Rural");
        outletChannel.setStatus(true);
        outletChannelList.add(outletChannel);

        outletChannel = new OutletChannel();
        outletChannel.setChannelName("Wet Market");
        outletChannel.setStatus(true);
        outletChannelList.add(outletChannel);

        outletChannel = new OutletChannel();
        outletChannel.setChannelName("Urban Neighbourhood");
        outletChannel.setStatus(true);
        outletChannelList.add(outletChannel);

        if (outletChannelRepository.count() < outletChannelList.size()) {
            outletChannelRepository.saveAll(outletChannelList);
        }
    }
}
