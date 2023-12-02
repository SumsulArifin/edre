package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.Distributorsetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributorsettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DistributorsettingService {

    MessageResponse saveDistributorsetting(DistributorsettingRequest distributorsettingRequest);

    public Optional<Distributorsetting> updateDistributorsetting(int id,
            DistributorsettingRequest distributorsettingRequest);

    public void deleteDistributorsettingById(int distributor_id);

    public Optional<Distributorsetting> updateDistributorsettingStatus(int id,
            DistributorsettingRequest distributorsettingRequest);

    public List<Distributorsetting> getAllDistributorsetting();

    public Distributorsetting findDistributorsettingById(int id);

}
