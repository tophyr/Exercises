package com.artbeatte.exercises.elevatorbank;

/**
 * @author art.beatte
 * @version 8/28/15
 */
public class Elevator {
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
        mDestination = request.getOrigin();
    }

    public Request getRequest() {
        return mRequest;
    }

    public boolean isIdle() {
        return mRequest == null;
    }

    void goToLobby() {
        if (isIdle() && mFloor != 1) {
            mDestination = 1;
        }
    }

    /**
     * Method used to advance an {@link Elevator} one turn in time.
     */
    void advance() {
        if (mFloor < mDestination) {
            mFloor++;
        } else if (mFloor > mDestination){
            mFloor--;
        } else if (mRequest != null) {
            // we are ready to begin
            if (mFloor == mRequest.getOrigin() && mRequest.getOrigin() != mRequest.getDestination()) {
                mRequest.setIsInFlight(true);
                mDestination = mRequest.getDestination();
                // we have arrived
            } else {
                mRequest.setIsInFlight(false);
                mRequest = null;
            }
        }
    }
}
