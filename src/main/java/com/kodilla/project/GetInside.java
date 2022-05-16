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
import org.apache.commons.logging.Log;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;

public class GetInside {

    PopUp popUp = new PopUp();
    private TextField pickUpNumberTF;
    private TextField tareField;
    private Integer tare;

    public void getInsideWindow (){

        Stage stage = new Stage();

        Label pickUpNumberLabel = new Label("Numer zaladunku: ");
        pickUpNumberTF = new TextField();

        Label tareLabel = new Label("Waga pustego zestawu: ");
        tareField = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(pickUpNumberLabel,0,0);
        gridPane.add(pickUpNumberTF,1,0);
        gridPane.add(tareLabel, 0,1);
        gridPane.add(tareField,1,1);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        pickUpNumberTF.setText("XXXXXX");
        tareField.setText("12000");

        HBox hBox = new HBox(gridPane);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hBox);
        vBox.setAlignment(Pos.CENTER);

        Button entryBtn = new Button("Wjedz");
        entryBtn.setOnAction(event -> {
            tare = Integer.parseInt(tareField.getText());
            getInsideforLoading();
            stage.close();
            App.refreshTableView();
            popUp.smallPopUp("Maksymalna mozliwa waga do zaladowania: " + countWeightToBeLoaded(tare)  + " kg");
        });

        vBox.setSpacing(15);
        vBox.getChildren().add(entryBtn);

        Scene scene = new Scene(vBox, 1400, 750, Color.GRAY);

        stage.setTitle("Wjazd");
        stage.setScene(scene);
        stage.show();

    }

    public void getInsideforLoading() {

        GetInside getInside = new GetInside();
        try {
            DataBaseStorage.driversLoggedIn.stream()
                    .filter(record -> record.getCalledInBoolean() == true)
                    .forEach(record -> {
                        if (!record.getPickUpNumber().equals(pickUpNumberTF.getText())) {
                            popUp.smallPopUp("Bledny numer zaladunku");
                        } else {
                            LoadingDrivers loadingDriver = new LoadingDrivers(record.getPickUpNumber(), record.getLicencePlate(), record.getNameAndSurname(), tare, tare);
                            DataBaseStorage.loadingDriversSet.add(loadingDriver);
                            getInside.removeFromMainTable(loadingDriver);
                            getInside.loadingStation(loadingDriver);
                            getInside.checkIfLoadingIsCorrect(loadingDriver);
                        }
                    });
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void removeFromMainTable(LoadingDrivers loadingDrivers){

        Iterator<LogInByDriver> driversLoggedInIterator = DataBaseStorage.driversLoggedIn.iterator();

        while(driversLoggedInIterator.hasNext()){
            LogInByDriver record = driversLoggedInIterator.next();
            if(loadingDrivers.getPickUpNumber().equals(record.getPickUpNumber())){
                DataBaseStorage.driversLoggedIn.remove(record);
            }
        }
    }

    public Integer countWeightToBeLoaded(Integer tare){

        int maxWeight = 40000;

        int weightToBeLoaded = maxWeight - tare;

        return weightToBeLoaded;
    }

    public Integer loadingStation(LoadingDrivers loadingDrivers){

        Random random = new Random();

        int loadedWeight = 0;

        while (loadingDrivers.getGrossWeight() <= 40000) {
            int loadingWeight = random.nextInt(1500);

            if  (loadingDrivers.getGrossWeight() + loadingWeight <= 40000) {
                loadedWeight += loadingWeight;
                loadingDrivers.setGrossWeight(loadingDrivers.getGrossWeight() + loadingWeight);
//                popUp.smallPopUp("Ladowanie: " + loadingDrivers.getGrossWeight());
            } else {
                break;
            }
        }

        return loadedWeight;
    }

    public void checkIfLoadingIsCorrect(LoadingDrivers loadingDrivers){

        if(loadingDrivers.getGrossWeight() <= 40000){
            popUp.smallPopUp("Zaladunek przebiegl prawidlowo!" + "\n"
                    + "Zaladowano: " + (loadingDrivers.getGrossWeight() - loadingDrivers.getTareWeight()) + " kg" + "\n"
                    + "Waga po zaladunku: " + loadingDrivers.getGrossWeight() + " kg");
        } else {
            popUp.smallPopUp("Zaladunek wykonany nieprawidlowo!" + "\n"
                                + "Przeladowano: " + (loadingDrivers.getGrossWeight() - 40000) + " kg");
        }
    }
}
