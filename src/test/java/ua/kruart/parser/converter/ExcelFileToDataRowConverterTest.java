package ua.kruart.parser.converter;

import org.junit.Test;
import ua.kruart.parser.model.DataRow;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Contain unit-tests for {@link ExcelFileToDataRowConverter}
 *
 * @author kruart
 */
public class ExcelFileToDataRowConverterTest {
    public static final File TEST_FILE = new File("src/test/resources/file/111.xls");

    @Test
    public void testExtractDataFromFile() {
        List<DataRow> dataRowList = ExcelFileToDataRowConverter.extractDataFromFile(TEST_FILE, 1 , 18);

        assertEquals("number of rows", 3, dataRowList.size());
        assertEquals("name attribute should be equals", "FUJI2NOOR", dataRowList.get(0).getName());
        assertEquals("name attribute should be equals", "FUJI2NOIN", dataRowList.get(2).getName());
        assertNotEquals("name attribute should not be equals", "FUJI2NOOR", dataRowList.get(1).getName());
        assertEquals("number of links in image attribute in third row", 3, dataRowList.get(2).getLinks().size());
    }

    @Test
    public void testIfFilePathIsEmptyThenReturnEmptyList() {
        List<DataRow> dataRowList = ExcelFileToDataRowConverter.extractDataFromFile(new File(""), 1 , 18);
        assertTrue("if path is empty then should be return empty list", dataRowList.isEmpty());
    }

    @Test
    public void testBreakStringOnTheLinks() {
        String line = "http://deltaplus-cei.com/img/b/10/00/100001.jpg,   http://deltaplus-cei.com/img/b/10/00/100001-1.png http://deltaplus-cei.com/img/b/10/00/100001-2.jpg;%20http://deltaplus-cei.com/img/b/10/00/100001-2.jpg";
        String[] links = ExcelFileToDataRowConverter.breakStringOnTheLinks(line);

        assertEquals("number of links in line after \"regex\" split", 4, links.length);
    }
}
