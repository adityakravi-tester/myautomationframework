package com.servicenow.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

  public static void main(String[] args) throws FileNotFoundException, IOException {

    XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("./testdat/Login.xlsx"));
    
    
    



    
    
  }
}
