package com.kodilla.project;

import javafx.application.Platform;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.*;

public class SchedulerGetIn{

    LogInWindow logInWindow = new LogInWindow();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    List<String> tableFromQueue = new ArrayList<>();

    PopUp popUp = new PopUp();

    public void callToGetIn(){

        Runnable callInMessage = () -> {


            tableFromQueue.addAll(DataBaseStorage.entryQueue);

            for (LogInByDriver record:DataBaseStorage.driversLoggedIn){
                final String currentTime = logInWindow.formatDateTime(LocalDateTime.now());

                if(currentTime.equals(record.getRegistryTime())){
                    try {
                        if (tableFromQueue.size() > 1) {
                            record.setCalledIn(true);
                            Platform.runLater(() -> popUp.smallPopUp("Wjazd: " + record.getLicencePlate() + "\n" + "Kolejny do wjazdu: " + tableFromQueue.get(1)));
                            DataBaseStorage.entryQueue.remove(record.getLicencePlate());
                        } else {
                            record.setCalledIn(true);
                            Platform.runLater(() -> popUp.smallPopUp("Wjazd: " + record.getLicencePlate()));
                            DataBaseStorage.entryQueue.remove(record.getLicencePlate());
                        }
                    } catch (Exception e){
                        System.out.println(e);
                    }
                }

            }
        };

        scheduler.scheduleAtFixedRate(callInMessage, 1,55,SECONDS);


    }

}
