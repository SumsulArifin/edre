package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DashboardSetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DashboardSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.systemsettings.DashboardSettingRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardSettingServiceImpl implements DashboardSettingService {
    @Autowired
    private DashboardSettingRepository repository;

    @Override
    public MessageResponse createDashboardSetting(DashboardSettingRequest request) {
        DashboardSetting dashboardSetting = new DashboardSetting();

        dashboardSetting.setStatus(request.isStatus());
        dashboardSetting.setCreatedAt(request.getCreatedAt());
        dashboardSetting.setUpdatedAt(request.getUpdatedAt());
        dashboardSetting.setDeletedAt(request.getDeletedAt());
        dashboardSetting.setBrowser(request.getBrowser());
        dashboardSetting.setIp(request.getIp());
        dashboardSetting.setRoleId(request.getRoleId());
        dashboardSetting.setFeature(request.getFeature());

        repository.save(dashboardSetting);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<DashboardSetting> updateDashboardSetting(Integer id, DashboardSettingRequest request) {
        Optional<DashboardSetting> result = repository.findById(id);

        if (result.isPresent()) {
            DashboardSetting dashboardSetting = result.get();

            dashboardSetting.setStatus(request.isStatus());
            dashboardSetting.setCreatedAt(request.getCreatedAt());
            dashboardSetting.setUpdatedAt(request.getUpdatedAt());
            dashboardSetting.setDeletedAt(request.getDeletedAt());
            dashboardSetting.setBrowser(request.getBrowser());
            dashboardSetting.setIp(request.getIp());
            dashboardSetting.setRoleId(request.getRoleId());
            dashboardSetting.setFeature(request.getFeature());

            repository.save(dashboardSetting);

        } else {
            throw new ResourceNotFoundException("DashboardSetting", "id", id);
        }

        return result;
    }

    @Override
    public void deleteDashboardSetting(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public DashboardSetting getDashboardSettingById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DashboardSetting", "id", id));
    }

    @Override
    public List<DashboardSetting> getAllDashboardSetting() {
        return repository.findAll();
    }

    @Override
    public Optional<DashboardSetting> dashboardSettingStatusChange(Integer id, DashboardSettingRequest request) {
        Optional<DashboardSetting> result = repository.findById(id);

        if (result.isPresent()) {
            DashboardSetting dashboardSetting = result.get();
            dashboardSetting.setStatus(request.isStatus());
            repository.save(dashboardSetting);

        } else {
            throw new ResourceNotFoundException("DashboardSetting", "id", id);
        }

        return result;
    }

    @Override
    public Page<DashboardSetting> findDashboardSettingByPagination(int offset, int pageSize) {
        Page<DashboardSetting> dashboardSettings = repository.findAll(PageRequest.of(offset, pageSize));
        return dashboardSettings;
    }

    @Override
    public Page<DashboardSetting> findSortedDashboardSettingByPagination(int offset, int pageSize, String field) {
        Page<DashboardSetting> dashboardSettings = repository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return dashboardSettings;
    }
}
