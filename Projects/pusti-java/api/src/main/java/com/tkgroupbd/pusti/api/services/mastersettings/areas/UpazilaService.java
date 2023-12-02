package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Upazila;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.UpazilaRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UpazilaService {
    public List<Upazila> getAllUpazilas();

    public Upazila findUpazilaById(int upazilaId);

    public List<Upazila> getUpazilaByName(String upazilaName);

    public Optional<Upazila> updateUpazila(Integer upazilaId, UpazilaRequest upazilaRequest);

    public Optional<Upazila> updateUpazilaStatus(int upazilaId, UpazilaRequest upazilaRequest);

    MessageResponse addNewUpazila(UpazilaRequest upazilaRequest);

    public void deleteUpazila(Integer upazilaId);

    public Page<Upazila> findUpazilasByPagination(int offset, int pageSize);
}
