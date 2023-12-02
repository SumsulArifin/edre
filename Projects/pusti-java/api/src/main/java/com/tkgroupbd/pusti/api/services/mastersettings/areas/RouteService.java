package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.RouteRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface RouteService {

    public MessageResponse saveRoute(RouteRequest routeRequest);

    public MessageResponse updateRoute(int routeId, RouteRequest routeRequest);

    public MessageResponse updateRouteLatLong(int routeId, RouteRequest routeRequest);

    public Optional<Route> updateRouteStatus(int routeId, RouteRequest routeRequest);

    List<Route> getByRouteName(String keyName);

    public List<Route> getAllRoutes();

    public Route findRouteById(int routeId);

    public MessageResponse uploadCSV(MultipartFile file);
    public void exportDataToExcel(HttpServletResponse response) throws IOException;

    public List<Route> getAllRoutesByDbNameOrZoneNameOrRegionNameOrDivisionName(String zoneName);

    public Page<Route> findRoutesByPagination(int offset, int pageSize);
}
