package com.artbeatte.exercises.elevatorbank;

/**
 * @author art.beatte
 * @version 8/28/15
 */
class Request {
    private int mOrigin;
    private int mDestination;

    Request(int origin, int destination) {
        mOrigin = origin;
        mDestination = destination;
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
}
