package com.example.coopangcrawling.crawling;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelWriter {

    private final HSSFWorkbook hWorkBook = new HSSFWorkbook();
    private HSSFSheet sheet = hWorkBook.createSheet();

    public void write(List<Review> reviewList) throws Exception {

        int total = 0;
        int contain = 0;
        int notContain = 0;
        int scoreContain = 0;
        int scoreNotContain = 0;


        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("total Num");
        firstRow.createCell(1).setCellValue("contain Num");
        firstRow.createCell(2).setCellValue("contain Avg");
        firstRow.createCell(3).setCellValue("notContain Num");
        firstRow.createCell(4).setCellValue("notContain Avg");



        Row titleRow = sheet.createRow(2);
        titleRow.createCell(0).setCellValue("score");
        titleRow.createCell(1).setCellValue("content");
        titleRow.createCell(2).setCellValue("isContain");


        for (int i = 0; i < reviewList.size(); i++) {
            Review review = reviewList.get(i);

            Row row = sheet.createRow(i+3);

            Cell score = row.createCell(0);
            score.setCellValue(review.getScore());

            Cell content = row.createCell(1);
            content.setCellValue(review.getContent());

            Cell isContain = row.createCell(2);
            isContain.setCellValue(String.valueOf(review.isContain()));

            total++;
            if (review.isContain()) {
                contain++;
                scoreContain += review.getScore();
            } else {
                notContain++;
                scoreNotContain += review.getScore();
            }
        }

        Row secondRow = sheet.createRow(1);
        secondRow.createCell(0).setCellValue(total);
        secondRow.createCell(1).setCellValue(contain);
        secondRow.createCell(2).setCellValue(String.format("%.3f", (double) scoreContain/contain));
        secondRow.createCell(3).setCellValue(notContain);
        secondRow.createCell(4).setCellValue(String.format("%.3f", (double) scoreNotContain/notContain));



        File file = new File("C:\\Users\\leo49\\Desktop\\cable.xls");
        FileOutputStream out = new FileOutputStream(file);
        hWorkBook.write(out);
        out.close();
    }

}
