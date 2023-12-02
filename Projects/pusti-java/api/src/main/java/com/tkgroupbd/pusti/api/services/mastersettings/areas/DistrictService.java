package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.District;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.DistrictRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DistrictService {
    public List<District> getAllDistricts();

    public District getDistrictById(Integer districtId);

    MessageResponse addNewDistrict(DistrictRequest districtRequest);

    public Optional<District> updateDistrict(Integer districtId, DistrictRequest districtRequest);

    public void deleteDistrict(Integer districtId);

    public List<District> getDistrictByName(String districtName);

    public Page<District> findDistrictsByPagination(int offset, int pageSize);
}
