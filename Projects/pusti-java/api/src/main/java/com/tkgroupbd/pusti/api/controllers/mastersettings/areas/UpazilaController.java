package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Upazila;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.UpazilaRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.UpazilaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name = "Upazila")
@RestController
@RequestMapping("/upazila")
public class UpazilaController {

    @Autowired
    private UpazilaService upazilaService;

    @GetMapping("/getAllUpazilas")
    public ResponseEntity<List<Upazila>> getUpazila() {
        List<Upazila> upazilas = upazilaService.getAllUpazilas();
        return new ResponseEntity<>(upazilas, HttpStatus.OK);
    }

    @GetMapping("/getUpazilaById/{upazilaId}")
    public ResponseEntity<Upazila> getUpazilaById(@PathVariable("upazilaId") Integer upazilaId) {
        Upazila upazila = upazilaService.findUpazilaById(upazilaId);
        return new ResponseEntity<>(upazila, HttpStatus.OK);
    }

    @GetMapping("/getUpazilaByName")
    public ResponseEntity<List<Upazila>> getUpazilaByName(@RequestParam("upazilaName") String upazilaName) {
        List<Upazila> upazila = upazilaService.getUpazilaByName(upazilaName);
        return new ResponseEntity<>(upazila, HttpStatus.OK);
    }

    @PutMapping("/updateUpazila/{upazilaId}")
    public ResponseEntity<Optional<Upazila>> updateUpazila(@PathVariable("upazilaId") Integer upazilaId, @RequestBody UpazilaRequest upazilaRequest) {
        Optional<Upazila> upazila = upazilaService.updateUpazila(upazilaId, upazilaRequest);
        return new ResponseEntity<>(upazila,HttpStatus.OK);
    }

    @PutMapping("/changeStatus/{upazilaId}")
    public ResponseEntity<Optional<Upazila>> updateUpazilaStatus(
            @PathVariable("upazilaId") Integer upazilaId, @RequestBody UpazilaRequest upazilaRequest) {
        Optional<Upazila> upazila = upazilaService.updateUpazilaStatus(upazilaId, upazilaRequest);
        return new ResponseEntity<>(upazila, HttpStatus.OK);
    }

    @PostMapping("/addNewUpazila")
    public ResponseEntity<MessageResponse> addNewUpazila(@RequestBody @Valid UpazilaRequest upazilaRequest) {
        MessageResponse newUpazila = upazilaService.addNewUpazila(upazilaRequest);
        return new ResponseEntity<>(newUpazila, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUpazila/{upazilaId}")
    public ResponseEntity<?> deleteUpazila(@PathVariable("upazilaId") Integer upazilaId) {
        upazilaService.deleteUpazila(upazilaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getPaginatedUpazilas")
    private ResponseEntity<Page<Upazila>> getPaginatedUpazilas(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Upazila> paginatedUpazilas = upazilaService.findUpazilasByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedUpazilas,HttpStatus.OK);
    }
}
