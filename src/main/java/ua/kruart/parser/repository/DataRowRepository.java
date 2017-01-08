package ua.kruart.parser.repository;

import ua.kruart.parser.model.DataRow;

import java.util.List;

/**
 * Defines methods to manipulate DataRow objects in the persistent storage
 *
 * @author kruart
 */
public interface DataRowRepository {

    /**
     * Saves list of the DataRow objects in the file system
     */
    public void save(String dest, List<DataRow> rows);
}
