package com.kodilla.project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LogInByDriver {

    private String pickUpNumber;
    private String phoneNumber;
    private String nameAndSurname;
    private String licencePlate;
    private LocalDateTime registryTime;

    public LogInByDriver(String pickUpNumber){
        this.pickUpNumber = pickUpNumber;
    }
    public LogInByDriver(String phoneNumber, String nameAndSurname, String licencePlate, LocalDateTime registryTime) {
        this.phoneNumber = phoneNumber;
        this.nameAndSurname = nameAndSurname;
        this.licencePlate = licencePlate;
        this.registryTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    }

    public LogInByDriver(String pickUpNumber, String phoneNumber, String nameAndSurname, String licencePlate, LocalDateTime registryTime) {
        this.pickUpNumber = pickUpNumber;
        this.phoneNumber = phoneNumber;
        this.nameAndSurname = nameAndSurname;
        this.licencePlate = licencePlate;
        this.registryTime = registryTime;
    }
    public LogInByDriver(String pickUpNumber, String phoneNumber, String nameAndSurname, String licencePlate) {
        this.pickUpNumber = pickUpNumber;
        this.phoneNumber = phoneNumber;
        this.nameAndSurname = nameAndSurname;
        this.licencePlate = licencePlate;

    }

    public String getPickUpNumber() {
        return pickUpNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public LocalDateTime getRegistryTime() {
        return registryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogInByDriver that = (LogInByDriver) o;

        if (!pickUpNumber.equals(that.pickUpNumber)) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        if (!nameAndSurname.equals(that.nameAndSurname)) return false;
        return licencePlate.equals(that.licencePlate);
    }

    @Override
    public int hashCode() {
        int result = pickUpNumber.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + nameAndSurname.hashCode();
        result = 31 * result + licencePlate.hashCode();
        return result;
    }


}
