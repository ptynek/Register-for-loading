package com.kodilla.project;

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


public class PopUp {

    public static ArrayList<Integer> weightList = new ArrayList<>();

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

    public void testScene() {

        Label label;
        VBox vbox;
        Scene scene;
        Stage stage = new Stage();

        stage.setTitle("Ladowanie");

        Label loadingText = new Label("Ladowanie...");
        loadingText.setAlignment(Pos.TOP_CENTER);


        for (int x = 0; x < weightList.size(); x++) {
                label = new Label(weightList.get(x).toString() + "kg");
                vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);
                vbox.getChildren().add(label);
                scene = new Scene(vbox, 300, 200);
                System.out.println(weightList.get(x));
                stage.setScene(scene);
                stage.show();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //stage.setScene(scene);

    }

}
