package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Zone;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.ZoneRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.ZoneRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ZoneServiceImpl implements ZoneService {
    private static final Logger log = LoggerFactory.getLogger(ZoneServiceImpl.class);

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private ZoneRepository zoneRepository;

    @Override
    public MessageResponse createZone(ZoneRequest zoneRequest) {
        try {
            Zone newZone = new Zone();

            newZone.setZoneName(zoneRequest.getZoneName().trim().toUpperCase());
            newZone.setStatus(zoneRequest.isStatus());
            newZone.setCreatedAt(zoneRequest.getCreatedAt());
            newZone.setDeletedAt(zoneRequest.getDeletedAt());
            newZone.setUpdatedAt(zoneRequest.getUpdatedAt());
            newZone.setBrowser(zoneRequest.getBrowser());
            newZone.setIp(zoneRequest.getIp());
            newZone.setRegion(zoneRequest.getRegion());

            zoneRepository.save(newZone);

            return new MessageResponse(Message.SUCCESS_ZONE_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_ZONE_CREATION + e.getMessage());
        }

    }

    @Override
    public Optional<Zone> updateZone(Integer id, ZoneRequest zoneRequest) {
        Optional<Zone> result = zoneRepository.findById(id);

        if (result.isPresent()) {
            Zone zone = result.get();
            zone.setZoneName(zoneRequest.getZoneName().trim().toUpperCase());
            zone.setStatus(zoneRequest.isStatus());
            zone.setRegion(zoneRequest.getRegion());
            zoneRepository.save(zone);
        } else {
            throw new ResourceNotFoundException("Zone", "id", id);
        }

        return result;
    }

    @Override
    public void deleteZone(Integer id) {
        zoneRepository.deleteById(id);
    }

    @Override
    public Zone getZoneById(Integer id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id));
    }

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public List<Zone> findZoneByRegionId(Integer id) {
        return zoneRepository.findZoneByRegionId(id);
    }


    @Override
    public Optional<Zone> zoneStatusChange(Integer id, ZoneRequest zoneRequest) {
        try {
            Optional<Zone> result = zoneRepository.findById(id);
            if (result.isPresent()) {
                Zone zone = result.get();
                zone.setStatus(zoneRequest.isStatus());
                zoneRepository.save(zone);
                return Optional.of(zone);
            } else {
                throw new ResourceNotFoundException("Zone", "id", id);
            }
        } catch (Exception e) {
            // Handle the exception, log it, and return an empty Optional
            log.error("Error occurred while updating zone status", e.getMessage() + e.getCause());
            return Optional.empty();
        }

    }

    @Override
    public ByteArrayInputStream exportToExcel(List<Zone> zones) throws IOException {
        String[] columns = {"Zone ID", "Zone Name", "Region ID", "Region Name"};

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Zones");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
            }

            // Create data rows
            int rowIdx = 1;
            for (Zone zone : zones) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(zone.getZoneId());
                row.createCell(1).setCellValue(zone.getZoneName());

                if (zone.getRegion() != null) {
                    row.createCell(2).setCellValue(zone.getRegion().getRegionId());
                    row.createCell(3).setCellValue(zone.getRegion().getRegionName());
                }else {
                    row.createCell(2).setCellValue("");
                    row.createCell(3).setCellValue("");
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public List<Zone> getZoneByZoneName(String zoneName) {
        List<Zone> zoneList = zoneRepository.findZonesByZoneNameContainingIgnoreCase(zoneName);
        return zoneList;
    }

    public List<Zone> getAllZoneByZoneNameOrRegionName(String zoneName) {
        List<Zone> zoneList = zoneRepository.findAllZoneByZoneNameOrRegionName(zoneName.toUpperCase().trim());
        Collections.sort(zoneList, Comparator.comparing(Zone::getZoneName));
        return zoneList;
    }

    @Override
    public Page<Zone> findZonesByPagination(int offset, int pageSize) {
        Page<Zone> zones = zoneRepository.findAll(PageRequest.of(offset, pageSize));
        return zones;
    }

}
