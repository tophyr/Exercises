package com.artbeatte.exercises.rects;

/**
 * Created by sarbs on 12/16/15.
 */
public abstract class Intersector {
    /**
     * Calculates the intersection (overlapping area) of two rectangles.
     *
     * @param a A rectangle
     * @param b Another rectangle
     * @return A new Rect object holding the overlapping area of the two given rectangles,
     *         OR null if the two rectangles do not intersect.
     * @throws IllegalArgumentException if either parameter is null.
     */
    public abstract Rect intersection(Rect a, Rect b);
}
