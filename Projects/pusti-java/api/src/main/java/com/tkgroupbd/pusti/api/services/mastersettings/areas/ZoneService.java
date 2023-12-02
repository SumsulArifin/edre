package com.tkgroupbd.pusti.api.services.mastersettings.areas;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.ZoneRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface ZoneService {
    MessageResponse createZone(ZoneRequest zoneRequest);
    Optional<Zone> updateZone(Integer id, ZoneRequest zoneRequest);
    void deleteZone(Integer id);
    Zone getZoneById(Integer id);
    List<Zone> getAllZones();
    List<Zone> findZoneByRegionId(Integer id);
    Optional<Zone> zoneStatusChange(Integer id, ZoneRequest zoneRequest);
    public List<Zone> getZoneByZoneName(String zoneName);
    public ByteArrayInputStream exportToExcel(List<Zone> zones)throws IOException;
    public List<Zone> getAllZoneByZoneNameOrRegionName(String regionName);

    public Page<Zone> findZonesByPagination(int offset, int pageSize);
}
