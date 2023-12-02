package com.tkgroupbd.pusti.api.controllers.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.common.ApiAndMessageResponse;
import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.RouteRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.areas.RouteService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Route")
@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private RouteService routeService;

    @PostMapping("/addNewRoute")
    public ResponseEntity<MessageResponse> routeSave(@RequestBody @Valid RouteRequest routeRequest) {
        MessageResponse newRoute = routeService.saveRoute(routeRequest);
        return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
    }

    @GetMapping("/getAllRoutes")
    public ResponseEntity<List<Route>> getRoute() {
        List<Route> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/getRouteById/{routeId}")
    public ResponseEntity<Route> getRegionById(@PathVariable("routeId") Integer routeId) {
        Route route = routeService.findRouteById(routeId);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PutMapping("/updateRoute/{routeId}")
    public ResponseEntity<MessageResponse> updateRoute(
            @PathVariable int routeId,
            @RequestBody RouteRequest routeRequest) {
        MessageResponse response = routeService.updateRoute(routeId, routeRequest);

        if (response.getMessage().equals(Message.SUCCESS_UPDATE)) {
            return ResponseEntity.ok(response);
        } else if (response.getMessage().equals(Message.FAILED_UPDATE)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/updateRouteLatitudeLongitude/{routeId}")
    public ResponseEntity<MessageResponse> updateRouteLatitudeLongitude(
            @PathVariable int routeId,
            @RequestBody RouteRequest routeRequest) {
        MessageResponse response = routeService.updateRouteLatLong(routeId, routeRequest);
        if (response.getMessage().equals(Message.SUCCESS_UPDATE)) {
            return ResponseEntity.ok(response);
        } else if (response.getMessage().equals(Message.FAILED_UPDATE)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/statusChange/{routeId}")
    public ResponseEntity<Optional<Route>> updateRouteStatus(@PathVariable("routeId") Integer routeId,
            @RequestBody RouteRequest routeRequest) {
        Optional<Route> route = routeService.updateRouteStatus(routeId, routeRequest);
        return new ResponseEntity<Optional<Route>>(route, HttpStatus.OK);
    }

    @GetMapping("/getAllRoutesByFieldName")
    private ApiResponse<List<Route>> getByRoutesName(@RequestParam("field") String field) {
        List<Route> routes = routeService.getAllRoutesByDbNameOrZoneNameOrRegionNameOrDivisionName(field);
        return new ApiResponse(routes.size(), routes);
    }


//    @GetMapping("/getAllRouteByFieldName")
//    private ResponseEntity<ApiAndMessageResponse<List<Route>>> getAllRouteByDbNameOrZoneNameOrRegionNameOrDivisionName(@RequestParam("field") String field) {
//        List<Route> routeList = routeService.getAllRouteByDbNameOrZoneNameOrRegionNameOrDivisionName(field);
//        if (routeList.isEmpty()) {
//            ApiAndMessageResponse<List<Route>> response = new ApiAndMessageResponse<>(0, null);
//            String errorMessage =  Message.FIELD_NAME+field +Message.NO_DATA_FOUND ;
//            response.setMessage(errorMessage);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        } else {
//            ApiAndMessageResponse<List<Route>> response = new ApiAndMessageResponse<>(routeList.size(), routeList, Message.RETRIEVAL_SUCCESS);
//            return ResponseEntity.ok(response);
//        }
//    }

    @PostMapping("/csvUpload")
    public ResponseEntity<MessageResponse> uploadCSVFile(@RequestParam("file") MultipartFile file) {

        try {
            MessageResponse newRoute = routeService.uploadCSV(file);
            return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error while processing CSV file: " + e.getMessage()));
        }

    }

    @GetMapping("/excelDownload")
    public void exportRoutesToExcel(HttpServletResponse response) throws IOException {
        routeService.exportDataToExcel(response);
    }

    @GetMapping("/getPaginatedRoutes")
    private ResponseEntity<Page<Route>> getPaginatedRoutes(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Route> paginatedRoutes = routeService.findRoutesByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedRoutes,HttpStatus.OK);
    }
}
