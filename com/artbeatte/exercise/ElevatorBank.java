package com.artbeatte.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

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

    private List<Elevator> mElevators;
    private Queue<Integer> requests;

    public ElevatorBank(int numElevators) {
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
                    printOutStatus();
                }
            }
        }).start();
    }

    private void printOutStatus() {
        for (Elevator e : mElevators) {
            if (e.isIdle()) {
                System.out.println("Elevator at floor " + e.getFloor() + " is idle");
            } else {
                System.out.println("Elevator at floor " + e.getFloor() + " is going to floor " + e.getDestination());
            }
        }
    }

    public void selectFloor(int floor) {
        requests.offer(floor);
    }

    public static void main(String[] args) {
        ElevatorBank bank = new ElevatorBank(3);
        bank.selectFloor(10);
        bank.selectFloor(3);
        bank.selectFloor(7);
        bank.selectFloor(5);
    }
}
