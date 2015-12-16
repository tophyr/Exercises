package com.artbeatte.exercises.bowlingball;

/**
 * Created by sarbs on 12/16/15.
 */
public abstract class HeightCalculator {
    /**
     * Finds the maximum height at which a pair of identical {@link BowlingBall}s can be dropped without the balls
     * breaking.
     *
     * @param maxDropPossible The maximum height at which it is possible to drop a ball from, regardless of whether the
     *                        ball will break at that height or not. ("Max height of the building being tested")
     * @param first           A {@link BowlingBall} that will break at a predetermined height
     * @param second          Another {@link BowlingBall} that will break at the same height
     * @return The maximum height at which more identical balls can be dropped without them breaking.
     */
    public abstract int maxHeight(int maxDropPossible, BowlingBall first, BowlingBall second);
}
