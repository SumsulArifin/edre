package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.AlternateChannelSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.AlternateChannelSettingsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AlternateChannelSettingsService {
    MessageResponse createAltChannelSettings(AlternateChannelSettingsRequest request);

    Optional<AlternateChannelSettings> updateAltChannelSettings(Integer id, AlternateChannelSettingsRequest request);

    void deleteAltChannelSettings(Integer id);

    AlternateChannelSettings getAltChannelSettingsById(Integer id);

    List<AlternateChannelSettings> getAllAltChannelSettings();

    Optional<AlternateChannelSettings> altChannelSettingsStatusChange(Integer id,
            AlternateChannelSettingsRequest request);

    Page<AlternateChannelSettings> findAltChannelSettingsByPagination(int offset, int pageSize);

    Page<AlternateChannelSettings> findSortedAltChannelSettingsByPagination(int offset, int pageSize, String field);

}
