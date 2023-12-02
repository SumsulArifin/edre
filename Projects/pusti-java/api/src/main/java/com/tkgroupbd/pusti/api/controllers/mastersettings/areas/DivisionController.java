package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;
import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Division;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.DivisionRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.DivisionService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Division")
@RestController
@RequestMapping("/division")
public class DivisionController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    DivisionService divisionService;

    // Create a new Division

    @PostMapping("/addNewDivision")
    public ResponseEntity<MessageResponse> divisionSave(@RequestBody @Valid DivisionRequest divisionRequest) {
        MessageResponse newDivision = divisionService.saveDivision(divisionRequest);
        return new ResponseEntity<>(newDivision, HttpStatus.CREATED);
    }

    // retrieve all Division
    @GetMapping("/getAllDivisions")
    @ResponseBody
    public ResponseEntity<List<Division>> getAllDivisions() {
        List<Division> divisions = divisionService.getAllDivisions();
        return new ResponseEntity<>(divisions, HttpStatus.OK);
    }

    // Update a Division information
    @PutMapping("/updateDivision/{divisionId}")
    public ResponseEntity<Optional<Division>> updateDivision(@PathVariable Integer divisionId,
            @RequestBody @Valid DivisionRequest divisionRequest) {
        Optional<Division> updateDivision = divisionService.updateDivision(divisionId, divisionRequest);
        return new ResponseEntity<Optional<Division>>(updateDivision, HttpStatus.OK);
    }

    // Division Status Change API
    @PutMapping("/statusChange/{divisionId}")
    public ResponseEntity<Optional<Division>> changeDivisionStatus(@PathVariable int divisionId,
            @RequestBody DivisionRequest divisionRequest) {
        Optional<Division> updateDivision = divisionService.updateDivisionStatus(divisionId, divisionRequest);
        return new ResponseEntity<Optional<Division>>(updateDivision, HttpStatus.OK);
    }

    // Delete Division by id
    @DeleteMapping("/deleteDivision/{divisionId}")
    public ResponseEntity<?> deleteDivisionById(@PathVariable("divisionId") Integer divisionId) {
        divisionService.deleteDivisionById(divisionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve Division by id
    @GetMapping("/getDivisionById/{divisionId}")
    public ResponseEntity<Division> getDivisionById(@PathVariable("divisionId") Integer divisionId) {
        Division division = divisionService.findDivisionById(divisionId);
        return new ResponseEntity<>(division, HttpStatus.OK);
    }
    

    @GetMapping("/getDivisionByDivisionName")
    private ApiResponse<List<Division>> getDivisionByDivisionName(@RequestParam("field") String field) {
        List<Division> divisionList = divisionService.getDivisionByDivisionName(field);
        return new ApiResponse(divisionList.size(), divisionList);
    }

    @GetMapping("/excelDownload")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
        ByteArrayInputStream stream = divisionService.generateToExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=divisions.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheettml.sheet")
                )
                .body(new InputStreamResource(stream));
    }

}
