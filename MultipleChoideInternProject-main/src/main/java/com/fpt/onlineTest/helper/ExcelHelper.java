package com.fpt.onlineTest.helper;

import com.fpt.onlineTest.model.ResultExam;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
    static String[] HEADERs = { "Id", "FullName", "CourseName", "Point"};
    static String SHEET = "ResultExam";

    public static ByteArrayInputStream resultExamToExcel(List<ResultExam> resultExams) {
        try(Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);
            for(int col = 0; col < HEADERs.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }
            int rowIdx = 1;
            for(ResultExam resultExam : resultExams) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(resultExam.getResultExamId());
                row.createCell(1).setCellValue(resultExam.getUser().getFullName());
                row.createCell(2).setCellValue(resultExam.getExam().getCourses().getCourseName());
                row.createCell(3).setCellValue(resultExam.getPoint());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
