package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributionTeamSetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributionTeamSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DistributionTeamSettingService {

    MessageResponse createDistributionTeamSetting(DistributionTeamSettingRequest request);

    Optional<DistributionTeamSetting> updateDistributionTeamSetting(Integer id, DistributionTeamSettingRequest request);

    void deleteDistributionTeamSetting(Integer id);

    DistributionTeamSetting getDistributionTeamSettingById(Integer id);

    List<DistributionTeamSetting> getAllDistributionTeamSetting();

    Optional<DistributionTeamSetting> distributionTeamSettingStatusChange(Integer id,
            DistributionTeamSettingRequest request);

    Page<DistributionTeamSetting> findDistributionTeamSettingByPagination(int offset, int pageSize);

    Page<DistributionTeamSetting> findSortedDistributionTeamSettingByPagination(int offset, int pageSize, String field);

}
