package com.kodilla.project;


import com.opencsv.*;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class ReadAndGeneratePickupNumber {

    public Set<DriverAndLicencePlate> readFile (String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Path path = Paths.get(file.getPath());

        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(String.valueOf(path))).build();

            return reader.readAll().stream().map(data -> {
                DriverAndLicencePlate driverAndLicencePlate = new DriverAndLicencePlate();
                driverAndLicencePlate.setNameAndSurname(data[0]);
                driverAndLicencePlate.setLicencePlate(data[1]);
                return driverAndLicencePlate;
            }).collect(Collectors.toSet());

        } catch (Exception e){
            System.out.println(e);
        }
        return new HashSet<>();
    }

    public String pickUpNumber() {

        String pickUpNumber = "";

        pickUpNumber = RandomStringUtils.randomAlphanumeric(6);

        return pickUpNumber.toUpperCase();
    }

    public void createFile(String fileName) throws Exception{
        ReadAndGeneratePickupNumber readAndGeneratePickupNumber = new ReadAndGeneratePickupNumber();
        DataBaseStorage dataBaseStorage = new DataBaseStorage();
        Set<DriverAndLicencePlate> driverAndLicencePlateSet = readAndGeneratePickupNumber.readFile(fileName);
        Map<PickUpNumber, DriverAndLicencePlate> driverAndLicencePlateMap = new HashMap<>();

        Iterator<DriverAndLicencePlate> iterator = driverAndLicencePlateSet.iterator();
        while(iterator.hasNext()) {
            DriverAndLicencePlate record = iterator.next();
            PickUpNumber pickUpNumber = new PickUpNumber(readAndGeneratePickupNumber.pickUpNumber());
            if (dataBaseStorage.PickUpNumbersSet.contains(pickUpNumber)) {
                pickUpNumber = new PickUpNumber(readAndGeneratePickupNumber.pickUpNumber());
            }
            dataBaseStorage.PickUpNumbersSet.add(String.valueOf(pickUpNumber));
            driverAndLicencePlateMap.put(pickUpNumber, record);
        }

        System.out.println("dataBaseStorage.PickUpNumbersSet (in ReadAndGeneratePickUpNumber): " + dataBaseStorage.PickUpNumbersSet);

        //Scheduler executor service

        String path = "C:\\Users\\tynek\\Desktop\\Projects\\my-project\\src\\main\\resources\\";

        File myFile = new File(path + "Numery zaladunkow.txt");
        myFile.createNewFile();

        try {
            FileWriter myWriter = new FileWriter(path + "Numery zaladunkow.txt");
            for(Map.Entry<PickUpNumber, DriverAndLicencePlate> entry :driverAndLicencePlateMap.entrySet()){
                myWriter.write(("Numer zaladunku: " + entry.getKey() + "\n"
                        + "Kierowca: " +  entry.getValue().getNameAndSurname() + "\n"
                        + "Pojazd: " + entry.getValue().getLicencePlate() + "\n\n"));
            }
            myWriter.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }
}