package com.example.SpringBootDemoProject.entity;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper
{
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "firstName", "lastName", "email"};
    static String SHEET = "Tutorials";

    public static boolean hasExcelFormat(@NotNull MultipartFile file)
    {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Employee> excelToEmployee(InputStream is)
    {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Employee> empList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext())
            {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0)
                {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                 Employee emp = new Employee();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            emp.setFirstName(currentCell.getStringCellValue());
                            break;

                        case 1:
                            emp.setLastName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            emp.setEmail(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }
                    cellIdx++;
                }
                empList.add(emp);
            }
            workbook.close();

            return empList;
        }
        catch (IOException e)
        {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
