package com.tkgroupbd.pusti.api.seeders;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.District;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.DistrictRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistrictSeeder {

    @Autowired
    private DistrictRepository districtRepository;

    public void seedDistrictTable() {

        List<District> districtList = new ArrayList();
        District district = new District();
        district.setDistrictId(1);
        district.setDistrictName("Bagerhat");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(2);
        district.setDistrictName("Bandarban");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(3);
        district.setDistrictName("Barguna");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(4);
        district.setDistrictName("Barisal");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(5);
        district.setDistrictName("Bhola");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(6);
        district.setDistrictName("Bogra");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(7);
        district.setDistrictName("Brahmanbaria");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(8);
        district.setDistrictName("Chandpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(9);
        district.setDistrictName("Chapai Nawabgan");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(10);
        district.setDistrictName("Chattogram");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(11);
        district.setDistrictName("Chuadanga");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(12);
        district.setDistrictName("Comilla");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(13);
        district.setDistrictName("Cox''s Bazar");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(14);
        district.setDistrictName("Dhaka");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(15);
        district.setDistrictName("Dinajpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(16);
        district.setDistrictName("Faridpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(17);
        district.setDistrictName("Feni");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(18);
        district.setDistrictName("Gaibandha");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(19);
        district.setDistrictName("Gazipur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(20);
        district.setDistrictName("Gopalganj");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(21);
        district.setDistrictName("Habiganj");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(22);
        district.setDistrictName("Jamalpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(23);
        district.setDistrictName("Jashore");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(24);
        district.setDistrictName("Jhalokathi");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(25);
        district.setDistrictName("Jhenaidah");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(26);
        district.setDistrictName("Joypurhat");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(27);
        district.setDistrictName("Khagrachari");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(28);
        district.setDistrictName("Khulna");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(29);
        district.setDistrictName("Kishoreganj");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(30);
        district.setDistrictName("Kurigram");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(31);
        district.setDistrictName("Kushtia");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(32);
        district.setDistrictName("Lakshmipur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(33);
        district.setDistrictName("Lalmonirhat");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(34);
        district.setDistrictName("Madaripur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(35);
        district.setDistrictName("Magura");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(36);
        district.setDistrictName("Manikganj");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(37);
        district.setDistrictName("Meherpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(38);
        district.setDistrictName("Moulvibazar");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(39);
        district.setDistrictName("Munshiganj");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(40);
        district.setDistrictName("Mymensingh");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(41);
        district.setDistrictName("Naogaon");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(42);
        district.setDistrictName("Narail");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(43);
        district.setDistrictName("Narayanganj");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(44);
        district.setDistrictName("Narsingdi");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(45);
        district.setDistrictName("Natore");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(46);
        district.setDistrictName("Netrokona");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(47);
        district.setDistrictName("Nilphamari");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(48);
        district.setDistrictName("Noakhali");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(49);
        district.setDistrictName("Pabna");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(50);
        district.setDistrictName("Panchagarh");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(51);
        district.setDistrictName("Patuakhali");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(52);
        district.setDistrictName("Pirojpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(53);
        district.setDistrictName("Rajbari");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(54);
        district.setDistrictName("Rajshahi");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(55);
        district.setDistrictName("Rangamati");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(56);
        district.setDistrictName("Rangpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(57);
        district.setDistrictName("Satkhira");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(58);
        district.setDistrictName("Shariatpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(59);
        district.setDistrictName("Sherpur");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(60);
        district.setDistrictName("Sirajganj");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(61);
        district.setDistrictName("Sunamganj");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(62);
        district.setDistrictName("Sylhet");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(63);
        district.setDistrictName("Tangail");
        district.setStatus(true);
        districtList.add(district);

        district = new District();
        district.setDistrictId(64);
        district.setDistrictName("Thakurgaon");
        district.setStatus(true);
        districtList.add(district);

        if (districtRepository.count() < districtList.size()) {
            districtRepository.saveAll(districtList);
        }
    }
}
