package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.DistributionTeamMember;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.DistributionTeamMemberRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DistributionTeamMemberService {

    MessageResponse createDistributionTeamMember(DistributionTeamMemberRequest request);

    Optional<DistributionTeamMember> updateDistributionTeamMember(Integer id, DistributionTeamMemberRequest request);

    void deleteDistributionTeamMember(Integer id);

    DistributionTeamMember getDistributionTeamMemberById(Integer id);

    List<DistributionTeamMember> getAllDistributionTeamMember();

    Optional<DistributionTeamMember> distributionTeamMemberStatusChange(Integer id,
            DistributionTeamMemberRequest request);

    Page<DistributionTeamMember> findDistributionTeamMemberByPagination(int offset, int pageSize);

    Page<DistributionTeamMember> findSortedDistributionTeamMemberByPagination(int offset, int pageSize, String field);

}
