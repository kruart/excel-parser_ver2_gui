package ua.kruart.parser.converter;

import org.junit.Test;
import ua.kruart.parser.model.DataRow;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Arthur on 05.01.2017.
 */
public class ExcelFileToDataRowConverterTest {
    public static final File TEST_FILE = new File("src/test/resources/file/111.xls");

    @Test
    public void testExtractDataFromFile() {
        List<DataRow> dataRowList = ExcelFileToDataRowConverter.extractDataFromFile(TEST_FILE, 1 , 18);

        assertEquals(3, dataRowList.size());
        assertEquals("FUJI2NOOR", dataRowList.get(0).getName());
        assertEquals("FUJI2NOIN", dataRowList.get(2).getName());
    }

    @Test
    public void testIfFilePathIsEmptyThenReturnEmptyList() {
        List<DataRow> dataRowList = ExcelFileToDataRowConverter.extractDataFromFile(new File(""), 1 , 18);
        assertTrue(dataRowList.isEmpty());
    }
}
