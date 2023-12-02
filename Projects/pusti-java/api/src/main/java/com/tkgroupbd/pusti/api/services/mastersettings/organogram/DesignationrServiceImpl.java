package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.Designation;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DesignationrServiceImpl implements DesignationService {

    @Autowired
    DesignationRepository designationRepository;

    @Override
    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    @Override
    public Designation findDesignationById(int distributor_id) {
        return designationRepository.findById(distributor_id).get();
    }
}
