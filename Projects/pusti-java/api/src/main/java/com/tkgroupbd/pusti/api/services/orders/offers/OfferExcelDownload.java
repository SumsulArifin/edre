package com.tkgroupbd.pusti.api.services.orders.offers;

import com.tkgroupbd.pusti.api.data.models.entity.orders.offers.Offer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OfferExcelDownload {
    private List<Offer> offers;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public OfferExcelDownload(List<Offer> offers) {
        this.offers = offers;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Offer");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        createCells(row, 0, "Offer Id", style);
        createCells(row, 1, "Browser", style);
        createCells(row, 2, "Created At", style);
        createCells(row, 3, "Created By", style);
        createCells(row, 4, "Deleted At", style);
        createCells(row, 5, "Deleted By", style);
        createCells(row, 6, "Ip", style);
        createCells(row, 7, "Status", style);
        createCells(row, 8, "Updated At", style);
        createCells(row, 9, "Updated By", style);
        createCells(row, 10, "End Date", style);
        createCells(row, 11, "Is Single Product Based Offer", style);
        createCells(row, 12, "Offer Category", style);
        createCells(row, 13, "Offer Level", style);
        createCells(row, 14, "Offer Name", style);
        createCells(row, 15, "Offer Target", style);
        createCells(row, 16, "Start Date", style);
    }

    private void createCells(Row row, int column, Object value, CellStyle style) {
        sheet.autoSizeColumn(column);
        Cell cell = row.createCell(column);

        if (value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(value.toString());  //toString() method for unknown types
        }

        cell.setCellStyle(style);
    }

    public void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        for (Offer record: offers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCells(row, columnCount++, record.getOfferId(), style);
            createCells(row, columnCount++, record.getBrowser(), style);
            createCells(row, columnCount++, record.getCreatedAt(), style);
            createCells(row, columnCount++, record.getCreatedBy(), style);
            createCells(row, columnCount++, record.getDeletedAt(), style);
            createCells(row, columnCount++, record.getDeletedBy(), style);
            createCells(row, columnCount++, record.getIp(), style);
            createCells(row, columnCount++, record.isStatus(), style);
            createCells(row, columnCount++, record.getUpdatedAt(), style);
            createCells(row, columnCount++, record.getUpdatedBy(), style);
            createCells(row, columnCount++, record.getEndDate(), style);
            createCells(row, columnCount++, record.isSingleProductBasedOffer(), style);
            createCells(row, columnCount++, record.getOfferCategory(), style);
            createCells(row, columnCount++, record.getOfferLevel(), style);
            createCells(row, columnCount++, record.getOfferName(), style);
            createCells(row, columnCount++, record.getOfferTarget(), style);
            createCells(row, columnCount++, record.getStartDate(), style);
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
