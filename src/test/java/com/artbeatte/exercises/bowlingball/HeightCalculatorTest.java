package com.artbeatte.exercises.bowlingball;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sarbs on 12/16/15.
 */
public class HeightCalculatorTest {

    private static final int[] BLDG_HEIGHTS = new int[] { 15, 100, 256, 291, 10068 };
    private static final HeightCalculator CALC = new SampleHeightCalculator();

    @Test
    public void testFindsCorrectHeight() throws Exception {
        for (int bldgHeight : BLDG_HEIGHTS) {
            for (int i = 1; i <= bldgHeight; i++) {
                BowlingBall a = new BowlingBall(i);
                BowlingBall b = new BowlingBall(i);
                assertEquals("failed to find break height at " + i + " with max " + bldgHeight,
                        i - 1, CALC.maxHeight(bldgHeight, a, b));
            }
        }
    }

    @Test
    public void testFindsCorrectHeightAtLeastLinearly() throws Exception {
        for (int bldgHeight : BLDG_HEIGHTS) {
            int maxDropCount = Integer.MIN_VALUE;
            for (int i = 1; i <= bldgHeight; i++) {
                BowlingBall a = new BowlingBall(i);
                BowlingBall b = new BowlingBall(i);
                assertEquals("failed to find break height at " + i + " with max " + bldgHeight,
                        i - 1, CALC.maxHeight(bldgHeight, a, b));
                maxDropCount = Math.max(maxDropCount, a.getDrops() + b.getDrops());
            }
            assertTrue("failed to make performance at height " + bldgHeight + ": perf " + maxDropCount + " expected " + bldgHeight,
                    maxDropCount <= bldgHeight);
        }
    }

    @Test
    public void testbeatsLinear() throws Exception {
        for (int bldgHeight : BLDG_HEIGHTS) {
            int maxDropCount = Integer.MIN_VALUE;
            for (int i = 1; i <= bldgHeight; i++) {
                BowlingBall a = new BowlingBall(i);
                BowlingBall b = new BowlingBall(i);
                assertEquals("failed to find break height at " + i + " with max " + bldgHeight,
                        i - 1, CALC.maxHeight(bldgHeight, a, b));
                maxDropCount = Math.max(maxDropCount, a.getDrops() + b.getDrops());
            }
            assertTrue("failed to make performance at height " + bldgHeight + ": perf " + maxDropCount + " expected " + Math.ceil(Math.sqrt(bldgHeight) * 2 - 1),
                    maxDropCount < Math.ceil(Math.sqrt(bldgHeight) * 2 - 1));
        }
    }

    @Test
    public void testachievesOptimal() throws Exception {
        for (int bldgHeight : BLDG_HEIGHTS) {
            int maxDropCount = Integer.MIN_VALUE;
            for (int i = 1; i <= bldgHeight; i++) {
                BowlingBall a = new BowlingBall(i);
                BowlingBall b = new BowlingBall(i);
                assertEquals("failed to find break height at " + i + " with max " + bldgHeight,
                        i - 1, CALC.maxHeight(bldgHeight, a, b));
                maxDropCount = Math.max(maxDropCount, a.getDrops() + b.getDrops());
            }
            assertTrue("failed to make performance at height " + bldgHeight + ": perf " + maxDropCount + " expected " + Math.ceil((Math.sqrt(8 * bldgHeight + 1) - 1) / 2),
                    maxDropCount <= Math.ceil((Math.sqrt(8 * bldgHeight + 1) - 1) / 2));
        }
    }
}
