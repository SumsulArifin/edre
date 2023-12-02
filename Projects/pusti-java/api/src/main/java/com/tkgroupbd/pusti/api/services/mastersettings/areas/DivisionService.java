package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Division;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.DivisionRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface DivisionService {

    MessageResponse saveDivision(DivisionRequest divisionRequest);

    public Optional<Division> updateDivision(int divisionId, DivisionRequest division);

    public void deleteDivisionById(int divisionId);

    public Optional<Division> updateDivisionStatus(int divisionId, DivisionRequest divisionRequest);

    List<Division> findSortedDivisionByKey(String field);

    public List<Division> getAllDivisions();

    public Division findDivisionById(int divisionId);

    public List<Division> getDivisionByDivisionName(String divisionName);

    ByteArrayInputStream generateToExcel() throws IOException;
}
