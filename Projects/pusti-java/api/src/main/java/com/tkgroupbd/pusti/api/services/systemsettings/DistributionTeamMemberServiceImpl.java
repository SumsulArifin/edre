package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributionTeamMember;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributionTeamMemberRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.systemsettings.DistributionTeamMemberRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributionTeamMemberServiceImpl implements DistributionTeamMemberService {
    @Autowired
    DistributionTeamMemberRepository repository;

    @Override
    public MessageResponse createDistributionTeamMember(DistributionTeamMemberRequest request) {
        DistributionTeamMember teamMember = new DistributionTeamMember();

        teamMember.setStatus(request.isStatus());
        teamMember.setCreatedAt(request.getCreatedAt());
        teamMember.setUpdatedAt(request.getUpdatedAt());
        teamMember.setDeletedAt(request.getDeletedAt());
        teamMember.setBrowser(request.getBrowser());
        teamMember.setIp(request.getIp());
        teamMember.setTeam(request.getTeam());
        teamMember.setEmployee(request.getEmployee());

        repository.save(teamMember);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public Optional<DistributionTeamMember> updateDistributionTeamMember(Integer id,
            DistributionTeamMemberRequest request) {
        Optional<DistributionTeamMember> result = repository.findById(id);

        if (result.isPresent()) {
            DistributionTeamMember teamMember = result.get();

            teamMember.setStatus(request.isStatus());
            teamMember.setCreatedAt(request.getCreatedAt());
            teamMember.setUpdatedAt(request.getUpdatedAt());
            teamMember.setDeletedAt(request.getDeletedAt());
            teamMember.setBrowser(request.getBrowser());
            teamMember.setIp(request.getIp());
            teamMember.setTeam(request.getTeam());
            teamMember.setEmployee(request.getEmployee());

            repository.save(teamMember);

        } else {
            throw new ResourceNotFoundException("DistributionTeamMember", "id", id);
        }

        return result;
    }

    @Override
    public void deleteDistributionTeamMember(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public DistributionTeamMember getDistributionTeamMemberById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DistributionTeamMember", "id", id));
    }

    @Override
    public List<DistributionTeamMember> getAllDistributionTeamMember() {
        return repository.findAll();
    }

    @Override
    public Optional<DistributionTeamMember> distributionTeamMemberStatusChange(Integer id,
            DistributionTeamMemberRequest request) {
        Optional<DistributionTeamMember> result = repository.findById(id);

        if (result.isPresent()) {
            DistributionTeamMember teamMember = result.get();

            teamMember.setStatus(request.isStatus());
            repository.save(teamMember);

        } else {
            throw new ResourceNotFoundException("DistributionTeamMember", "id", id);
        }

        return result;
    }

    @Override
    public Page<DistributionTeamMember> findDistributionTeamMemberByPagination(int offset, int pageSize) {
        Page<DistributionTeamMember> teamMembers = repository.findAll(PageRequest.of(offset, pageSize));
        return teamMembers;
    }

    @Override
    public Page<DistributionTeamMember> findSortedDistributionTeamMemberByPagination(int offset, int pageSize,
            String field) {
        Page<DistributionTeamMember> teamMembers = repository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return teamMembers;
    }
}
