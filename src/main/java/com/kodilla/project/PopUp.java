package com.kodilla.project;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


public class PopUp {

    public static ArrayList<Integer> weightList = new ArrayList<>();

    static boolean finisedLoading = false;

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
        okButton.setOnAction(event -> {
            stage.close();});
        vBox.setSpacing(15);
        vBox.getChildren().add(okButton);

        Scene scene = new Scene(vBox, 300, 150);

        stage.setTitle("Informacja");
        stage.setScene(scene);
        stage.show();
    }

    public void loadingPopUp() {

        Label label;
        VBox vbox;
        Scene scene;
        Stage stage = new Stage();

        stage.setTitle("Ladowanie");

        Label loadingText = new Label("Ladowanie...");
        loadingText.setAlignment(Pos.TOP_CENTER);


        int x = 0;
        label = new Label();
        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(label);
        scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Iterator<Integer> weightListIterator = weightList.iterator();
                if (weightListIterator.hasNext()) {
                    int nextIterate = weightListIterator.next();
                    weightListIterator.remove();
                    Platform.runLater(() -> label.setText(nextIterate + " kg"));
                }
            }
        }, 1000, 1000);
    }



}
