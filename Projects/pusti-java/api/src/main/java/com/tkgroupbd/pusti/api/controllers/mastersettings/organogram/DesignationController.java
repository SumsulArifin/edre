package com.tkgroupbd.pusti.api.controllers.mastersettings.organogram;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.organogram.Designation;
import com.tkgroupbd.pusti.api.services.mastersettings.organogram.DesignationService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Designation")
@RestController
@RequestMapping("/designation")
public class  DesignationController {
    @Autowired
    DesignationService designationService;

    @GetMapping("/getAllDesignations")
    public ResponseEntity<List<Designation>> getDistributor() {
        List<Designation> distributors = designationService.getAllDesignations();
        return new ResponseEntity<>(distributors, HttpStatus.OK);
    }

    @GetMapping("/getDistributorById/{designationId}")
    public ResponseEntity<Designation> getDistributorById(@PathVariable("designationId") Integer designationId) {
        Designation distributor = designationService.findDesignationById(designationId);
        return new ResponseEntity<>(distributor, HttpStatus.OK);
    }
}
