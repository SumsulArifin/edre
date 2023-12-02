package com.tkgroupbd.pusti.api.seeders;

import com.tkgroupbd.pusti.api.data.models.enums.Role;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.Designation;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tkgroupbd.pusti.api.data.repositories.hrm.users.UserRepository;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.DesignationRepository;

@Component
public class DatabaseSeeder {
    private UserRepository userRepository;
    private DesignationRepository designationRepository;
    private OutletChannelSeeder outletChannelSeeder;
    private OutletTypeSeeder outletTypeSeeder;
    private DistrictSeeder districtSeeder;
    private UpazilaSeeder upazilaSeeder;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeeder(UserRepository userRepo, DesignationRepository designationRepo,
            OutletChannelSeeder channelSeeder, OutletTypeSeeder typeSeeder,
            DistrictSeeder objDistrictSeeder,
            UpazilaSeeder objUpazilaSeeder) {
        this.userRepository = userRepo;
        this.designationRepository = designationRepo;
        this.outletChannelSeeder = channelSeeder;
        this.outletTypeSeeder = typeSeeder;
        this.districtSeeder = objDistrictSeeder;
        this.upazilaSeeder = objUpazilaSeeder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedDesignationsTable();
        outletChannelSeeder.seedOutletChannelTable();
        outletTypeSeeder.seedOutletTypeTable();
        districtSeeder.seedDistrictTable();
        upazilaSeeder.seedUpazilaTable();
    }

    public void seedUsersTable() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUserName("tkgl@tkgl.com");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole(Role.ADMIN);

            userRepository.save(user);
        }
    }

    public void seedDesignationsTable() {

        List<Designation> designationList = new ArrayList();

        if (designationRepository.count() == 0) {
            Designation designation = new Designation();
            designation.setName("ASM");
            designation.setStatus(true);
            designationList.add(designation);

            designation = new Designation();
            designation.setName("DSM");
            designation.setStatus(true);
            designationList.add(designation);

            designation = new Designation();
            designation.setName("HOB");
            designation.setStatus(true);
            designationList.add(designation);

            designation = new Designation();
            designation.setName("HOS");
            designation.setStatus(true);
            designationList.add(designation);

            designation = new Designation();
            designation.setName("RSM");
            designation.setStatus(true);
            designationList.add(designation);

            designation = new Designation();
            designation.setName("SO");
            designation.setStatus(true);
            designationList.add(designation);

            designationRepository.saveAll(designationList);
        }
    }
}
