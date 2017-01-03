package ua.kruart.parser.model;

import java.util.List;

/**
 * Entity that encapulates one row from excel file
 *
 * @author kruart
 */
public class ExcelDataRow {

    /**
     * matches the name of attribute or product in excel file
     */
    private String name;

    private List<String> links;

    public ExcelDataRow() {
    }

    public ExcelDataRow(String name, List<String> links) {
        this.name = name;
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
