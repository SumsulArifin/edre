package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.National;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.NationalRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.NationalService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name = "National")
@RestController
@CacheConfig(cacheNames = "national")
@RequestMapping("/national")
public class NationalController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private NationalService nationalService;

    @PostMapping("/addNewNational")
    public ResponseEntity<MessageResponse> saveNational(@RequestBody @Valid NationalRequest nationalRequest) {
        MessageResponse response = nationalService.addNewNational(nationalRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateNational/{nationalId}")
    public ResponseEntity<Optional<National>> updateNational(@PathVariable int nationalId,
            @RequestBody @Valid NationalRequest nationalRequest) {
        Optional<National> national = nationalService.updateNational(nationalId, nationalRequest);
        return new ResponseEntity<Optional<National>>(national, HttpStatus.OK);

    }

    @DeleteMapping("/deleteNational/{nationalId}")
    public ResponseEntity<?> deleteNationalId(@PathVariable("nationalId") Integer nationalId) {
        nationalService.deleteNationalId(nationalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAllNationals")
    public ResponseEntity<List<National>> getAllNational() {
        List<National> nationalList = nationalService.getAllNationals();
        return new ResponseEntity<>(nationalList, HttpStatus.OK);
    }

    @GetMapping("/getNationalById/{nationalId}")
    public ResponseEntity<National> getNationalById(@PathVariable("nationalId") Integer nationalId) {
        National national = nationalService.findNationalById(nationalId);
        return new ResponseEntity<>(national, HttpStatus.OK);
    }
}
