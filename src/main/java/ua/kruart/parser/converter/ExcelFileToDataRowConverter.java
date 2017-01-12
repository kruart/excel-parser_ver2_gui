package ua.kruart.parser.converter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kruart.parser.model.DataRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * Converts rows data from excel file to DataRow objects
 *
 * @author kruart
 */
public class ExcelFileToDataRowConverter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelFileToDataRowConverter.class);

    public static List<DataRow> extractDataFromFile(File src, int attributeColumn, int imageColumn) {
        Workbook wb = null;
        try {
            //Создаем workbook
            if (src.getAbsolutePath().endsWith(".xls")) {
                wb = new HSSFWorkbook(new FileInputStream(src));    //if file has .xls extension
            } else {
                wb = new XSSFWorkbook(new FileInputStream(src));    //if file has .xlsx extension
            }

            Sheet sheet = wb.getSheetAt(0);// Проверяем только первую страницу

            Iterator<Row> rows = sheet.rowIterator(); // gets iterator of the rows
            List<DataRow> dataRowList = new ArrayList<>();

            if (rows.hasNext()) // miss the first row of the sheet
                rows.next();

            while (rows.hasNext()) { // Перебираем все строки начиная со второй до тех пор, пока документ не закончится
                Row row = rows.next();
                DataRow dataRow = extractCellsFromRow(attributeColumn, imageColumn, row);
                if (dataRow != null) {
                    dataRowList.add(dataRow);
                }
            }
            return dataRowList;

        } catch (IOException e) {
            LOGGER.error("Something has gone wrong. Probably the file cannot be found. Absolute path to file: {}. Please select the file again.", src.getAbsoluteFile());
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<>();
    }

    private static DataRow extractCellsFromRow(int attributeColumn, int imageColumn, Row row) {
        //Получаем ячейки из строки по номерам столбцов
        Cell attributeCell = row.getCell(attributeColumn);
        Cell imageLinkCell = row.getCell(imageColumn);
        DataRow dataRow = null;

        if (isCellValid(attributeCell) && isCellValid(imageLinkCell)) {
            dataRow = convertStringsToDataRowObject(attributeCell.getStringCellValue(), imageLinkCell.getStringCellValue());
        }

        return dataRow;
    }

    /**
     * Checks that HSSFCell object isn't null and not empty
     */
    private static boolean isCellValid(Cell cell) {
        return cell != null && !cell.getStringCellValue().isEmpty();
    }

    private static DataRow convertStringsToDataRowObject(String attributeCell, String imageLinkCell) {
        String[] links = breakStringOnTheLinks(imageLinkCell);
        return new DataRow(attributeCell, Arrays.asList(links));
    }

    public static String[] breakStringOnTheLinks(String line) {
        String newLine = line.replaceAll("%20", " ");
//      String[] splitStrs = newLine.split(("("(?<=.jpg|.png)")"));
        return newLine.split("[,; ]+");

    }
}
