package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.RegionalHead;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.RegionalHeadRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface RegionalHeadService {

    MessageResponse saveRegionalHead(RegionalHeadRequest regionalHeadRequest);

    public Optional<RegionalHead> updateRegionalHead(int regionalHeadId, RegionalHeadRequest regionalHeadRequest);

    public void deleteRegionalHeadById(int regionalHeadId);

    public Optional<RegionalHead> updateRegionalHeadStatus(int regionalHeadId,
            RegionalHeadRequest regionalHeadRequest);

    List<RegionalHead> findSortedRegionalHeadByKey(String keyName);

    public List<RegionalHead> getAllRegionalHeads();

    public RegionalHead findRegionalHeadById(int regionalHeadId);

    public Page<RegionalHead> findRegionalHeadsByPagination(int offset, int pageSize);
}
