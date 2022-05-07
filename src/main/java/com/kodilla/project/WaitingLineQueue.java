package com.kodilla.project;

import java.util.ArrayDeque;
import java.util.Deque;

public class WaitingLineQueue {

    public Deque<LogInByDriver> waitingLineQueue (LogInByDriver logInByDriver){

        Deque<LogInByDriver> waitingLineQueue = new ArrayDeque<>();

        waitingLineQueue.offer(logInByDriver);

        System.out.println(waitingLineQueue.size());

        return new ArrayDeque<>();
    }
}
