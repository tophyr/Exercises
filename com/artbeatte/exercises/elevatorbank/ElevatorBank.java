package com.artbeatte.exercises.elevatorbank;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author art.beatte
 * @version 8/27/15
 */
public class ElevatorBank {

    private int mFloors;
    private List<Elevator> mElevators;
    private List<Request> mRequests;

    public ElevatorBank(int numFloors, int numElevators) {
        mFloors = numFloors;
        mElevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            mElevators.add(new Elevator());
        }
        mRequests = new ArrayList<>(mElevators.size());
        run();
    }

    private void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                printOutStatus();
                while (working()) {
                    for (Elevator e : mElevators) {
                        // Elevator is idle
                        if (e.isIdle()) {
                            if (!mRequests.isEmpty()) {
                                e.setRequest(mRequests.remove(0));
                            } else {
                                e.goToLobby();
                            }
                        }
                        // Advance Elevators one Turn
                        e.advance();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ignored) { }
                    printOutStatus();
                }
                System.out.println();
                System.out.println("Thank you for riding the Art Plaza Elevator System.");
                System.out.println("Have a great day!");
                System.exit(0);
            }
        }).start();
    }

    private boolean working() {
        boolean working = false;
        for (Elevator e : mElevators) {
            working = working || (e.getRequest() != null || e.getFloor() != 1);
        }
        return working || !mRequests.isEmpty();
    }

    private void printOutStatus() {
        String lid = "";
        for (Elevator e: mElevators) {
            lid += "____";
        }
        System.out.println(lid);
        System.out.println("| Art Plaza |");
        System.out.println(lid);
        for (int i = mFloors; i > 0; i--) {
            System.out.print("|");
            int numMoving = 0;
            // draw elevators
            for (Elevator e : mElevators) {
                if (e.getFloor() == i) {
                    String status;
                    if (e.isIdle()) {
                        status = "I";
                    } else {
                        numMoving++;
                        if (e.getRequest().getDestination() == i) {
                            status = "D";
                        } else {
                            status = "" + e.getDestination();
                        }
                    }

                    System.out.print(status + " |");
                } else {
                    System.out.print("  |");
                }
            }
            // draw requests
            int numWaiting = 0;
            for (Request r : mRequests) {
                if (r.getOrigin() == i && !r.isInFlight()) {
                    numWaiting++;
                }
            }
            if (numWaiting > 0 || numMoving > 0) {
                System.out.print(" <-- ");
            }
            if (numMoving > 0) {
                for (int m = 0; m < numMoving; m++) {
                    System.out.print("m");
                }
            }
            if (numWaiting > 0) {
                for (int o = 0; o < numWaiting; o++) {
                    System.out.print("o");
                }
            }
            System.out.println();
        }
        System.out.println(lid);
    }

    private void selectFloor(int fromFloor, int toFloor) {
        mRequests.add(new Request(fromFloor, toFloor));
    }

    public static void main(String[] args) {
        ElevatorBank bank = new ElevatorBank(10, 3);
        bank.selectFloor(1, 10);
        bank.selectFloor(10, 3);
        bank.selectFloor(3, 7);
        bank.selectFloor(1, 5);
        bank.selectFloor(7, 7);
        bank.selectFloor(8, 2);
    }
}
