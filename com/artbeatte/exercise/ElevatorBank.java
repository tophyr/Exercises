package com.artbeatte.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author art.beatte
 * @version 8/27/15
 */
public class ElevatorBank {

    private class Elevator {
        private int mFloor;
        private int mDestination;

        public Elevator() {
            mFloor = 1;
            mDestination = 1;
        }

        public int getFloor() {
            return mFloor;
        }

        public void setFloor(int Floor) {
            mFloor = Floor;
        }

        public int getDestination() {
            return mDestination;
        }

        public void setDestination(int destination) {
            mDestination = destination;
        }

        public boolean isIdle() {
            return getDestination() == getFloor();
        }

        private void goToLobby() {
            if (isIdle() && mFloor != 1) {
                mDestination = 1;
            }
        }

        /**
         * Method used to advance an {@link com.artbeatte.exercise.ElevatorBank.Elevator} one turn in time.
         */
        private void advance() {
            if (mFloor < mDestination) {
                mFloor++;
            } else if (mFloor > mDestination){
                mFloor--;
            }
        }

    }

    private int mFloors;
    private List<Elevator> mElevators;
    private Queue<Integer> requests;

    public ElevatorBank(int numFloors, int numElevators) {
        mFloors = numFloors;
        mElevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            mElevators.add(new Elevator());
        }
        requests = new ArrayBlockingQueue<>(mElevators.size());
        run();
    }

    private void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                printOutStatus();
                while (true) {
                    for (Elevator e : mElevators) {
                        // Elevator is idle
                        if (e.isIdle()) {
                            if (!requests.isEmpty()) {
                                e.setDestination(requests.remove());
                            } else {
                                e.goToLobby();
                            }
                        }
                        // Advance Elevators one Turn
                        e.advance();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException ignored) { }
                    printOutStatus();
                }
            }
        }).start();
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
            for (Elevator e : mElevators) {
                if (e.getFloor() == i) {
                    String status;
                    if (e.isIdle()) {
                        status = i == 1 ? "I" : "D";
                    } else {
                        status = "" + e.getDestination();
                    }
                    System.out.print(status + " |");
                } else {
                    System.out.print("  |");
                }
            }
            System.out.println();
        }
        System.out.println(lid);
    }

    public void selectFloor(int floor) {
        requests.offer(floor);
    }

    public static void main(String[] args) {
        ElevatorBank bank = new ElevatorBank(10, 3);
        bank.selectFloor(10);
        bank.selectFloor(3);
        bank.selectFloor(7);
        bank.selectFloor(5);
    }
}
