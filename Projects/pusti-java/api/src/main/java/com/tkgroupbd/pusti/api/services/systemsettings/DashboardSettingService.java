package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DashboardSetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DashboardSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DashboardSettingService {
    MessageResponse createDashboardSetting(DashboardSettingRequest request);

    Optional<DashboardSetting> updateDashboardSetting(Integer id, DashboardSettingRequest request);

    void deleteDashboardSetting(Integer id);

    DashboardSetting getDashboardSettingById(Integer id);

    List<DashboardSetting> getAllDashboardSetting();

    Optional<DashboardSetting> dashboardSettingStatusChange(Integer id, DashboardSettingRequest request);

    Page<DashboardSetting> findDashboardSettingByPagination(int offset, int pageSize);

    Page<DashboardSetting> findSortedDashboardSettingByPagination(int offset, int pageSize, String field);

}
