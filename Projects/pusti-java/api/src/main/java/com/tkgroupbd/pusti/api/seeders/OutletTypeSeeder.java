package com.tkgroupbd.pusti.api.seeders;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.OutletType;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.OutletTypeRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OutletTypeSeeder {

    private OutletTypeRepository outletTypeRepository;

    @Autowired
    public OutletTypeSeeder(OutletTypeRepository outletTypeRepo) {
        this.outletTypeRepository = outletTypeRepo;
    }

    public void seedOutletTypeTable() {

        List<OutletType> outletTypeList = new ArrayList();

        OutletType outletType = new OutletType();
        outletType.setTypeName("NA");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Backery");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Coffee Shop");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Confectionery");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Cosmetic");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Departmental Store");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Doctors");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Flexi Load");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("General Store");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Grocery");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Hotel or Restaurant");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Institutions");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Library");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Neighbourhood");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Pharmacy");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Photostat");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Professionals");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("SPO");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Stationary");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Tea, Tobaco and Tong(TTT)");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        outletType = new OutletType();
        outletType.setTypeName("Wet Market");
        outletType.setStatus(true);
        outletTypeList.add(outletType);

        if (outletTypeRepository.count() < outletTypeList.size()) {
            outletTypeRepository.saveAll(outletTypeList);
        }
    }
}
