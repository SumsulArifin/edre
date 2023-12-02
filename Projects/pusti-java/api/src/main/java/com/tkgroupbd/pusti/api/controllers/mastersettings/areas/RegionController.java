package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.ApiAndMessageResponse;
import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.RegionRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.RegionService;

import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Region")
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private RegionService regionService;

    @PostMapping("/addNewRegion")
    public ResponseEntity<MessageResponse> regionSave(@RequestBody @Valid RegionRequest regionRequest) {
        MessageResponse newRegion = regionService.saveRegion(regionRequest);
        return new ResponseEntity<>(newRegion, HttpStatus.CREATED);
    }

    @GetMapping("/getAllRegions")
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> region = regionService.getAllRegions();
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRegionById/{regionId}")
    public ResponseEntity<?> deleteZones(@PathVariable("regionId") Integer regionId) {
        regionService.deleteRegionById(regionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getRegionById/{regionId}")
    public ResponseEntity<Region> getRegionById(@PathVariable("regionId") Integer regionId) {
        Region region = regionService.findRegionById(regionId);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @PutMapping("/updateRegion/{regionId}")
    public ResponseEntity<Optional<Region>> updateZone(@PathVariable Integer regionId,
                                                       @RequestBody @Valid RegionRequest regionRequest) {
        Optional<Region> region = regionService.updateRegion(regionId, regionRequest);
        return new ResponseEntity<Optional<Region>>(region, HttpStatus.OK);
    }

    @PutMapping("/statusChange/{regionId}")
    public ResponseEntity<Optional<Region>> updateZoneStatus(@PathVariable Integer regionId,
                                                             @RequestBody RegionRequest regionRequest) {
        Optional<Region> region = regionService.updateRegionStatus(regionId, regionRequest);
        return new ResponseEntity<Optional<Region>>(region, HttpStatus.OK);
    }

//    @GetMapping("/getRegionByRegionName")
//    private ApiResponse<List<Region>> getRegionByRegionName(@RequestParam("field") String field) {
//        List<Region> regionList = regionService.getRegionByRegionName(field);
//        return new ApiResponse(regionList.size(), regionList);
//    }

    @GetMapping("/getAllRegionByFieldName")
    private ApiResponse<List<Region>> getAllRegionByRegionNameOrDivisionNameOrNationalName(@RequestParam("field") String field) {
        List<Region> regionList = regionService.getAllRegionByRegionNameOrDivisionNameOrNationalName(field);
        return new ApiResponse(regionList.size(), regionList);
    }


    @GetMapping("/excelDownload")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
        ByteArrayInputStream stream = regionService.generateToExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=regions.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheettml.sheet")
                )
                .body(new InputStreamResource(stream));
    }

    @GetMapping("/getPaginatedRegions")
    private ResponseEntity<Page<Region>> getPaginatedRegions(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Region> paginatedRegions = regionService.findRegionByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedRegions,HttpStatus.OK);
    }
}
