package com.tkgroupbd.pusti.api.services.mastersettings.areas;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletType;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.OutletTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OutletTypeServiceImpl implements OutletTypeService {
    @Autowired
    OutletTypeRepository outletTypeRepository;
    @Override
    public List<OutletType> getAllOutletTypes() {
        return outletTypeRepository.findAll();
    }
    @Override
    public OutletType findOutletTypeById(int outletTypeId) {
        return outletTypeRepository.findById(outletTypeId).get();
    }
}
