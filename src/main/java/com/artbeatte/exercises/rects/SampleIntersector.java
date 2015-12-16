package com.artbeatte.exercises.rects;

/**
 * Created by sarbs on 12/16/15.
 */
public class SampleIntersector extends Intersector {
    @Override
    public Rect intersection(Rect a, Rect b) {
        if (a == null || b == null)
            throw new IllegalArgumentException("no nulls dick");

        double left = Math.max(a.getX(), b.getX());
        double right = Math.min(a.getX() + a.getWidth(), b.getX() + b.getWidth());
        double top = Math.max(a.getY(), b.getY());
        double bottom = Math.min(a.getY() + a.getHeight(), b.getY() + b.getHeight());

        if (left >= right || top >= bottom)
            return null;

        return new Rect(left, top, right - left, bottom - top);
    }
}
