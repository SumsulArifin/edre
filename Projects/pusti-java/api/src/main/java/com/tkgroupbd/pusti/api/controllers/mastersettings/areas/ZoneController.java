package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.ApiAndMessageResponse;
import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.ZoneRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.ZoneService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

@Tag(name = "Zone")
@RestController
@RequestMapping("/zone")
public class ZoneController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private ZoneService zoneService;

    @GetMapping("/getAllZones")
    public ResponseEntity<List<Zone>> getZones() {
        List<Zone> zone = zoneService.getAllZones();
        return new ResponseEntity<>(zone, HttpStatus.OK);
    }

    @PostMapping("/addNewZone")

    public ResponseEntity<MessageResponse> addZones(@RequestBody @Valid ZoneRequest zoneRequest) {
        MessageResponse newZones = zoneService.createZone(zoneRequest);

        return new ResponseEntity<>(newZones, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteZoneById/{id}")
    public ResponseEntity<?> deleteZones(@PathVariable("id") Integer id) {
        zoneService.deleteZone(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getZoneById/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable("id") Integer id) {
        Zone zone = zoneService.getZoneById(id);
        return new ResponseEntity<>(zone, HttpStatus.OK);
    }

    @PutMapping("/updateZone/{id}")

    public ResponseEntity<Optional<Zone>> updateZone(@PathVariable Integer id,
            @RequestBody @Valid ZoneRequest zoneRequest) {
        Optional<Zone> zone = zoneService.updateZone(id, zoneRequest);
        return new ResponseEntity<Optional<Zone>>(zone, HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<Optional<Zone>> updateZoneStatus(@PathVariable Integer id,
            @RequestBody ZoneRequest zoneRequest) {
        Optional<Zone> updateZone = zoneService.zoneStatusChange(id, zoneRequest);
        return new ResponseEntity<Optional<Zone>>(updateZone, HttpStatus.OK);
    }

    @GetMapping(value = "/downloadExcel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadExcel() throws IOException {
        List<Zone> zones = zoneService.getAllZones();
        ByteArrayInputStream excelStream = zoneService.exportToExcel(zones);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=zones.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excelStream.readAllBytes());
    }

//    @GetMapping("/getZoneByZoneName")
//    private ApiResponse<List<Zone>> getZoneByZoneName(@RequestParam("field") String field) {
//        List<Zone> zoneList = zoneService.getZoneByZoneName(field);
//        return new ApiResponse(zoneList.size(), zoneList);
//    }

    @GetMapping("/getAllZoneByZoneNameOrRegionName")
    private ApiResponse<List<Zone>> getAllZoneByZoneNameOrRegionName(@RequestParam("field") String field) {
        List<Zone> zoneList = zoneService.getAllZoneByZoneNameOrRegionName(field);
        return new ApiResponse(zoneList.size(),zoneList);
    }

    @GetMapping("/getPaginatedZones")
    private ResponseEntity<Page<Zone>> getPaginatedZones(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Zone> paginatedZones = zoneService.findZonesByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedZones,HttpStatus.OK);
    }
}
