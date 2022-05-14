package com.kodilla.project;

import java.util.*;
import java.util.logging.Logger;

public class DataBaseStorage {

        public static Set<String> PickUpNumbersSet = new HashSet<>();

        public static Deque<String> entryQueue = new ArrayDeque<>();

        public static Set<LogInByDriver> driversLoggedIn = new HashSet<>();

        public static Set<LoadingDrivers> loadingDriversSet = new HashSet<>();


}
