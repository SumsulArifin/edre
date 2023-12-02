package com.tkgroupbd.pusti.api.services.mastersettings.areas;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.National;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.NationalRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.NationalRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NationalServiceImpl implements NationalService {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private NationalRepository nationalRepository;

    @Override
    public MessageResponse addNewNational(NationalRequest nationalRequest) {
        try {
            National national = new National();
            national.setNationalName(nationalRequest.getNationalName().trim());
            national.setStatus(nationalRequest.isStatus());
            national.setCreatedBy(nationalRequest.getCreatedBy());
            national.setUpdatedBy(nationalRequest.getUpdatedBy());

            nationalRepository.save(national);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }

    }

    @Override
    public Optional<National> updateNational(int nationalId, NationalRequest nationalRequest) {
        Optional<National> result = nationalRepository.findById(nationalId);
        if (result.isPresent()) {
            National national = result.get();
            national.setStatus(nationalRequest.isStatus());
            national.setNationalName(nationalRequest.getNationalName().trim().toUpperCase());
            national.setCreatedBy(nationalRequest.getCreatedBy());
            national.setUpdatedBy(nationalRequest.getUpdatedBy());
            national.setCreatedAt(nationalRequest.getCreatedAt());
            national.setUpdatedAt(nationalRequest.getUpdatedAt());
            national.setDeletedAt(nationalRequest.getDeletedAt());
            national.setBrowser(nationalRequest.getBrowser());
            national.setIp(nationalRequest.getIp());
            nationalRepository.save(national);
        } else {
            throw new ResourceNotFoundException("National", "nationalId", nationalId);
        }

        return result;
    }

    @Override
    public List<National> getAllNationals() {
        return nationalRepository.findAll();
    }

    @Override
    public National findNationalById(int nationalId) {
        return nationalRepository.findById(nationalId).get();
    }

    @Override
    public void deleteNationalId(int nationalId) {
        nationalRepository.deleteById(nationalId);
    }
}
