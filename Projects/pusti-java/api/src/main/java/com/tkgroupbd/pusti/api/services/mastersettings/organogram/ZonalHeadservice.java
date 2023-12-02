package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.ZonalHead;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.ZonalHeadRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface ZonalHeadservice {

    MessageResponse saveZonalHead(ZonalHeadRequest zonalHeadRequest);

    public Optional<ZonalHead> updateZonalHead(int zonalHeadId, ZonalHeadRequest zonalHeadRequest);

    public void deleteZonalHeadById(int zonalHeadId);

    public Optional<ZonalHead> updateZonalHeadStatus(int zonalHeadId, ZonalHeadRequest zonalHeadRequest);

    List<ZonalHead> findSortedZonalHeadByKey(String keyName);

    public List<ZonalHead> getAllZonalHeads();

    public ZonalHead findZonalHeadById(int zonalHeadId);

    public Page<ZonalHead> findZonalHeadsByPagination(int offset, int pageSize);
}
