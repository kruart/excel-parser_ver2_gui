package ua.kruart.parser.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
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
    public Label labelSelectedFile;
    @FXML
    public Label labelSelectedDirectory;
    @FXML
    public Label labelStatus;

    private Stage mainStage;

    private FileChooser fileChooser;
    private File fileChosen;


    @FXML
    private void hndlOpenFile(ActionEvent event) {
        //Класс для работи с діалогом виборки та збереження
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");//Заголовок діалогу
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Microsoft Excel", "*.xls", "*.xlsx");  //Розширення
        fileChooser.getExtensionFilters().add(extFilter);
        //Вказуємо поточну сцену
        fileChosen = fileChooser.showOpenDialog(this.getMainStage());   //зберігаєм файл
        if (fileChosen != null) {
            labelSelectedFile.setText(fileChosen.getAbsolutePath());
        } else{
            labelSelectedFile.setText("Файл не вибрано");
        }
    }

    @FXML
    private void hndlChoosingDirectory(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Directory");  //Заголовок диалога
        File selectedDirectory = directoryChooser.showDialog(this.getMainStage());  //зберігаєм шлях в об'єкті File

        if(selectedDirectory != null){
            labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
            this.getMainStage().sizeToScene();
        } else{
            labelSelectedDirectory.setText("Директорію не вибрано");
        }
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
