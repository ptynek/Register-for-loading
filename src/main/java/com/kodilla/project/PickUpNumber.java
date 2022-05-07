package com.kodilla.project;

public class PickUpNumber {
    String pickUpNumber;
    public PickUpNumber(String pickUpNumber) {
        this.pickUpNumber = pickUpNumber;
    }
    public String getPickUpNumber() {
        return pickUpNumber;
    }
    public void setPickUpNumber(String pickUpNumber) {
        this.pickUpNumber = pickUpNumber;
    }
    @Override
    public String toString() {
        return pickUpNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PickUpNumber that = (PickUpNumber) o;

        return pickUpNumber.equals(that.pickUpNumber);
    }

    @Override
    public int hashCode() {
        return pickUpNumber.hashCode();
    }
}
