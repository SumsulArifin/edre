package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.Designation;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface DesignationService {

    public List<Designation> getAllDesignations();

    public Designation findDesignationById(int designationId);
}
