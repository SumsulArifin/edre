package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.AlternateChannelSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.AlternateChannelSettingsRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.systemsettings.AlternateChannelSettingsRepository;
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
public class AlternateChannelSettingsServiceImpl implements AlternateChannelSettingsService {

    @Autowired
    AlternateChannelSettingsRepository repository;

    @Override
    public MessageResponse createAltChannelSettings(AlternateChannelSettingsRequest request) {
        AlternateChannelSettings alternateChannelSettings = new AlternateChannelSettings();

        alternateChannelSettings.setStartDate(request.getStartDate());
        alternateChannelSettings.setEndDate(request.getEndDate());
        alternateChannelSettings.setEligibleAreaIds(request.getEligibleAreaIds());
        alternateChannelSettings.setEligibleOutletIds(request.getEligibleOutletIds());
        alternateChannelSettings.setStatus(request.isStatus());
        alternateChannelSettings.setCreatedAt(request.getCreatedAt());
        alternateChannelSettings.setUpdatedAt(request.getUpdatedAt());
        alternateChannelSettings.setDeletedAt(request.getDeletedAt());
        alternateChannelSettings.setBrowser(request.getBrowser());
        alternateChannelSettings.setIp(request.getIp());
        alternateChannelSettings.setProducts(request.getProducts());

        repository.save(alternateChannelSettings);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<AlternateChannelSettings> updateAltChannelSettings(Integer id,
            AlternateChannelSettingsRequest request) {
        Optional<AlternateChannelSettings> result = repository.findById(id);

        if (result.isPresent()) {
            AlternateChannelSettings alternateChannelSettings = result.get();

            alternateChannelSettings.setStartDate(request.getStartDate());
            alternateChannelSettings.setEndDate(request.getEndDate());
            alternateChannelSettings.setEligibleAreaIds(request.getEligibleAreaIds());
            alternateChannelSettings.setEligibleOutletIds(request.getEligibleOutletIds());
            alternateChannelSettings.setStatus(request.isStatus());
            alternateChannelSettings.setCreatedAt(request.getCreatedAt());
            alternateChannelSettings.setUpdatedAt(request.getUpdatedAt());
            alternateChannelSettings.setDeletedAt(request.getDeletedAt());
            alternateChannelSettings.setBrowser(request.getBrowser());
            alternateChannelSettings.setIp(request.getIp());
            alternateChannelSettings.setProducts(request.getProducts());

            repository.save(alternateChannelSettings);

        } else {
            throw new ResourceNotFoundException("AlternateChannelSettings", "id", id);
        }

        return result;
    }

    @Override
    public void deleteAltChannelSettings(Integer id) {
        repository.deleteById(id);

    }

    @Override
    public AlternateChannelSettings getAltChannelSettingsById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AlternateChannelSettings", "id", id));
    }

    @Override
    public List<AlternateChannelSettings> getAllAltChannelSettings() {
        return repository.findAll();
    }

    @Override
    public Optional<AlternateChannelSettings> altChannelSettingsStatusChange(Integer id,
            AlternateChannelSettingsRequest request) {
        Optional<AlternateChannelSettings> result = repository.findById(id);

        if (result.isPresent()) {
            AlternateChannelSettings alternateChannelSettings = result.get();
            alternateChannelSettings.setStatus(request.isStatus());
            repository.save(alternateChannelSettings);

        } else {
            throw new ResourceNotFoundException("AlternateChannelSettings", "id", id);
        }

        return result;
    }

    @Override
    public Page<AlternateChannelSettings> findAltChannelSettingsByPagination(int offset, int pageSize) {
        Page<AlternateChannelSettings> alternateChannelSettings = repository.findAll(PageRequest.of(offset, pageSize));
        return alternateChannelSettings;
    }

    @Override
    public Page<AlternateChannelSettings> findSortedAltChannelSettingsByPagination(int offset, int pageSize,
            String field) {
        Page<AlternateChannelSettings> alternateChannelSettings = repository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return alternateChannelSettings;
    }
}
