package com.kodilla.project;

import java.util.*;

public class LoggingIn {

    public boolean checkLength(String pickUpNumber){
        if (pickUpNumber.length() < 6 ){
            System.out.println("Numer załadunku jest za krótki.");
            return false;
        } else if(pickUpNumber.length() > 6) {
            System.out.println("Numer załadunkowy jest za długi.");
            return false;
        }
        return true;
    }

    public void logInInformationByDriver(){

        Scanner scanner = new Scanner(System.in);
        LoggingIn loggingIn = new LoggingIn();
        String pickUpNumber = "";

        boolean x = false;

        while (x != true) {
            System.out.println("Numer załadunku");
            pickUpNumber = scanner.nextLine().toUpperCase();
            x = loggingIn.checkLength(pickUpNumber);
        }

        System.out.println("Numer telefonu");
        String phoneNumber = scanner.nextLine();
        System.out.println("Imię i nazwisko");
        String nameAndSurname = scanner.nextLine().toUpperCase();
        System.out.println("Tablice rejestracyjne pojazdu");
        String licencePlate = scanner.nextLine().toUpperCase();

        LogInByDriver logInByDriver = new LogInByDriver(pickUpNumber, phoneNumber, nameAndSurname, licencePlate);

    }

    public Map<String, DriverAndLicencePlate> pickUpNumberLicencePlateAndDriversMap(String fileName){

        ReadAndGeneratePickupNumber readAndGeneratePickupNumber = new ReadAndGeneratePickupNumber();
        Set<DriverAndLicencePlate> fileWithDataSet = readAndGeneratePickupNumber.readFile(fileName);

        Map<String, DriverAndLicencePlate> resultMap = new HashMap<>();

        Iterator<DriverAndLicencePlate> driverAndLicencePlateIterator = fileWithDataSet.iterator();

        while(driverAndLicencePlateIterator.hasNext()){
            DriverAndLicencePlate currentLicencePlate = driverAndLicencePlateIterator.next();
            resultMap.put(readAndGeneratePickupNumber.pickUpNumber(), currentLicencePlate);
        }

        return new HashMap<>();
        }

    public static void main(String[] args) {

        LoggingIn loggingIn = new LoggingIn();


    }


}


