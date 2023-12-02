package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.District;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.DistrictRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.DistrictRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService{

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictById(Integer districtId) {
        return districtRepository.findById(districtId).get();
    }

    @Override
    public List<District> getDistrictByName(String districtName) {

        List<District> district = districtRepository.findByDistrictNameContaining(districtName);
        Collections.sort(district, Comparator.comparing(District::getDistrictName));

        return district;
    }

    @Override
    public Page<District> findDistrictsByPagination(int offset, int pageSize) {
        Page<District> districts = districtRepository.findAll(PageRequest.of(offset, pageSize));
        return districts;
    }

    @Override
    public MessageResponse addNewDistrict(DistrictRequest districtRequest) {
        try {
            District newDistrict = new District();
            newDistrict.setDistrictName(districtRequest.getDistrictName());
            districtRepository.save(newDistrict);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }
    }

    @Override
    public Optional<District> updateDistrict(Integer districtId, DistrictRequest districtRequest) {
        Optional<District> update = districtRepository.findById(districtId);

        if (update.isPresent()) {
            District district = update.get();
            district.setDistrictName(districtRequest.getDistrictName());
            districtRepository.save(district);
        } else {
            throw new ResourceNotFoundException("District", "districtId", districtId);
        }
        return update;
    }

    @Override
    public void deleteDistrict(Integer districtId) {
        districtRepository.deleteById(districtId);
    }
}
