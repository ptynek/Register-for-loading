package com.kodilla.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.logging.Log;

import java.util.*;

public class DataBaseStorage {

        public static Set<String> PickUpNumbersSet = new HashSet<>();

        public static Deque<String> entryQueue = new ArrayDeque<>();

        public static Set<LogInByDriver> driversLoggedIn = new HashSet<>();


}
