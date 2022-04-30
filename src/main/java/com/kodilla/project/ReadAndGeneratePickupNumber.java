package com.kodilla.project;


import com.opencsv.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileReader;
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

        return pickUpNumber;
    }



}