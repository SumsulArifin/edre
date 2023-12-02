package com.tkgroupbd.pusti.api.services.mastersettings.areas;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Division;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Region;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.RegionRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.RegionRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private RegionRepository regionRepository;

    @Override
    public MessageResponse saveRegion(RegionRequest regionRequest) {
        try {
            Region newRegion = new Region();
            newRegion.setRegionName(regionRequest.getRegionName().trim().toUpperCase());
            newRegion.setTotalDistributor(regionRequest.getTotalDistributor());
            newRegion.setStatus(regionRequest.isStatus());
            newRegion.setDivision(regionRequest.getDivision());

            regionRepository.save(newRegion);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }

    }

    @Override
    public Optional<Region> updateRegion(int regionId, RegionRequest regionRequest) {
        Optional<Region> result = regionRepository.findById(regionId);

        if (result.isPresent()) {
            Region region = result.get();

            region.setRegionName(regionRequest.getRegionName().trim().toUpperCase());
            region.setTotalDistributor(regionRequest.getTotalDistributor());
            region.setStatus(regionRequest.isStatus());
            region.setDivision(regionRequest.getDivision());

            regionRepository.save(region);
        } else {
            throw new ResourceNotFoundException("Region", "regionId", regionId);
        }

        return result;
    }

    @Override
    public void deleteRegionById(int regionId) {
        regionRepository.deleteById(regionId);
    }

    @Override
    public Optional<Region> updateRegionStatus(int regionId, RegionRequest regionRequest) {
        Optional<Region> result = regionRepository.findById(regionId);

        if (result.isPresent()) {
            Region region = result.get();
            region.setStatus(regionRequest.isStatus());
            regionRepository.save(region);
            System.out.println(region);
        } else {
            throw new ResourceNotFoundException("Region", "regionId", regionId);
        }
        return result;
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region findRegionById(int regionId) {
        return regionRepository.findById(regionId).get();
    }

    public List<Region> getRegionByRegionName(String regionName) {
        List<Region> regionList = regionRepository
                .findRegionByRegionNameContainingIgnoreCase(regionName.toUpperCase().trim());
        return regionList;
    }

    public List<Region> getAllRegionByRegionNameOrDivisionNameOrNationalName(String regionName) {
        List<Region> regionList = regionRepository.findAllRegionByRegionNameOrDivisionNameOrNationalName(regionName.toUpperCase().trim());
        Collections.sort(regionList, Comparator.comparing(Region::getRegionName));
        return regionList;
    }

    @Override
    public Page<Region> findRegionByPagination(int offset, int pageSize) {
        Page<Region> regions = regionRepository.findAll(PageRequest.of(offset, pageSize));
        return regions;
    }

    public ByteArrayInputStream generateToExcel() throws IOException {
        String[] columns = {
                "Region Id", "Region Name", "Total Distributor", "Division Id", "Division Name"
        };
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Regions");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
            List<Region> regionList = getAllRegions();

            int rowNumber = 1;
            for (Region region : regionList) {
                Row row = sheet.createRow(rowNumber++);
                row.createCell(0).setCellValue(region.getRegionId());
                row.createCell(1).setCellValue(region.getRegionName());
                row.createCell(2).setCellValue(region.getTotalDistributor());
                if (region.getDivision() != null) {
                    row.createCell(3).setCellValue(region.getDivision().getDivisionId());
                    row.createCell(4).setCellValue(region.getDivision().getDivisionName());
                } else {
                    row.createCell(3).setCellValue("");
                    row.createCell(4).setCellValue("");

                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }


}
