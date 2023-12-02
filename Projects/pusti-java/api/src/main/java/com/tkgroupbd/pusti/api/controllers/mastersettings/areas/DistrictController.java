package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.District;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.DistrictRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.DistrictService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "District")
@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private DistrictService districtService;

    @GetMapping("/getAllDistricts")
    public ResponseEntity<List<District>> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/getDistrictById/{districtId}")
    public ResponseEntity<District> getDistrictById(@PathVariable("districtId") Integer districtId) {
        District district = districtService.getDistrictById(districtId);
        return new ResponseEntity<>(district, HttpStatus.OK);
    }

    @GetMapping("/getDistrictByName")
    public ResponseEntity<List<District>> getDistrictByName(@RequestParam("districtName") String districtName) {
        List<District> district = districtService.getDistrictByName(districtName);
        return new ResponseEntity<>(district, HttpStatus.OK);
    }

    @PostMapping("/addNewDistrict")
    public ResponseEntity<MessageResponse> addNewDistrict(@RequestBody @Valid DistrictRequest districtRequest) {
        MessageResponse newDistrict = districtService.addNewDistrict(districtRequest);
        return new ResponseEntity<>(newDistrict,HttpStatus.CREATED);
    }

    @PutMapping("updateDistrict/{districtId}")
    public ResponseEntity<Optional<District>> updateDistrict(@PathVariable Integer districtId, @RequestBody @Valid DistrictRequest districtRequest) {
        Optional<District> updateDistrict = districtService.updateDistrict(districtId, districtRequest);
        return new ResponseEntity<>(updateDistrict, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDistrict/{districtId}")
    public ResponseEntity<?> deleteDistrict(@PathVariable("districtId") Integer districtId) {
        districtService.deleteDistrict(districtId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getPaginatedDistricts")
    private ResponseEntity<Page<District>> getPaginatedDistricts(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<District> paginatedDistricts = districtService.findDistrictsByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedDistricts,HttpStatus.OK);
    }
}
