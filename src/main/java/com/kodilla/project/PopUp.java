package com.kodilla.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PopUp {

    public void smallPopUp(String information){
        GridPane gridPane = new GridPane();
        Stage stage = new Stage();

        Text text = new Text(information);
        text.setWrappingWidth(280);
        text.setTextAlignment(TextAlignment.CENTER);

        gridPane.add(text, 0,0);

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hBox);
        vBox.setAlignment(Pos.CENTER);

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> stage.close());
        vBox.setSpacing(15);
        vBox.getChildren().add(okButton);


        Scene scene = new Scene(vBox, 300, 150);

        stage.setTitle("Informacja");
        stage.setScene(scene);
        stage.show();


    }
}
