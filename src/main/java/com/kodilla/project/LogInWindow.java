package com.kodilla.project;

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
import org.apache.commons.logging.Log;

import java.awt.event.ItemEvent;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

public class LogInWindow {

    public String pickUpNumber;
    public String phoneNumber;
    public String nameAndSurname;
    public String licencePlate;
    public LocalDateTime registryTime;
        public void logInPickUpNumber () {

        GridPane gp = new GridPane();
        Stage stage = new Stage();

        Label pickUpNumberLabel = new Label("Numer zaladunku: ");
        TextField pickUpNumberField = new TextField();

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



        Button confirmBtn = new Button("Potwierdz");
        confirmBtn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                pickUpNumber = pickUpNumberField.getText();
                if (pickUpNumber != "") {
                    LogInByDriver logInByDriver = new LogInByDriver(pickUpNumber);
                    stage.close();
                    logInRestInformations();
                }

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
        TextField nameAndSurnameField = new TextField();
        Label phoneNumberLabel = new Label("Numer telefonu");
        TextField phoneNumberField = new TextField();
        Label licencePlateLabel = new Label("Numer rejestracyjny pojazdu");
        TextField licencePlateField = new TextField();

        gp.add(nameAndSurnameLabel, 0 , 0);
        gp.add(nameAndSurnameField, 1, 0);
        gp.add(phoneNumberLabel, 0,1);
        gp.add(phoneNumberField,1,1);
        gp.add(licencePlateLabel, 0, 2);
        gp.add(licencePlateField,1,2);
        gp.setHgap(10);
        gp.setVgap(10);

        phoneNumber = phoneNumberField.getText();
        nameAndSurname = nameAndSurnameField.getText();
        licencePlate = licencePlateField.getText();
        registryTime = LocalDateTime.now();

        Button addBtn = new Button("Dodaj");
        addBtn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                confirmationLogInWindow();
            }
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


       TableView<LogInByDriver> tblConfirmationTable = new TableView<>();
       tblConfirmationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

       VBox.setVgrow(tblConfirmationTable, Priority.ALWAYS);

       TableColumn<LogInByDriver, String> columnPickUpNumber = new TableColumn<>("Numer zaladunku");
       TableColumn<LogInByDriver, String> columnPhoneNumber = new TableColumn<>("Numer telefonu");
       TableColumn<LogInByDriver, String> columnNameAndSurname = new TableColumn<>("Imie i nazwisko");
       TableColumn<LogInByDriver, String> columnLicencePlate = new TableColumn<>("Numery rejestracyjne");

       columnPickUpNumber.setCellValueFactory(new PropertyValueFactory<>("pickumNumber"));
       columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
       columnPickUpNumber.setCellValueFactory(new PropertyValueFactory<>("nameAndSurname"));
       columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Licenceplate"));

       tblConfirmationTable.getColumns().addAll(columnPickUpNumber, columnPhoneNumber, columnNameAndSurname, columnLicencePlate);
       tblConfirmationTable.getItems().addAll();

       Button confirmBtn = new Button("Potwierdz");

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
}
