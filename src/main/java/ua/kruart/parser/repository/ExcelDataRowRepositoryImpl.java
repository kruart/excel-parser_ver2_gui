package ua.kruart.parser.repository;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kruart.parser.model.DataRow;
import ua.kruart.parser.util.PathConstructorHelper;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * Implementation of the {@link DataRowRepository} which works with excel files
 *
 * @author kruart
 */
public class ExcelDataRowRepositoryImpl implements DataRowRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelDataRowRepositoryImpl.class);

    @Override
    public void save(String dest, List<DataRow> rows) {
        for (DataRow row : rows) {
            File folderPath = PathConstructorHelper.constructPath(dest, row.getName());

            for (String link : row.getLinks()) {
                try {
                    File fullPath = PathConstructorHelper.addImageNameToPath(folderPath.getAbsolutePath(), link);


                    FileUtils.copyURLToFile(new URL(link), fullPath);
                    LOGGER.info("Image has been downloaded: {}", fullPath.getCanonicalPath());
                } catch (Exception e) {
                    LOGGER.error("Something has gone wrong. Probably the file cannot be found.", e.getMessage());
                }
            }
        }
    }


}
