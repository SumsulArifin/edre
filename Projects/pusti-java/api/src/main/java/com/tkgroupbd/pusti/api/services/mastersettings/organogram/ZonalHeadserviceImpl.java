package com.tkgroupbd.pusti.api.services.mastersettings.organogram;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.ZonalHead;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.ZonalHeadRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.ZonalHeadRepository;
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
public class ZonalHeadserviceImpl implements ZonalHeadservice {

    @Autowired
    ZonalHeadRepository zonalHeadRepository;

    private LocalDate now() {
        return null;
    }

    @Override
    public MessageResponse saveZonalHead(ZonalHeadRequest zonalHeadRequest) {
        ZonalHead regionalHead = new ZonalHead();
        regionalHead.setName(zonalHeadRequest.getName());
        regionalHead.setRegionalHead(zonalHeadRequest.getRegionalHead());
        regionalHead.setStatus(zonalHeadRequest.isStatus());
        regionalHead.setCreatedBy(zonalHeadRequest.getCreatedBy());
        regionalHead.setCreatedAt(zonalHeadRequest.getCreatedAt());
        regionalHead.setBrowser(zonalHeadRequest.getBrowser());
        regionalHead.setIp(zonalHeadRequest.getIp());
        zonalHeadRepository.save(regionalHead);

        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<ZonalHead> updateZonalHead(int zonalHeadId, ZonalHeadRequest zonalHeadRequest) {
        Optional<ZonalHead> result = zonalHeadRepository.findById(zonalHeadId);
        if (result.isPresent()) {
            ZonalHead zonalHead = result.get();
            zonalHead.setZonalHeadId(zonalHeadId);
            zonalHead.setName(zonalHeadRequest.getName());
            zonalHead.setRegionalHead(zonalHeadRequest.getRegionalHead());

            zonalHead.setStatus(zonalHeadRequest.isStatus());
            zonalHead.setUpdatedAt(now());
            zonalHead.setBrowser(zonalHeadRequest.getBrowser());
            zonalHead.setIp(zonalHeadRequest.getIp());

            zonalHeadRepository.save(zonalHead);
        } else {
            throw new ResourceNotFoundException("ZonalHead", "zonalHeadId", zonalHeadId);
        }

        return result;
    }

    @Override
    public void deleteZonalHeadById(int zonalHeadId) {
        zonalHeadRepository.deleteById(zonalHeadId);
    }

    @Override
    public Optional<ZonalHead> updateZonalHeadStatus(int zonalHeadId, ZonalHeadRequest zonalHeadRequest) {
        Optional<ZonalHead> zonalHead = zonalHeadRepository.findById(zonalHeadId);
        if (zonalHead.isEmpty()) {
            throw new ResourceNotFoundException("ZonalHead", "zonalHeadId", zonalHeadId);
        } else
            zonalHead.get().setStatus(zonalHeadRequest.isStatus());

        zonalHeadRepository.save(zonalHead.get());
        return zonalHead;
    }

    @Override
    public List<ZonalHead> findSortedZonalHeadByKey(String keyName) {
        return zonalHeadRepository.findAll(Sort.by(Sort.Direction.ASC, keyName));
    }

    @Override
    public List<ZonalHead> getAllZonalHeads() {
        return zonalHeadRepository.findAll();
    }

    @Override
    public ZonalHead findZonalHeadById(int zonalHeadId) {
        return zonalHeadRepository.findById(zonalHeadId).get();
    }

    @Override
    public Page<ZonalHead> findZonalHeadsByPagination(int offset, int pageSize) {
        Page<ZonalHead> zonalHeads = zonalHeadRepository.findAll(PageRequest.of(offset, pageSize));
        return zonalHeads;
    }
}
