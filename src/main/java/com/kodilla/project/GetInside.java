package com.kodilla.project;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Iterator;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;
import static org.apache.commons.lang3.StringUtils.join;

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
            if (pickUpNumberTF.getText() != "" && tareField.getText() != ""){
            tare = Integer.parseInt(tareField.getText());
            getInsideforLoading();
            stage.close();
            App.refreshTableView();
            } else {
                showMessageDialog(null, "Pola nie moga byc puste");
            }
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
        Iterator<LogInByDriver> driversLoggedInIterator = DataBaseStorage.driversLoggedIn.iterator();

        if (driversLoggedInIterator.hasNext()) {
            LogInByDriver record = driversLoggedInIterator.next();
            if (record.getCalledInBoolean() && record.getPickUpNumber().equals(pickUpNumberTF.getText())) {
                LoadingDrivers loadingDriver = new LoadingDrivers(record.getPickUpNumber(), record.getLicencePlate(), record.getNameAndSurname(), tare, tare);
                DataBaseStorage.loadingDriversSet.add(loadingDriver);
                getInside.removeFromMainTable(loadingDriver);
                getInside.loadingStation(loadingDriver);
                popUp.loadingPopUp();
                getInside.checkIfLoadingIsCorrect(loadingDriver);
            } else {
                popUp.smallPopUp("Bledny numer zaladunku!");
            }
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

    public Integer loadingStation(LoadingDrivers loadingDrivers){

        Random random = new Random();

        int loadedWeight = 0;

        while (loadingDrivers.getGrossWeight() <= 40000) {
            int loadingWeight = random.nextInt(2000);

            if  (loadingDrivers.getGrossWeight() + loadingWeight <= 40000) {
                loadedWeight += loadingWeight;
                loadingDrivers.setGrossWeight(loadingDrivers.getGrossWeight() + loadingWeight);
                PopUp.weightList.add(loadingDrivers.getGrossWeight());

            } else {
                break;
            }
        }

        return loadedWeight;
    }

    public void checkIfLoadingIsCorrect(LoadingDrivers loadingDrivers){
        CreateHTML createHTML = new CreateHTML();
        if(loadingDrivers.getGrossWeight() <= 40000){
            popUp.smallPopUp("Zaladunek przebiegl prawidlowo!" + "\n"
                    + "Zaladowano: " + (loadingDrivers.getGrossWeight() - loadingDrivers.getTareWeight()) + " kg" + "\n"
                    + "Waga po zaladunku: " + loadingDrivers.getGrossWeight() + " kg");
            LoadInformations loadInformations = new LoadInformations
                    (loadingDrivers.getLicencePlate(), loadingDrivers.getNameAndSurname(), loadingDrivers.getTareWeight(), loadingDrivers.getGrossWeight(), false);
            DataBaseStorage.resultMap.put
                    (loadingDrivers.getPickUpNumber(), loadInformations);
            createHTML.createHTMLFile();
        } else {
            popUp.smallPopUp("Zaladunek wykonany nieprawidlowo!" + "\n"
                                + "Przeladowano: " + (loadingDrivers.getGrossWeight() - 40000) + " kg");
        }
    }
}
