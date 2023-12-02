package com.tkgroupbd.pusti.api.services.mastersettings.areas;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Division;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.areas.DivisionRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas.DivisionRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
   private DivisionRepository divisionRepository;

    @Override
    public MessageResponse saveDivision(DivisionRequest divisionRequest) {
        try {

            Division newDivision = new Division();
            newDivision.setDivisionName(divisionRequest.getDivisionName().trim().toUpperCase());
            newDivision.setNational(divisionRequest.getNational());
            divisionRepository.save(newDivision);
            return new MessageResponse(Message.SUCCESS_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage());
        }
    }

    @Override
    public void deleteDivisionById(int divisionId) {
        divisionRepository.deleteById(divisionId);
    }

    @Override
    public Optional<Division> updateDivisionStatus(int divisionId, DivisionRequest divisionRequest) {
        Optional<Division> result = divisionRepository.findById(divisionId);
        if (result.isPresent()) {
            Division division = result.get();
            division.setStatus(divisionRequest.isStatus());
            divisionRepository.save(division);
        } else {
            throw new ResourceNotFoundException("Division", "divisionId", divisionId);
        }

        return result;
    }

    @Override
    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    @Override
    public Optional<Division> updateDivision(int divisionId, DivisionRequest divisionRequest) {

        Optional<Division> result = divisionRepository.findById(divisionId);

        if (result.isPresent()) {
            Division division = result.get();
            division.setDivisionName(divisionRequest.getDivisionName().trim().toUpperCase());
            division.setNational(divisionRequest.getNational());

            divisionRepository.save(division);
        } else {
            throw new ResourceNotFoundException("Division", "divisionId", divisionId);
        }

        return result;
    }

    @Override
    public Division findDivisionById(int divisionId) {
        return divisionRepository.findById(divisionId).get();
    }

    @Override
    public List<Division> findSortedDivisionByKey(String field) {
        return divisionRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public List<Division> getDivisionByDivisionName(String divisionName) {
        List<Division> divisionList = divisionRepository
                .findDivisionByDivisionNameContainingIgnoreCase(divisionName.toUpperCase().trim());
        Collections.sort(divisionList, Comparator.comparing(Division::getDivisionName));

        return divisionList;
    }

    @Override
    public ByteArrayInputStream generateToExcel() throws IOException {
        String[] columns = {
                "Division Id", "Division Name", "National Id" , "National Name"
        };
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("divisions");

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
            List<Division> divisionList = getAllDivisions();

            int rowNumber = 1;
            for (Division division : divisionList) {
                Row row = sheet.createRow(rowNumber++);
                row.createCell(0).setCellValue(division.getDivisionId());
                row.createCell(1).setCellValue(division.getDivisionName());
                row.createCell(2).setCellValue(division.isStatus());
                if (division.getNational()!=null){
                    row.createCell(3).setCellValue(division.getNational().getNationalId());
                    row.createCell(4).setCellValue(division.getNational().getNationalName());
                }else {
                    row.createCell(3).setCellValue("");
                    row.createCell(4).setCellValue("");

                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
