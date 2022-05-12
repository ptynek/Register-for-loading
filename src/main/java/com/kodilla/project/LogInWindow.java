package com.kodilla.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class LogInWindow {
    private TextField pickUpNumberField;
    private TextField phoneNumberField;
    private TextField nameAndSurnameField;
    private TextField licencePlateField;
    private LocalDateTime registryTime;


    private String pickUpNumber;

        public void logInPickUpNumber () {

        GridPane gp = new GridPane();
        Stage stage = new Stage();
        PopUp popUp = new PopUp();

        Label pickUpNumberLabel = new Label("Numer zaladunku: ");
        pickUpNumberField = new TextField();

        gp.add(pickUpNumberLabel, 0, 0);
        gp.add(pickUpNumberField, 1,0);

        gp.setHgap(10);
        gp.setVgap(10);

        HBox hbox = new HBox(gp);
        hbox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hbox);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 1400,750, Color.GRAY);

        stage.setTitle("Piotr Tynek - project");
        stage.setScene(scene);
        stage.show();

        DataBaseStorage dataBaseStorage = new DataBaseStorage();

        System.out.println("dataBaseStorage.PickUpNumbersSet (print in class LogInWindow: " + dataBaseStorage.PickUpNumbersSet);

        Button confirmBtn = new Button("Potwierdz");
        confirmBtn.setOnAction(event -> {
            pickUpNumber = pickUpNumberField.getText();
            System.out.println("pickUpNumberField: " + pickUpNumber);
            if (!pickUpNumber.equals("") && DataBaseStorage.PickUpNumbersSet.contains(pickUpNumber)) {
                stage.close();
                logInRestInformations();
            } else {
                pickUpNumberField.clear();
                popUp.smallPopUp("Bledny numer zaladunku");
            }

        });
        vBox.setSpacing(10);
        vBox.getChildren().add(confirmBtn);
    }
    public void logInRestInformations(){

        GridPane gp = new GridPane();
        Stage stage = new Stage();
        HBox hbox = new HBox(gp);
        hbox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hbox);
        vBox.setAlignment(Pos.CENTER);

        Label nameAndSurnameLabel = new Label("Imie i nazwisko: ");
        nameAndSurnameField = new TextField();
        Label phoneNumberLabel = new Label("Numer telefonu");
        phoneNumberField = new TextField();
        Label licencePlateLabel = new Label("Numer rejestracyjny pojazdu");
        licencePlateField = new TextField();

        gp.add(nameAndSurnameLabel, 0 , 0);
        gp.add(nameAndSurnameField, 1, 0);
        gp.add(phoneNumberLabel, 0,1);
        gp.add(phoneNumberField,1,1);
        gp.add(licencePlateLabel, 0, 2);
        gp.add(licencePlateField,1,2);
        gp.setHgap(10);
        gp.setVgap(10);

        Button addBtn = new Button("Dodaj");
        addBtn.setOnAction(event -> {
            stage.close();
            confirmationLogInWindow();
        });
        vBox.setSpacing(10);
        vBox.getChildren().add(addBtn);

        Scene scene = new Scene(vBox, 1400,750, Color.GRAY);

        stage.setTitle("Projekt Piotr Tynek");
        stage.setScene(scene);
        stage.show();
    }

    public void confirmationLogInWindow() {
       Stage stage = new Stage();
       PopUp popUp = new PopUp();

       registryTime = LocalDateTime.now();

       final ObservableList<LogInByDriver> dataForSummaryView = FXCollections.observableArrayList
                       (new LogInByDriver(pickUpNumberField.getText(), phoneNumberField.getText() , nameAndSurnameField.getText(), licencePlateField.getText()));

       TableView<LogInByDriver> tblConfirmationTable = new TableView<>();
       tblConfirmationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

       VBox.setVgrow(tblConfirmationTable, Priority.ALWAYS);

       TableColumn<LogInByDriver, String> columnPickUpNumber = new TableColumn<>("Numer zaladunku");
       TableColumn<LogInByDriver, String> columnPhoneNumber = new TableColumn<>("Numer telefonu");
       TableColumn<LogInByDriver, String> columnNameAndSurname = new TableColumn<>("Imie i nazwisko");
       TableColumn<LogInByDriver, String> columnLicencePlate = new TableColumn<>("Numery rejestracyjne");

       columnPickUpNumber.setCellValueFactory(new PropertyValueFactory<>("pickUpNumber"));
       columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
       columnNameAndSurname.setCellValueFactory(new PropertyValueFactory<>("nameAndSurname"));
       columnLicencePlate.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));

       tblConfirmationTable.getColumns().addAll(columnPickUpNumber, columnPhoneNumber, columnNameAndSurname, columnLicencePlate);
       tblConfirmationTable.setItems(dataForSummaryView);

       Button confirmBtn = new Button("Potwierdz");
       confirmBtn.setOnAction(event -> {
           LogInByDriver loggingIn = new LogInByDriver
                   (pickUpNumberField.getText(), phoneNumberField.getText(), nameAndSurnameField.getText(), licencePlateField.getText(), registryTime.plusMinutes(plusMinutesToGetIn()), false);
           DataBaseStorage.driversLoggedIn.add(loggingIn);
           stage.close();
           DataBaseStorage.PickUpNumbersSet.remove(pickUpNumber);
           popUp.smallPopUp("Szacowany czas oczekiwania to: " + plusMinutesToGetIn() + " minut");
           DataBaseStorage.entryQueue.offer(licencePlateField.getText());
           System.out.println("drivers.loggedinSize: " + DataBaseStorage.driversLoggedIn.size());

       });

       HBox hBox = new HBox(confirmBtn);
       hBox.setSpacing(8);

       VBox vBox = new VBox(tblConfirmationTable, confirmBtn);
       vBox.setSpacing(10);
       vBox.setPadding(new Insets(10));

       Scene scene = new Scene(vBox);

       stage.setTitle("Podsumowanie");
       stage.setScene(scene);
       stage.setHeight(376);
       stage.setWidth(667);
       stage.show();


    }

    private Integer plusMinutesToGetIn(){

            int minutes = 0;

            if (DataBaseStorage.entryQueue.size() == 0 ) {
                minutes = 1;
            } else {
                minutes = DataBaseStorage.entryQueue.size() * 10;
            }

            return minutes;
    }

    private String formatDateTime(LocalDateTime localDateTime) {
            String registryTime = "";

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            registryTime = localDateTime.format(dateTimeFormatter);

            return registryTime;
    }
}
