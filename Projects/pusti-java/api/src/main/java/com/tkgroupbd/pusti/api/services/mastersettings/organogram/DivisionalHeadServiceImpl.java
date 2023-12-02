package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.DivisionalHead;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.DivisionalHeadRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.DivisionalHeadRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DivisionalHeadServiceImpl implements DivisionalHeadService {
    @Autowired
    DivisionalHeadRepository divisionalHeadRepository;

    @Override
    public MessageResponse addDivisionalHead(DivisionalHeadRequest divisionalHeadRequest) {
        DivisionalHead divisionalHead = new DivisionalHead();
        divisionalHead.setName(divisionalHeadRequest.getName());
        divisionalHead.setStatus(divisionalHeadRequest.isStatus());
        divisionalHead.setCreatedBy(divisionalHeadRequest.getCreatedBy());
        divisionalHead.setUpdatedBy(divisionalHeadRequest.getUpdatedBy());
        divisionalHead.setCreatedAt(divisionalHeadRequest.getCreatedAt());
        divisionalHead.setUpdatedAt(divisionalHeadRequest.getUpdatedAt());
        divisionalHead.setDeletedAt(divisionalHeadRequest.getDeletedAt());
        divisionalHead.setBrowser(divisionalHeadRequest.getBrowser());
        divisionalHead.setIp(divisionalHeadRequest.getIp());
        divisionalHeadRepository.save(divisionalHead);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public List<DivisionalHead> getAllDivisionalHeads() {
        return divisionalHeadRepository.findAll();
    }

    @Override
    public Optional<DivisionalHead> updateDivisionalHead(int divisionalHeadId,
            DivisionalHeadRequest divisionalHeadRequest) {
        Optional<DivisionalHead> result = divisionalHeadRepository.findById(divisionalHeadId);

        if (result.isPresent()) {
            DivisionalHead divisionalHead = result.get();
            divisionalHead.setName(divisionalHeadRequest.getName());
            divisionalHead.setStatus(divisionalHeadRequest.isStatus());
            divisionalHead.setCreatedBy(divisionalHeadRequest.getCreatedBy());
            divisionalHead.setUpdatedBy(divisionalHeadRequest.getUpdatedBy());
            divisionalHead.setCreatedAt(divisionalHeadRequest.getCreatedAt());
            divisionalHead.setUpdatedAt(divisionalHeadRequest.getUpdatedAt());
            divisionalHead.setDeletedAt(divisionalHeadRequest.getDeletedAt());
            divisionalHead.setBrowser(divisionalHeadRequest.getBrowser());
            divisionalHead.setIp(divisionalHeadRequest.getIp());

            divisionalHeadRepository.save(divisionalHead);
        } else {
            throw new ResourceNotFoundException("DivisionalHead", "divisionHeadId", divisionalHeadId);
        }

        return result;
    }

    @Override
    public DivisionalHead findById(int divisionalHeadId) {
        return divisionalHeadRepository.findById(divisionalHeadId).get();
    }

    @Override
    public void deleteDivisionalHeadById(int divisionalHeadId) {
        divisionalHeadRepository.deleteById(divisionalHeadId);

    }

    @Override
    public Optional<DivisionalHead> updateDivisionalHeadStatus(int divisionalHeadId,
            DivisionalHeadRequest divisionalHeadRequest) {
        Optional<DivisionalHead> result = divisionalHeadRepository.findById(divisionalHeadId);
        DivisionalHead divisionalHead = null;
        if (result.isPresent()) {
            divisionalHead = result.get();
            divisionalHead.setStatus(divisionalHeadRequest.isStatus());
            divisionalHeadRepository.save(divisionalHead);
        } else {
            throw new ResourceNotFoundException("DivisionalHead", "divisionalHeadId", divisionalHeadId);
        }

        return result;
    }
}