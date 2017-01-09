package ua.kruart.parser.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Main Controller of the GUI form.
 */
public class MainController {
    @FXML
    public TextField txtAttrColumn;
    @FXML
    public TextField txtLinksColumn;
    @FXML
    public Button btnChoosingExcel;
    @FXML
    public Button btnChoosingDirectory;
    @FXML
    public Button btnParsing;
    @FXML
    public Label labelPathToFile;
    @FXML
    public Label labelPathToDirectory;
    @FXML
    public Label labelStatus;


    public void actionButtonPressed(ActionEvent actionEvent) {

    }
}
