package com.kodilla.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;
import java.util.Set;

public class DataBaseStorage {

        public static Set<String> PickUpNumbersSet = new HashSet<>();

        public boolean containsPickUpNumber(PickUpNumber pickUpNumber){

                if (PickUpNumbersSet.contains(pickUpNumber)){
                        return true;
                }
                return false;
        }

}
