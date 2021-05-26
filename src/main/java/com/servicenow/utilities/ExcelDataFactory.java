package com.servicenow.utilities;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataFactory {
  
  public String[][] readExcelData(String fileName) throws IOException{
    
    XSSFWorkbook workbook = new XSSFWorkbook("./testdata/"+fileName+".xlsx");
    
    XSSFSheet sheet = workbook.getSheet("Sheet1");
    
    int rowCount = sheet.getLastRowNum();
    
    short colCount = sheet.getRow(0).getLastCellNum();
    
    String [][] data = new String[rowCount][colCount];
    
    for(int i = 1; i<= rowCount; i++) {
      for(int j = 0; j < colCount; j++) {
        data[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
      }
    }
    workbook.close();
    return data;
  }
}
