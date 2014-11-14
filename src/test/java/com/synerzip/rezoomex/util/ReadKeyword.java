package com.synerzip.rezoomex.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ReadKeyword {
       public static List<String> cellvalue = new ArrayList<String>();
       public static int rowNum;
 
       public static String[][] readExcel(String absolutePathToFile) {
 
              // A Two dimensional array of Strings which represents the data in the
              // sheet
              String[][] data = null;
              try {
                     // A Buffered File Input Stream to read the data
                     InputStream is = new BufferedInputStream(new FileInputStream(
                                  absolutePathToFile));
                     // Workbook representing the excel file
                     XSSFWorkbook wb = new XSSFWorkbook(is);
                     // Next a sheet which represents the sheet within that excel file
                     XSSFSheet sheet = wb.getSheet("Sheet1");
                     // No of rows in the sheet
                     rowNum = sheet.getLastRowNum() + 1;
                     // No of columns in the sheet
                   //  int colNum = sheet.getRow(1).getLastCellNum();
                     int colNum=2;
                     data = new String[rowNum][colNum];
                     for (int i = 0; i < rowNum; i++) {
                           // Get the row
                           XSSFRow row = sheet.getRow(i);
                           for (int j =0; j < colNum; j++) {
                                  // Get the columns or cells for the first row and keep
                                  // looping
                                  // for the other rows
                                  XSSFCell cell = row.getCell(j);
                                  // Make a call to the method cellToString which actually
                                  // converts the cell contents to String
                                  String value = cellToString(cell);
                                  data[i][j] = value;
 
                                  cellvalue.add(value);
                                  // String[] cellvalue = {} ;
                                  // cellvalue[i] = value;
 
                                  // Logic for handling the data
                                  // You can write the logic here, or leave the method as it
                                  // is to return a two dimensional array
                                  // representing the excel data
                                  // System.out.println("Value:" + value);
                                 // System.out.println(cellvalue);
                           }
 
                     }
              } catch (Exception e) {
                     e.printStackTrace();
              }
              return data;
 
       }
 
       public static String cellToString(XSSFCell cell) {
 
              Object result;
 
              switch (cell.getCellType()) {
              case Cell.CELL_TYPE_NUMERIC:
                     result = cell.getNumericCellValue();
                     break;
 
              case Cell.CELL_TYPE_STRING:
                     result = cell.getStringCellValue();
                     break;
 
              case Cell.CELL_TYPE_BOOLEAN:
                     result = cell.getBooleanCellValue();
                     break;
 
              case Cell.CELL_TYPE_FORMULA:
                     result = cell.getCellFormula();
                     break;
 
              default:
                     throw new RuntimeException("Unknown Cell Type");
              }
 
              return result.toString();
       }
 
}
 