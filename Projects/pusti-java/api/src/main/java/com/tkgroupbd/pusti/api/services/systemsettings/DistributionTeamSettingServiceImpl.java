package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributionTeamSetting;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributionTeamSettingRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.systemsettings.DistributionTeamSettingRepository;
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
public class DistributionTeamSettingServiceImpl implements DistributionTeamSettingService {

    @Autowired
    DistributionTeamSettingRepository repository;

    @Override
    public MessageResponse createDistributionTeamSetting(DistributionTeamSettingRequest request) {
        DistributionTeamSetting teamSetting = new DistributionTeamSetting();

        teamSetting.setTeamName(request.getTeamName());
        teamSetting.setCreatedBy(request.getCreatedBy());
        teamSetting.setUpdatedBy(request.getUpdatedBy());
        teamSetting.setStatus(request.isStatus());
        teamSetting.setCreatedAt(request.getCreatedAt());
        teamSetting.setUpdatedAt(request.getUpdatedAt());
        teamSetting.setDeletedAt(request.getDeletedAt());
        teamSetting.setBrowser(request.getBrowser());
        teamSetting.setIp(request.getIp());

        repository.save(teamSetting);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<DistributionTeamSetting> updateDistributionTeamSetting(Integer id,
            DistributionTeamSettingRequest request) {
        Optional<DistributionTeamSetting> result = repository.findById(id);

        if (result.isPresent()) {
            DistributionTeamSetting teamSetting = result.get();

            teamSetting.setTeamName(request.getTeamName());
            teamSetting.setCreatedBy(request.getCreatedBy());
            teamSetting.setUpdatedBy(request.getUpdatedBy());
            teamSetting.setStatus(request.isStatus());
            teamSetting.setCreatedAt(request.getCreatedAt());
            teamSetting.setUpdatedAt(request.getUpdatedAt());
            teamSetting.setDeletedAt(request.getDeletedAt());
            teamSetting.setBrowser(request.getBrowser());
            teamSetting.setIp(request.getIp());

            repository.save(teamSetting);

        } else {
            throw new ResourceNotFoundException("DistributionTeamSetting", "id", id);
        }

        return result;
    }

    @Override
    public void deleteDistributionTeamSetting(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public DistributionTeamSetting getDistributionTeamSettingById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DistributionTeamSetting", "id", id));
    }

    @Override
    public List<DistributionTeamSetting> getAllDistributionTeamSetting() {
        return repository.findAll();
    }

    @Override
    public Optional<DistributionTeamSetting> distributionTeamSettingStatusChange(Integer id,
            DistributionTeamSettingRequest request) {
        Optional<DistributionTeamSetting> result = repository.findById(id);

        if (result.isPresent()) {
            DistributionTeamSetting teamSetting = result.get();

            teamSetting.setStatus(request.isStatus());
            repository.save(teamSetting);

        } else {
            throw new ResourceNotFoundException("DistributionTeamSetting", "id", id);
        }

        return result;
    }

    @Override
    public Page<DistributionTeamSetting> findDistributionTeamSettingByPagination(int offset, int pageSize) {
        Page<DistributionTeamSetting> teamSettings = repository.findAll(PageRequest.of(offset, pageSize));
        return teamSettings;
    }

    @Override
    public Page<DistributionTeamSetting> findSortedDistributionTeamSettingByPagination(int offset, int pageSize,
            String field) {
        Page<DistributionTeamSetting> teamSettings = repository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return teamSettings;
    }
}
