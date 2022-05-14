package com.kodilla.project;

import com.sun.javafx.scene.control.IntegerField;
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

public class GetInside {

    private TextField pickUpNumberTF;
    private TextField tareField;

    public void getInsideWindow (){

        Stage stage = new Stage();
        GetInside getInside = new GetInside();

        Label pickUpNumberLabel = new Label("Numer zaladunku: ");
        pickUpNumberTF = new TextField();

        Label tareLabel = new Label("Tare: ");
        tareField = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(pickUpNumberLabel,0,0);
        gridPane.add(pickUpNumberTF,1,0);
        gridPane.add(tareLabel, 0,1);
        gridPane.add(tareField,1,1);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hBox);
        vBox.setAlignment(Pos.CENTER);

        Button entryBtn = new Button("Wjedz");
        entryBtn.setOnAction(event ->
                checkStatus()
        );

        vBox.setSpacing(15);
        vBox.getChildren().add(entryBtn);

        Scene scene = new Scene(vBox, 1400, 750, Color.GRAY);

        stage.setTitle("Wjazd");
        stage.setScene(scene);
        stage.show();

    }

    public void checkStatus(){

        DataBaseStorage.driversLoggedIn.stream()
                .filter(rec -> rec.getCalledInBoolean() == true)
                .forEach(rec -> DataBaseStorage.loadingDriversSet.add(rec));

    }
}
