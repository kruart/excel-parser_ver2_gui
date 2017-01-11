package ua.kruart.parser.service;

import ua.kruart.parser.converter.ExcelFileToDataRowConverter;
import ua.kruart.parser.model.DataRow;
import ua.kruart.parser.repository.DataRowRepository;
import ua.kruart.parser.repository.ExcelDataRowRepositoryImpl;

import java.io.File;
import java.util.List;

/**
 * Contains business logic over DataRow objects
 */
public class ExcelDataRowService {

    private DataRowRepository repository = new ExcelDataRowRepositoryImpl();

    public void extractDataRowsFromFileAndSave(File file, File directory, int attrColumn, int linkColumn){
        List<DataRow> dataRowList = ExcelFileToDataRowConverter.extractDataFromFile(file, attrColumn, linkColumn);

        repository.save(directory.getAbsolutePath(), dataRowList);
    }
}
