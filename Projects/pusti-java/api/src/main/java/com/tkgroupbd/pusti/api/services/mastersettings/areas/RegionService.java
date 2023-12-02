package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.RegionRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface RegionService {
    MessageResponse saveRegion(RegionRequest regionRequest);

    public Optional<Region> updateRegion(int regionId, RegionRequest regionRequest);

    public void deleteRegionById(int regionId);

    public Optional<Region> updateRegionStatus(int regionId, RegionRequest regionRequest);

    public List<Region> getAllRegions();

    public Region findRegionById(int regionId);

    public List<Region> getRegionByRegionName(String regionName);
    public ByteArrayInputStream generateToExcel() throws IOException;

    public List<Region> getAllRegionByRegionNameOrDivisionNameOrNationalName(String regionName);

    public Page<Region> findRegionByPagination(int offset, int pageSize);
}
