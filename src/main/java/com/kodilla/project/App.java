package com.kodilla.project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.TableColumn;
import org.apache.commons.logging.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        LogInWindow logInWindow = new LogInWindow();

        DataBaseStorage.PickUpNumbersSet.add("XXXXXX");
        DataBaseStorage.PickUpNumbersSet.add("XXXXXY");
        DataBaseStorage.PickUpNumbersSet.add("XXXXXZ");
        DataBaseStorage.PickUpNumbersSet.add("XXXXXC");
        DataBaseStorage.PickUpNumbersSet.add("XXXXXV");

        HBox topControls = new HBox();
        VBox.setMargin(topControls, new Insets(10.0d));
        topControls.setAlignment(Pos.TOP_LEFT);

        Button loadAndGenerateNumbersBtn = new Button("Wygeneruj numery zaladunku");
        loadAndGenerateNumbersBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ReadAndGeneratePickupNumber readAndGeneratePickupNumber = new ReadAndGeneratePickupNumber();
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Otworz plik");
                    String fileName = fileChooser.showOpenDialog(stage).getName();
                    readAndGeneratePickupNumber.createFile(fileName);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        HBox topRightControls = new HBox();
        HBox.setHgrow(topRightControls, Priority.ALWAYS);
        topRightControls.setAlignment(Pos.TOP_RIGHT );
        Button registerBtn = new Button("Zarejestruj sie");
        topRightControls.getChildren().add(registerBtn);

        HBox testBtns = new HBox();
        testBtns.setSpacing(10);
        HBox.setHgrow(testBtns, Priority.ALWAYS);
        PopUp popUp = new PopUp();
        Button test = new Button("Test");
        testBtns.getChildren().add(test);
        testBtns.setAlignment(Pos.TOP_RIGHT);
        test.setOnAction(event -> popUp.smallPopUp("xxx"));

        topControls.getChildren().addAll(loadAndGenerateNumbersBtn, testBtns, topRightControls);

        registerBtn.setOnAction(event -> logInWindow.logInPickUpNumber());

        ObservableList<LogInByDriver> dataForLoggedInDrivers = FXCollections.observableArrayList();

        for (LogInByDriver record:DataBaseStorage.driversLoggedIn){
            dataForLoggedInDrivers.add(record);
        }

        TableView<LogInByDriver> loggedInDrivers = new TableView<>();
        loggedInDrivers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(loggedInDrivers, Priority.ALWAYS);

        TableColumn<LogInByDriver, String> colPickUpNumber = new TableColumn<>("Numer zaladunku");
        TableColumn<LogInByDriver, String> colPhoneNumber = new TableColumn<>("Numer telefonu");
        TableColumn<LogInByDriver, String> colNameAndSurname = new TableColumn<>("Imie i nazwisko");
        TableColumn<LogInByDriver, String> colLicencePlate = new TableColumn<>("Numer rejestracyjny");
        TableColumn<LogInByDriver, LocalDateTime> colEstimatedTimeToCallIn = new TableColumn<>("Przew. czas wjazdu");
        TableColumn<LogInByDriver, Boolean> colCalledIn = new TableColumn<>("Wezwany");

        colPickUpNumber.setCellValueFactory(new PropertyValueFactory<>("pickUpNumber"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colNameAndSurname.setCellValueFactory(new PropertyValueFactory<>("nameAndSurname"));
        colLicencePlate.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        colEstimatedTimeToCallIn.setCellValueFactory(new PropertyValueFactory<>("registryTime"));
        colCalledIn.setCellValueFactory(new PropertyValueFactory<>("calledIn"));

        loggedInDrivers.getColumns().addAll(
                colPickUpNumber, colPhoneNumber, colNameAndSurname, colLicencePlate, colEstimatedTimeToCallIn, colCalledIn
             );
        loggedInDrivers.setItems(dataForLoggedInDrivers);

        Button refreshBtn = new Button("refresh");
        refreshBtn.setOnAction(event -> {
            loggedInDrivers.refresh();
            System.out.println("drivers.loggedinSize: " + DataBaseStorage.driversLoggedIn.size());
            System.out.println("OBList: " + dataForLoggedInDrivers.size());
        });

        VBox vBox = new VBox(topControls, loggedInDrivers, refreshBtn);
        vBox.setPadding(new Insets(10, 10,20,10));

        Scene scene = new Scene(vBox, 1400, 750, Color.GRAY);

        stage.setTitle("Piotr Tynek - project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
