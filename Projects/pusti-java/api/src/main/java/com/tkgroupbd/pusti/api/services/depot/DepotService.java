package com.tkgroupbd.pusti.api.services.depot;

import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.payloads.dto.depot.DepotRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DepotService {
    MessageResponse addDepot(DepotRequest depotRequest);

    Optional<Depot> updateDepot(Integer depotId, DepotRequest depotRequest);

    void deleteDepot(Integer depotId);

    Depot getDeportById(Integer depotId);

    List<Depot> getAllDepots();

    List<Depot> findSortedDepotsByKey(String field);

    Page<Depot> findDepotByPagination(int offset, int pageSize);

    Page<Depot> findSortedDepotByPagination(int offset, int pageSize, String field);

    List<Depot> findDepotByDepotName(String name);
    public Optional<Depot> statusChangeDepot(Integer depotId, DepotRequest depotRequest);
}
