package com.tkgroupbd.pusti.api.services.factory;

import com.tkgroupbd.pusti.api.data.models.entity.depot.AssignDepot;
import com.tkgroupbd.pusti.api.data.models.entity.depot.Depot;
import com.tkgroupbd.pusti.api.data.models.entity.factory.Factory;
import com.tkgroupbd.pusti.api.data.payloads.dto.depot.AssignDepotRequest;
import com.tkgroupbd.pusti.api.data.payloads.dto.factory.FactoryRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.depot.DepotRepository;
import com.tkgroupbd.pusti.api.data.repositories.factory.FactoryRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private FactoryRepository factoryRepository;
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private DepotRepository depotRepository;


    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public MessageResponse createNewFactory(FactoryRequest factoryRequest) {

        try {
            if (factoryRequest == null) {
                throw new RuntimeException("Data not found.");
            }
            Factory factory = new Factory();

            factory.setFactoryName(factoryRequest.getFactoryName());
            factory.setAddress(factoryRequest.getAddress());
            factory.setStatus(factoryRequest.isStatus());
            Set<AssignDepot> assignDepots = new HashSet<>();
            for (AssignDepotRequest assignDepotRequest : factoryRequest.getAssignDepots()) {
                AssignDepot assignDepot = new AssignDepot();
                assignDepot.setDepot(assignDepotRequest.getDepot());
                assignDepots.add(assignDepot);
            }
            factory.setAssignDepots(assignDepots);
            factoryRepository.save(factory);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }
    }

    @Override
    public Optional<Factory> updateFactory(int factoryId, FactoryRequest factoryRequest) {

        Optional<Factory> result = Optional.ofNullable(factoryRepository.findById(factoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Factory", "factoryId", factoryId)));

        if (result.isPresent()) {
            Factory factory = result.get();

            factory.setFactoryName(factoryRequest.getFactoryName());
            factory.setAddress(factoryRequest.getAddress());
            factory.setStatus(factoryRequest.isStatus());
            Set<AssignDepot> assignDepots = new HashSet<>();
            for (AssignDepotRequest assignDepotRequest : factoryRequest.getAssignDepots()) {
                AssignDepot assignDepot = new AssignDepot();
                assignDepot.setDepot(assignDepotRequest.getDepot());
                assignDepots.add(assignDepot);
            }
            factory.setAssignDepots(assignDepots);
            factoryRepository.save(factory);

            return result;
        } else {
            throw new ResourceNotFoundException("Factory", "Factory", factoryId);
        }
    }

    @Override
    public Optional<Factory> changeFactoryStatus(int factoryId, FactoryRequest factoryRequest) {

        Optional<Factory> result = Optional.ofNullable(factoryRepository.findById(factoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Factory", "factoryId", factoryId)));

        if (result.isPresent()) {
            Factory factory = result.get();
            factory.setStatus(factoryRequest.isStatus());
            factoryRepository.save(factory);
            return result;
        } else {
            throw new ResourceNotFoundException("Factory", "Factory", factoryId);
        }
    }

    public List<Factory> getFactoriesByDepotId(int depotId) {
        return factoryRepository.findFactoriesByDepotId(depotId);
    }

    @Override
    public List<Factory> getByFactoryName(String factoryName) {
        return factoryRepository.findAllByFactoryNameContainingIgnoreCase(factoryName);

    }

    @Override
    public void deleteFactory(int factoryId) {
        factoryRepository.deleteById(factoryId);
    }

    @Override
    public Factory factoryById(int factoryId) {
        return factoryRepository.findById(factoryId).get();
    }

    @Override
    public List<Factory> getAllFactory() {
        return factoryRepository.findAll();
    }


    public List<Factory> searchFactoriesAndDepots(String fieldName) {
        return factoryRepository.searchFactoriesAndDepots(fieldName);
    }

    @Override
    public MessageResponse uploadCSVWithFactoryAndDepot(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            reader.lines().skip(1).forEach(line -> {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String factoryName = data[0].toLowerCase().trim();
                    Factory factory = factoryRepository.findByFactoryName(factoryName)
                            .orElseGet(() -> createNewFactory(data));
                    AssignDepot assignDepot = new AssignDepot();
                    int depotId = Integer.parseInt(data[3]);
                    Optional<Depot> depot = depotRepository.findById(depotId);
                    depot.ifPresent(assignDepot::setDepot);
                    factory.getAssignDepots().add(assignDepot);
                    factoryRepository.save(factory);
                }
            });

            return new MessageResponse(Message.SUCCESS_CSV_UPLOAD);
        } catch (Exception e) {
            return new MessageResponse("Error while processing CSV file: " + e.getMessage());
        }
    }
    private Factory createNewFactory(String[] data) {
        Factory factory = new Factory();
        factory.setFactoryName(data[0].toLowerCase().trim());
        factory.setAddress(data[1]);
        factory.setStatus(Boolean.parseBoolean(data[2]));
        return factory;
    }
    @Override
    public void downloadExcel(HttpServletResponse response) throws IOException {
        List<Factory> factories = factoryRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Factories");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Factory ID");
            headerRow.createCell(1).setCellValue("Factory Name");
            headerRow.createCell(2).setCellValue("Address");
            headerRow.createCell(3).setCellValue("Status");
            headerRow.createCell(4).setCellValue("Depot Id");
            headerRow.createCell(5).setCellValue("Depot Name");
            headerRow.createCell(6).setCellValue("Email");
            headerRow.createCell(7).setCellValue("Phone");
            headerRow.createCell(8).setCellValue("Department");

            int rowNum = 1;
            for (Factory factory : factories) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(factory.getFactoryId());
                row.createCell(1).setCellValue(factory.getFactoryName());
                row.createCell(2).setCellValue(factory.getAddress());
                row.createCell(3).setCellValue(factory.isStatus());

                if (!factory.getAssignDepots().isEmpty()) {
                    row.createCell(4).setCellValue(factory.getAssignDepots().iterator().next().getDepot().getDepotId());
                    row.createCell(5).setCellValue(factory.getAssignDepots().iterator().next().getDepot().getDepotName());
                    row.createCell(6).setCellValue(factory.getAssignDepots().iterator().next().getDepot().getEmail());
                    row.createCell(7).setCellValue(factory.getAssignDepots().iterator().next().getDepot().getPhone());
                    row.createCell(8).setCellValue(factory.getAssignDepots().iterator().next().getDepot().getDepartment().name());
                }
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=factories.xlsx");
            workbook.write(response.getOutputStream());
        }
    }

    public Page<Factory> getFactoryByPagination(int offset, int pageSize) {
        Page<Factory> factories = factoryRepository.findAll(PageRequest.of(offset, pageSize));
        return factories;
    }


}
