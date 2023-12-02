package com.tkgroupbd.pusti.api.services.mastersettings.areas;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.National;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.NationalRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface NationalService {
    MessageResponse addNewNational(NationalRequest nationalRequest);
    public Optional<National> updateNational(int nationalId, NationalRequest nationalRequest);
    public List<National> getAllNationals();
    public National findNationalById(int nationalId);
    public void deleteNationalId(int nationalId);
}
