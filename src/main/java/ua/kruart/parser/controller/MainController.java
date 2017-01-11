package ua.kruart.parser.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ua.kruart.parser.service.ExcelDataRowService;

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

    private File selectedFile;
    private File selectedDirectory;
    private boolean isDataCorrect = true;
    private ExcelDataRowService service = new ExcelDataRowService();


    @FXML
    private void hndlOpenFile(ActionEvent event) {
        //Класс для работи с діалогом виборки та збереження
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");//Заголовок діалогу
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Microsoft Excel", "*.xls", "*.xlsx");  //Розширення
        fileChooser.getExtensionFilters().add(extFilter);
        //Вказуємо поточну сцену
        selectedFile = fileChooser.showOpenDialog(this.getMainStage());   //зберігаєм файл
        if (selectedFile != null) {
            labelSelectedFile.setText(selectedFile.getAbsolutePath());
        } else{
            labelSelectedFile.setText("Файл не вибрано");
        }
    }

    @FXML
    private void hndlChoosingDirectory(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Directory");  //Заголовок диалога
        //зберігаєм шлях в об'єкті File
        selectedDirectory = directoryChooser.showDialog(this.getMainStage());

        if(selectedDirectory != null){
            labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
            this.getMainStage().sizeToScene();
        } else{
            labelSelectedDirectory.setText("Директорію не вибрано");
        }
    }

    @FXML
    private void hndlStartParsing(ActionEvent actionEvent) {

        if (isAllFieldsCorrectFilled()) {
//            labelStatus.textProperty().bind("Статус: парсинг...");
            labelStatus.setText("Статус: парсинг...");


                int attrColumn = Integer.parseInt(txtAttrColumn.getText()) - 1;
                int linkColumn = Integer.parseInt(txtLinksColumn.getText()) - 1;
                service.extractDataRowsFromFileAndSave(selectedFile, selectedDirectory, attrColumn, linkColumn);
                labelStatus.setText("Статус: парсинг завершен");

        }
    }

    private boolean isAllFieldsCorrectFilled() {
        labelStatus.setText("Cтатус:");
        if (selectedFile != null && selectedDirectory != null   //if files not null and textField contains digits
                && txtAttrColumn.getText().matches("\\d+") && txtLinksColumn.getText().matches("\\d+")) {
            labelStatus.setTextFill(Color.web("#42e329"));

            isDataCorrect = true;

        } else {
            labelStatus.setTextFill(Color.web("#FF0000"));
            labelStatus.setText("Статус: не всі дані заповнені коректно!");
            isDataCorrect = false;
        }

        return isDataCorrect;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
