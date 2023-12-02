package com.tkgroupbd.pusti.api.services.mastersettings.areas;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletType;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface OutletTypeService {
    public List<OutletType> getAllOutletTypes();
    public OutletType findOutletTypeById(int outletTypeId);
}
