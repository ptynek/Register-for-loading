package com.kodilla.project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class LogInWindow {

    private StringProperty pickUpNumber = new SimpleStringProperty();
    private StringProperty nameAndSurname = new SimpleStringProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty licencePlate = new SimpleStringProperty();

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

        String pickUpNumber = pickUpNumberField.getText();

        Button confirmBtn = new Button("Potwierdz");
        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (pickUpNumber != "") {
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

        Button addBtn = new Button("Dodaj");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        vBox.setSpacing(10);
        vBox.getChildren().add(addBtn);

        Scene scene = new Scene(vBox, 1400,750, Color.GRAY);

        stage.setTitle("Projekt Piotr Tynek");
        stage.setScene(scene);
        stage.show();

    }
}
