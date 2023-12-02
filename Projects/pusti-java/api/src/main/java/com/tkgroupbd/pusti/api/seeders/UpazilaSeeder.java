package com.tkgroupbd.pusti.api.seeders;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.District;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Upazila;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.UpazilaRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpazilaSeeder {

    @Autowired
    private UpazilaRepository upazilaRepository;

    public void seedUpazilaTable() {

        List<Upazila> upazilaList = new ArrayList();

        District district = new District();
        district.setDistrictId(1);

        Upazila upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Sadar Upazila");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Sarankhola");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(2);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Hathazari");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Thanchi");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(3);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Amtali");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(4);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Barisal Sadar");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(5);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Bhola Sadar");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(6);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Adamdighi");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(7);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Bancharampur");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(8);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Matlab Uttar");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(9);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Gomastapur");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(10);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Anwara");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(11);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Damurhuda");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(12);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Comilla Sadar");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(13);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Kutubdia");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(14);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Dhamrai");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Tejgaon");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Mohammadpur");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(15);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Birampur");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(16);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Faridpur Sadar");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(17);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Feni Sadar");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(18);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Palashbari");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(19);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Siddhirganj");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        district = new District();
        district.setDistrictId(20);

        upazila = new Upazila();
        upazila.setDistrict(district);
        upazila.setUpazilaName("Gopalganj Sadar");
        upazila.setStatus(true);
        upazilaList.add(upazila);

        if (upazilaRepository.count() < upazilaList.size()) {
            upazilaRepository.saveAll(upazilaList);
        }
    }
}
