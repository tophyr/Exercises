package com.artbeatte.exercises.bowlingball;

/**
 * Created by sarbs on 12/16/15.
 */
public class SampleHeightCalculator extends HeightCalculator {
    @Override
    public int maxHeight(int maxDropPossible, BowlingBall first, BowlingBall second) {
        int bigStep = (int)Math.ceil((Math.sqrt(8 * maxDropPossible + 1) - 1) / 2);

        int dropHeight = bigStep;
        while (dropHeight <= maxDropPossible && !first.drop(dropHeight)) {
            dropHeight += --bigStep;
        }

        int secondDropHeight = dropHeight - bigStep + 1;
        while (secondDropHeight < dropHeight && !second.drop(secondDropHeight)) {
            secondDropHeight++;
        }

        return secondDropHeight - 1;
    }
}
