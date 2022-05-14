package com.kodilla.project;

import java.time.LocalDateTime;

public class LogInByDriver {

    private String pickUpNumber;
    private String phoneNumber;
    private String nameAndSurname;
    private String licencePlate;
    private LocalDateTime registryTime;
    private boolean calledIn;

    public LogInByDriver(String pickUpNumber, String phoneNumber, String nameAndSurname, String licencePlate) {
        this.pickUpNumber = pickUpNumber;
        this.phoneNumber = phoneNumber;
        this.nameAndSurname = nameAndSurname;
        this.licencePlate = licencePlate;
    }

    public LogInByDriver(String pickUpNumber, String phoneNumber, String nameAndSurname, String licencePlate, LocalDateTime registryTime, boolean calledIn) {
        this.pickUpNumber = pickUpNumber;
        this.phoneNumber = phoneNumber;
        this.nameAndSurname = nameAndSurname;
        this.licencePlate = licencePlate;
        this.registryTime = registryTime;
        this.calledIn = calledIn;
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

    public void setCalledIn(boolean calledIn) {
        this.calledIn = calledIn;
    }

    public String getRegistryTime(){
        LogInWindow logInWindow = new LogInWindow();
        String time = logInWindow.formatDateTime(registryTime);
        return time;
    }

    public String getCalledIn(){
        if (calledIn) {
            return "Wezwany";
        } else  {
            return "Oczekiwanie";
        }
    }

    public Boolean getCalledInBoolean(){

        if (calledIn) {
            return true;
        }
        return false;
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
