package com.fpt.onlineTest.helper;

import com.fpt.onlineTest.model.Answer;
import com.fpt.onlineTest.model.Questions;
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

public class QuestionExcelHelper {
    static String[] HEADERs = { "Id", "ContentQuestion", "Answer1", "Answer2", "Answer3", "Answer4"};
    static String SHEET = "Question";

    public static ByteArrayInputStream QuestionToExcel(List<Questions> questions) {
        try(Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);
            for(int col = 0; col < HEADERs.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }
            int rowIdx = 1;
            for(Questions question : questions) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(question.getQuestionId());
                row.createCell(1).setCellValue(question.getContentQuestion());
                for(Answer answer : question.getAnswer()) {
                    row.createCell(2).setCellValue(answer.getContentAnswer());
                    row.createCell(3).setCellValue(answer.getContentAnswer());
                    row.createCell(4).setCellValue(answer.getContentAnswer());
                    row.createCell(5).setCellValue(answer.getContentAnswer());
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}