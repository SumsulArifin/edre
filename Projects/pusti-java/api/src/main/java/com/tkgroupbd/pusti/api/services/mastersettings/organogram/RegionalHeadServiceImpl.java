package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.RegionalHead;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.RegionalHeadRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.RegionalHeadRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegionalHeadServiceImpl implements RegionalHeadService {

    @Autowired
    RegionalHeadRepository regionalHeadRepository;

    private LocalDate now() {
        return null;
    }

    @Override
    public MessageResponse saveRegionalHead(RegionalHeadRequest regionalHeadRequest) {
        RegionalHead regionalHead = new RegionalHead();
        regionalHead.setName(regionalHeadRequest.getName());
        regionalHead.setDivisionalHead(regionalHeadRequest.getDivisionalHead());
        regionalHead.setStatus(regionalHeadRequest.isStatus());
        regionalHead.setCreatedBy(regionalHeadRequest.getCreatedBy());
        regionalHead.setCreatedAt(regionalHeadRequest.getCreatedAt());
        regionalHead.setBrowser(regionalHeadRequest.getBrowser());
        regionalHead.setIp(regionalHeadRequest.getIp());
        regionalHeadRepository.save(regionalHead);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<RegionalHead> updateRegionalHead(int regionalHeadId, RegionalHeadRequest regionalHeadRequest) {
        Optional<RegionalHead> result = regionalHeadRepository.findById(regionalHeadId);
        if (result.isPresent()) {
            RegionalHead regionalHead = result.get();
            regionalHead.setRegionalHeadId(regionalHeadId);
            regionalHead.setName(regionalHeadRequest.getName());
            regionalHead.setDivisionalHead(regionalHeadRequest.getDivisionalHead());

            regionalHead.setStatus(regionalHeadRequest.isStatus());
            regionalHead.setUpdatedAt(now());
            regionalHead.setBrowser(regionalHeadRequest.getBrowser());
            regionalHead.setIp(regionalHeadRequest.getIp());

            regionalHeadRepository.save(regionalHead);
        } else {
            throw new ResourceNotFoundException("RegionalHead", "regionalHeadId", regionalHeadId);
        }

        return result;
    }

    @Override
    public void deleteRegionalHeadById(int regionalHeadId) {
        regionalHeadRepository.deleteById(regionalHeadId);
    }

    @Override
    public Optional<RegionalHead> updateRegionalHeadStatus(int regionalHeadId,
            RegionalHeadRequest regionalHeadRequest) {
        Optional<RegionalHead> regionalHead = regionalHeadRepository.findById(regionalHeadId);
        if (regionalHead.isEmpty()) {
            throw new ResourceNotFoundException("RegionalHead", "regionalHeadId", regionalHeadId);
        } else
            regionalHead.get().setStatus(regionalHeadRequest.isStatus());

        regionalHeadRepository.save(regionalHead.get());
        return regionalHead;
    }

    @Override
    public List<RegionalHead> findSortedRegionalHeadByKey(String keyName) {
        return regionalHeadRepository.findAll(Sort.by(Sort.Direction.ASC, keyName));
    }

    @Override
    public List<RegionalHead> getAllRegionalHeads() {
        return regionalHeadRepository.findAll();
    }

    @Override
    public RegionalHead findRegionalHeadById(int regionalHeadId) {
        return regionalHeadRepository.findById(regionalHeadId).get();
    }

    @Override
    public Page<RegionalHead> findRegionalHeadsByPagination(int offset, int pageSize) {
        Page<RegionalHead> regionalHeads = regionalHeadRepository.findAll(PageRequest.of(offset, pageSize));
        return regionalHeads;
    }
}
