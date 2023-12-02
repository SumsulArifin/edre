package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Outlet;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Route;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.OutletRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface OutletService {
    public MessageResponse createOutlet(OutletRequest request);

    public Optional<Outlet> updateOutlet(int id, OutletRequest request);

    public List<Outlet> outletList();

    public Optional<Outlet> outletById(int id);

    public List<Outlet> getByOutletName(String outletName);

    public Optional<Outlet> statusChangeOutlet(int id, OutletRequest request);

    public MessageResponse uploadCSV(MultipartFile file);

    public MessageResponse updateOutletLatLong(int routeId, OutletRequest outletRequest);

    public List<Outlet> getAllOutletByOutletNameOrRouteNameOrAddressOrContactPerson(String zoneName);

    public void exportDataToExcel(HttpServletResponse response) throws IOException;

    public Page<Outlet> findOutletsByPagination(int offset, int pageSize);
}