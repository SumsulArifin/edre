package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.Distributor;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.organogram.DistributorRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface DistributorService {

    MessageResponse saveDistributor(DistributorRequest distributorsRequest);

    public Optional<Distributor> updateDistributor(int distributor_id, DistributorRequest distributorsRequest);

    public void deleteDistributor(int distributorId);

    public Optional<Distributor> updateDistributorStatus(int distributor_id, DistributorRequest distributorsRequest);

    List<Distributor> findSortedDistributorByKey(String field);

    public List<Distributor> getAllDistributors();

    public Distributor findDistributorById(int distributor_id);

    public List<Distributor> getDistributorByDistributorName(String distributorName);

    public List<Distributor> getDistributorByZoneName(String distributorName);

    public List<Distributor> getDistributorsByUpazilaId(int upazilaId);

    public Page<Distributor> findDistributorsByPagination(int offset, int pageSize);
}
