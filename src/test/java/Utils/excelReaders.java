package Utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class excelReaders {
    String path_excel = "/src/main/resources/saucedemotestdata.xlsx";
    @DataProvider
    public Object[][] getLoginData() {

        FileInputStream fileInput;
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        XSSFRow row = null;
        XSSFCell cell = null;
        String path;
        path = System.getProperty("user.dir") + path_excel;

        try {//open file
            fileInput = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try { //get workbook from the file
            workbook = new XSSFWorkbook(fileInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sheet = workbook.getSheet("Logins"); //get Products worksheet

        System.out.println("\nLogins workSheet last row: " + sheet.getLastRowNum());
        System.out.println("\nLogins workSheet last cell number: " + sheet.getRow(0).getLastCellNum());

        //define size of object
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        //iterate and populate data
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {//row
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }

        try {
            fileInput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @DataProvider
    public Object[][] getProductData() {

        FileInputStream fileInput;
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        String path, text;
        path = System.getProperty("user.dir") + path_excel;
        DataFormatter formatter = new DataFormatter();

        try {//open file
            fileInput = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try { //get workbook
            workbook = new XSSFWorkbook(fileInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sheet = workbook.getSheet("Products"); //get Products worksheet

        System.out.println("\nProducts workSheet last row: " + sheet.getLastRowNum());
        System.out.println("\nProducts workSheet last cell number: " + sheet.getRow(0).getLastCellNum());

        //define size of object [lastrow][lastcolumn]
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        //iterate and populate data
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {//row
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                text = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                data[i - 1][j] = text;
                //data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }

        try {
            fileInput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }


}