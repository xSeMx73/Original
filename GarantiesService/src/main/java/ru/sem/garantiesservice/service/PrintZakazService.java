package ru.sem.garantiesservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;
import ru.sem.garantiesservice.model.GarantRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Slf4j
@Service
public class PrintZakazService {


    private final NumberToWordsConverter numberToWordsConverter = new NumberToWordsConverter();

    public byte[] createZakaz(GarantRequest request, Long normHours, Long price, String job) throws IOException {

        ClassPathResource resource = new ClassPathResource("zakaz.xlsx");
        FileInputStream fis = new FileInputStream(resource.getFile());
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        job = UriUtils.decode(job, StandardCharsets.UTF_8);

        updateCell(sheet, 7, 5, String.valueOf(request.getId() + LocalDate.now().getDayOfYear()));
        updateCell(sheet, 11, 6, request.getTransportBrand());
        updateCell(sheet, 12, 6, request.getTransportModel());
        updateCell(sheet, 13, 6, request.getGosNumber());
        updateCell(sheet, 14, 6, String.valueOf(request.getTransportYear()));
        updateCell(sheet, 15, 6, request.getVin());
        updateCell(sheet, 18, 6, String.valueOf(request.getMileageStart()));
        updateCell(sheet, 24, 2, job);
        updateCell(sheet, 24, 3, String.valueOf(normHours));
        updateCell(sheet, 24, 4, String.valueOf(price));
        updateCell(sheet, 24, 5, String.valueOf(normHours * price));
        overrideCell(sheet, 16, 1, String.valueOf(request.getDateStartRepair()));
        overrideCell(sheet, 17, 1, String.valueOf(request.getDateStartRepair()));
        updateCell(sheet, 34, 2, request.getPartName() + " "
                + request.getPartBrand() + " " + request.getPartArticle());

        overrideCell(sheet, 36, 4, normHours * price + " руб.");
        overrideCell(sheet, 39, 1, numberToWordsConverter
                .convert((int) (normHours * price)) + " руб. 00 коп.");
        overrideCell(sheet,14,1, request.getClientName());

        fis.close();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        byte[] byteArray = out.toByteArray();
        workbook.close();
        return byteArray;
    }

    private void updateCell(Sheet sheet, int rowIndex, int cellIndex, String newValue) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);
        }

        cell.setCellValue(newValue);
    }

    private void overrideCell(Sheet sheet, int rowIndex, int cellIndex, String newValue) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(cellIndex);
        String existingText = cell.getStringCellValue();
        cell.setCellValue(existingText + " " + newValue);
    }
}
