package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.ApiAndMessageResponse;
import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.OutletRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.OutletService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Outlet")
@RestController
@RequestMapping("/outlet")
public class OutletController {

    @Autowired
    @Qualifier("outletServiceImpl")
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private OutletService outletService;

    @PostMapping("/addNewOutlet")
    public ResponseEntity<MessageResponse> outletSave(
            @RequestBody @Valid OutletRequest request) {
        MessageResponse newOutlet = outletService.createOutlet(request);
        return new ResponseEntity<>(newOutlet, HttpStatus.CREATED);
    }

    @PutMapping("/updateOutlet/{id}")
    public ResponseEntity<Optional<Outlet>> updateBulkOutlet(@PathVariable("id") Integer id,
            @RequestBody @Valid OutletRequest outletRequest) {
        Optional<Outlet> updatedOutlet = outletService.updateOutlet(id, outletRequest);
        return new ResponseEntity<Optional<Outlet>>(updatedOutlet, HttpStatus.OK);
    }

    @PutMapping("/statusChangeOutlet/{id}")
    public ResponseEntity<Optional<Outlet>> statusChangeOutlet(@PathVariable("id") Integer id,
            @RequestBody OutletRequest outletRequest) {
        Optional<Outlet> updatedOutlet = outletService.statusChangeOutlet(id, outletRequest);
        return new ResponseEntity<Optional<Outlet>>(updatedOutlet, HttpStatus.OK);
    }
    @PutMapping("/updateOutletLatLong/{outletId}")
    public ResponseEntity<MessageResponse> updateOutletLatLong(
            @PathVariable int outletId,
            @RequestBody OutletRequest routeRequest) {
        MessageResponse response = outletService.updateOutletLatLong(outletId, routeRequest);
        if (response.getMessage().equals(Message.SUCCESS_UPDATE)) {
            return ResponseEntity.ok(response);
        } else if (response.getMessage().equals(Message.FAILED_UPDATE)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("/getAllOutlets")
    public ResponseEntity<List<Outlet>> getOutlets() {
        List<Outlet> outlets = outletService.outletList();
        return new ResponseEntity<>(outlets, HttpStatus.OK);
    }
//    @GetMapping("/getByOutletName")
//    private ApiResponse<List<Outlet>> getByRouteName(@RequestParam("outletName") String outletName) {
//        List<Outlet> outletList = service.getByOutletName(outletName);
//        return new ApiResponse(outletList.size(), outletList);
//    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Outlet>> getOutlets(@PathVariable("id") Integer id) {
        Optional<Outlet> outlet = outletService.outletById(id);
        return new ResponseEntity<>(outlet, HttpStatus.OK);
    }
    @PostMapping("/csvUpload")
    public ResponseEntity<MessageResponse> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        MessageResponse newRoute = outletService.uploadCSV(file);
        return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
    }


    @GetMapping("/getAllOutletByFieldName")
    private ApiResponse<List<Outlet>> getByRouteName(@RequestParam("field") String field) {
        List<Outlet> outletList = outletService.getAllOutletByOutletNameOrRouteNameOrAddressOrContactPerson(field);
        return new ApiResponse(outletList.size(), outletList);
    }


    @GetMapping("/excelDownload")
    public void exportRoutesToExcel(HttpServletResponse response) throws IOException {
        outletService.exportDataToExcel(response);
    }

    @GetMapping("/getPaginatedOutlets")
    private ResponseEntity<Page<Outlet>> getPaginatedOutlets(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Outlet> paginatedOutlets = outletService.findOutletsByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedOutlets,HttpStatus.OK);
    }

//    @GetMapping("/getAllOutletByFieldName")
//    private ResponseEntity<ApiAndMessageResponse<List<Outlet>>> getAllRouteByDbNameOrZoneNameOrRegionNameOrDivisionName(@RequestParam("field") String field) {
//        List<Outlet> routeList = service.getAllOutletByOutletNameOrRouteNameOrAddressOrContactPerson(field);
//        if (routeList.isEmpty()) {
//            ApiAndMessageResponse<List<Outlet>> response = new ApiAndMessageResponse<>(0, null);
//            String errorMessage =  Message.FIELD_NAME+field +Message.NO_DATA_FOUND ;
//            response.setMessage(errorMessage);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        } else {
//            ApiAndMessageResponse<List<Outlet>> response = new ApiAndMessageResponse<>(routeList.size(), routeList, Message.RETRIEVAL_SUCCESS);
//            return ResponseEntity.ok(response);
//        }
//    }

}
