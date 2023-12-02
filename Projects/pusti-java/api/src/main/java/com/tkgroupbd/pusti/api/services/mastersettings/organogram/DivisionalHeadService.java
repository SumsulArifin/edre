package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.DivisionalHead;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.DivisionalHeadRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface DivisionalHeadService {
        public MessageResponse addDivisionalHead(DivisionalHeadRequest divisionalHeadRequest);

        public List<DivisionalHead> getAllDivisionalHeads();

        public Optional<DivisionalHead> updateDivisionalHead(int divisionalHeadId,
                        DivisionalHeadRequest divisionalHeadRequest);

        public DivisionalHead findById(int divisionalHeadId);

        public void deleteDivisionalHeadById(int divisionalHeadId);

        public Optional<DivisionalHead> updateDivisionalHeadStatus(int divisionalHeadId,
                        DivisionalHeadRequest divisionalHeadRequest);
}
