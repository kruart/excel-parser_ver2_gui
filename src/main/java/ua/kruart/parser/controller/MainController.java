package ua.kruart.parser.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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

    private Stage mainStage;

    private FileChooser fileChooser;
    private File fileChosen;


    @FXML
    private void hndlOpenFile(ActionEvent event) {
        //Класс работы с диалогом выборки и сохранения
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Microsoft Excel", "*.xls", "*.xlsx");  //Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        //Указываем текущую сцену
        fileChosen = fileChooser.showOpenDialog(this.getMainStage());
        if (fileChosen != null) {     //Open
            System.out.println("Процесс открытия файла");
            labelPathToFile.setText(fileChosen.getAbsolutePath());
        }
    }

    @FXML
    private void hndlChoosingDirectory(ActionEvent actionEvent) {
    }

    @FXML
    private void hndlStartParsing(ActionEvent actionEvent) {
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
