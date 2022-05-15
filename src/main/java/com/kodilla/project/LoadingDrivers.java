package com.kodilla.project;

public class LoadingDrivers {

    private String pickUpNumber;
    private String licencePlate;
    private String nameAndSurname;
    private Integer tareWeight;

    public LoadingDrivers(String pickUpNumber, String licencePlate, String nameAndSurname, Integer tareWeight) {
        this.pickUpNumber = pickUpNumber;
        this.licencePlate = licencePlate;
        this.nameAndSurname = nameAndSurname;
        this.tareWeight = tareWeight;
    }

    public String getPickUpNumber() {
        return pickUpNumber;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public Integer getTareWeight() {
        return tareWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoadingDrivers that = (LoadingDrivers) o;

        if (!pickUpNumber.equals(that.pickUpNumber)) return false;
        if (!licencePlate.equals(that.licencePlate)) return false;
        if (!nameAndSurname.equals(that.nameAndSurname)) return false;
        return tareWeight.equals(that.tareWeight);
    }

    @Override
    public int hashCode() {
        int result = pickUpNumber.hashCode();
        result = 31 * result + licencePlate.hashCode();
        result = 31 * result + nameAndSurname.hashCode();
        return result;
    }
}

