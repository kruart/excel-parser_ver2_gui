package ua.kruart.parser.repository;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import ua.kruart.parser.converter.ExcelFileToDataRowConverter;
import ua.kruart.parser.model.DataRow;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Contain unit-tests for {@link ExcelDataRowRepositoryImplTest}
 *
 * @author kruart
 */
public class ExcelDataRowRepositoryImplTest {
    private DataRowRepository repository;
    private String DESTINATION = "src/test/resources/excel";

    @Before
    public void setUp() throws Exception {
        repository = new ExcelDataRowRepositoryImpl();
    }

    @Test
    public void testSave() throws IOException, InterruptedException {
        List<DataRow> rows = getTestData();

        repository.save(DESTINATION, rows);
        String expectedPathToFile = DESTINATION + File.separator + "FUJI2NOOR" + File.separator + "zazaa.jpg";
        assertTrue(new File(expectedPathToFile).exists());

        FileUtils.deleteDirectory(new File(DESTINATION));
    }

    public List<DataRow> getTestData() {
        File test_file = new File("src/test/resources/file/111.xls");

        return ExcelFileToDataRowConverter.extractDataFromFile(test_file, 1 , 18);
    }
}
