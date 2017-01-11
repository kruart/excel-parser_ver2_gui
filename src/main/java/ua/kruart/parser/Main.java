package ua.kruart.parser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kruart.parser.controller.MainController;

import java.io.IOException;


public class Main extends Application {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final String FXML_MAIN = "/fxml/main.fxml";
    private Stage primaryStage;
    private MainController mainController;
    private FXMLLoader fxmlLoader;
    private VBox currentRoot;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        createGUI();
    }

    private void createGUI() {
        currentRoot = loadFXML();
        Scene scene = new Scene(currentRoot, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);
        primaryStage.show();
    }

    // загружает дерево компонентов и возвращает в виде VBox (корневой элемент в FXML)
    private VBox loadFXML() {
        fxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN));


        VBox node = null;

        try {
            node = (VBox)fxmlLoader.load();

            mainController = fxmlLoader.getController();
            mainController.setMainStage(primaryStage);
            primaryStage.setTitle("Excel parser");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return node;
    }
}
