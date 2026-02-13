package main.java.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage stage) throws Exception {
        ObservableList<Item> data = FXCollections.observableArrayList();

        TableView table = new TableView<>();
        TableColumn<Item, String> positionCol = new TableColumn<>("Position");
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Item, Double> valueCol = new TableColumn<>("Value");
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        MainApp mainApp = new MainApp();

        table.getColumns().addAll(positionCol, valueCol);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("Калькулятор баннеров");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.show();



    }
}

