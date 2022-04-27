package com.kodilla.project;


import com.opencsv.*;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class ReadAndGeneratePickupNumber {

    public void read (String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Path path = Paths.get(file.getPath());

        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(String.valueOf(path))).build();

            List<DriverAndLicencePlate> driverAndLicencePlates = reader.readAll().stream().map(data -> {
                DriverAndLicencePlate driverAndLicencePlate = new DriverAndLicencePlate();
                driverAndLicencePlate.setNameAndSurname(data[0]);
                driverAndLicencePlate.setLicencePlate(data[1]);
                return driverAndLicencePlate;
            }).collect(Collectors.toList());

            driverAndLicencePlates.forEach(System.out::println);

        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) throws Exception  {

        ReadAndGeneratePickupNumber readAndGeneratePickupNumber = new ReadAndGeneratePickupNumber();

        readAndGeneratePickupNumber.read("drivers.csv");

    }

}