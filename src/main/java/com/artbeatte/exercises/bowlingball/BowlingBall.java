package com.artbeatte.exercises.bowlingball;

/**
 * @author sarbs
 * @version 11/16/15
 */
public final class BowlingBall {
    private final int breakingHeight;
    private boolean broken;
    private int drops;

    public BowlingBall(int breakingHeight) {
        this.breakingHeight = breakingHeight;
    }

    /**
     * Tests if a ball will break when dropped from a particular height. The ball will never break at 0 height.
     *
     * @param height Height to test the ball from.
     * @return Whether or not the ball broke during the test.
     * @throws IllegalStateException if the ball is already broken.
     */
    public boolean drop(int height) {
        if (broken)
            throw new IllegalStateException("ball is already broken!");

        drops++;

        broken = height >= breakingHeight;
        return broken;
    }

    public boolean isBroken() {
        return broken;
    }

    /**
     * @return The number of times this ball has been dropped.
     */
    public int getDrops() {
        return drops;
    }
}
