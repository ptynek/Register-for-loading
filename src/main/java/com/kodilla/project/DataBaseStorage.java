package com.kodilla.project;


import java.util.*;

public class DataBaseStorage {

        public static Set<String> PickUpNumbersSet = new HashSet<>();

        public static Deque<String> entryQueue = new ArrayDeque<>();

        public static Set<LogInByDriver> driversLoggedIn = new HashSet<>();

        public static Set<LoadingDrivers> loadingDriversSet = new HashSet<>();

        public static Map<String, LoadInformations> resultMap = new HashMap<>();
}
