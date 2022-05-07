package com.kodilla.project;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        LogInWindow logInWindow = new LogInWindow();

        VBox vBox = new VBox();

        HBox topControls = new HBox();
        VBox.setMargin(topControls, new Insets(10.0d));
        topControls.setAlignment(Pos.BOTTOM_LEFT);

        Button loadAndGenerateNumbersBtn = new Button("Wygeneruj numery zaladunku");
        loadAndGenerateNumbersBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ReadAndGeneratePickupNumber readAndGeneratePickupNumber = new ReadAndGeneratePickupNumber();
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Otworz plik");
                    String fileName = fileChooser.showOpenDialog(stage).getName();
                    readAndGeneratePickupNumber.readFile(fileName);
                    readAndGeneratePickupNumber.pickUpNumber();
                    readAndGeneratePickupNumber.createFile();

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        HBox topRightControls = new HBox();
        HBox.setHgrow(topRightControls, Priority.ALWAYS);
        topRightControls.setAlignment(Pos.BOTTOM_RIGHT );
        Button registerBtn = new Button("Zarejestruj sie");
        topRightControls.getChildren().add(registerBtn);
        topControls.getChildren().addAll(loadAndGenerateNumbersBtn, topRightControls);

        registerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logInWindow.logInPickUpNumber();

            }
        });

        vBox.getChildren().add(topControls);

        Scene scene = new Scene(vBox, 1400, 750, Color.GRAY);

        stage.setTitle("Piotr Tynek - project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
