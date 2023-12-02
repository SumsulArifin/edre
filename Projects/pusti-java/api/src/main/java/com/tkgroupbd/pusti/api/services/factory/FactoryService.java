package com.tkgroupbd.pusti.api.services.factory;

import com.tkgroupbd.pusti.api.data.models.entity.factory.Factory;
import com.tkgroupbd.pusti.api.data.payloads.dto.factory.FactoryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface FactoryService {
    public MessageResponse createNewFactory(FactoryRequest factoryRequest);

    public Optional<Factory> updateFactory(int factoryId, FactoryRequest factoryRequest);

    public void deleteFactory(int factoryId);

    public Factory factoryById(int factoryId);

    public List<Factory> getAllFactory();

    public List<Factory> searchFactoriesAndDepots(String fieldName);

    public Optional<Factory> changeFactoryStatus(int factoryId, FactoryRequest factoryRequest);

    public List<Factory> getByFactoryName(String factoryName);

    public MessageResponse uploadCSVWithFactoryAndDepot(MultipartFile file);

    public List<Factory> getFactoriesByDepotId(int depotId);

    public void downloadExcel(HttpServletResponse response) throws IOException;

    Page<Factory> getFactoryByPagination(int offset, int pageSize);
}
