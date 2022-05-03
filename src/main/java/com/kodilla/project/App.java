package com.kodilla.project;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private StringProperty nameAndSurname = new SimpleStringProperty();


    @Override
    public void start(Stage stage) throws Exception {
       // Group root = new Group();


        Label nameAndSurnameLable = new Label("Imie i nazwisko: ");
        TextField nameAndSurnameField =  new TextField();

        GridPane gp = new GridPane();
        gp.add(nameAndSurnameLable, 0,0);
        gp.add(nameAndSurnameField, 1, 0);
        gp.setHgap(10);
        gp.setVgap(10);

        HBox hbox = new HBox(gp);
        hbox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hbox);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 1400, 750, Color.GRAY);

        stage.setTitle("Piotr Tynek - project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
