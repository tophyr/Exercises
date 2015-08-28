package com.artbeatte.exercises.elevatorbank;

/**
 * @author art.beatte
 * @version 8/28/15
 */
class Request {
    private int mOrigin;
    private int mDestination;
    private boolean mIsInFlight;

    Request(int origin, int destination) {
        mOrigin = origin;
        mDestination = destination;
        mIsInFlight = false;
    }

    public int getOrigin() {
        return mOrigin;
    }

    public void setOrigin(int mOrigin) {
        this.mOrigin = mOrigin;
    }

    public int getDestination() {
        return mDestination;
    }

    public void setDestination(int mDestination) {
        this.mDestination = mDestination;
    }

    public boolean isInFlight() {
        return mIsInFlight;
    }

    public void setIsInFlight(boolean isInFlight) {
        this.mIsInFlight = isInFlight;
    }
}
