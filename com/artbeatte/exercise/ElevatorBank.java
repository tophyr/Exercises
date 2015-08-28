package com.artbeatte.exercise;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author art.beatte
 * @version 8/27/15
 */
public class ElevatorBank {

    private class Elevator {
        private int mFloor;
        private int mDestination;
        private Request mRequest;

        public Elevator() {
            mFloor = 1;
            mDestination = 1;
            mRequest = null;
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

        public void setRequest(Request request) {
            mRequest = request;
            mDestination = request.mOrigin;
        }

        public Request getRequest() {
            return mRequest;
        }

        public boolean isIdle() {
            return mRequest == null;
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
            } else if (mRequest != null) {
                // we are ready to begin
                if (mFloor == mRequest.mOrigin && mRequest.mOrigin != mRequest.mDestination) {
                    mDestination = mRequest.mDestination;
                // we have arrived
                } else {
                    mRequest = null;
                }
            }
        }

    }

    private class Request {
        private int mOrigin;
        private int mDestination;

        public Request(int origin, int destination) {
            mOrigin = origin;
            mDestination = destination;
        }
    }

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
                while (true) {
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
                        status = "I";
                    } else if (e.getRequest().mDestination == i) {
                        status = "D";
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

    public void selectFloor(int fromFloor, int toFloor) {
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
