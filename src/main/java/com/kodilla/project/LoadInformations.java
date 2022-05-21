package com.kodilla.project;

public class LoadInformations {

    String licencePlate;
    String nameAndSurname;
    Integer tare;
    Integer grossWeight;

    Boolean createdHMTL;


    public LoadInformations(String licencePlate, String nameAndSurname, Integer tare, Integer grossWeight, boolean createdHMTL) {
        this.licencePlate = licencePlate;
        this.nameAndSurname = nameAndSurname;
        this.tare = tare;
        this.grossWeight = grossWeight;
        this.createdHMTL = createdHMTL;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public Integer getTare() {
        return tare;
    }

    public Integer getGrossWeight() {
        return grossWeight;
    }

    public Boolean getCreatedHMTL() {
        return createdHMTL;
    }

    public void setCreatedHMTL(Boolean createdHMTL) {
        this.createdHMTL = createdHMTL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoadInformations that = (LoadInformations) o;

        if (!licencePlate.equals(that.licencePlate)) return false;
        if (!nameAndSurname.equals(that.nameAndSurname)) return false;
        if (!tare.equals(that.tare)) return false;
        return grossWeight.equals(that.grossWeight);
    }

    @Override
    public int hashCode() {
        int result = licencePlate.hashCode();
        result = 31 * result + nameAndSurname.hashCode();
        result = 31 * result + tare.hashCode();
        result = 31 * result + grossWeight.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LoadInformations{" +
                "licencePlate='" + licencePlate + '\'' +
                ", nameAndSurname='" + nameAndSurname + '\'' +
                ", tare=" + tare +
                ", grossWeight=" + grossWeight +
                ", createdHMTL=" + createdHMTL +
                '}';
    }
}
