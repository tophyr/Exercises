package com.artbeatte.exercises.rects;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by sarbs on 12/16/15.
 */
public class IntersectorTest {

    public static final Intersector INTERSECTOR = new SampleIntersector();

    @Test
    public void testIntersectValid1() {
        Rect a = new Rect(0, 0, 10, 10);
        Rect b = new Rect(5, 5, 10, 10);
        Rect i = INTERSECTOR.intersection(a, b);
        assertTrue(i.getX() == 5 && i.getY() == 5 && i.getWidth() == 5 && i.getHeight() == 5);
    }

    @Test
    public void testIntersectValid2() {
        Rect a = new Rect(-5, -5, 12, 12);
        Rect b = new Rect(5, 5, 10, 10);
        Rect i = INTERSECTOR.intersection(a, b);
        assertTrue(i.getX() == 5 && i.getY() == 5 && i.getWidth() == 2 && i.getHeight() == 2);
    }

    @Test
    public void testIntersectValid3() {
        Rect a = new Rect(5, 5, 10, 10);
        Rect b = new Rect(-5, -5, 12, 12);
        Rect i = INTERSECTOR.intersection(a, b);
        assertTrue(i.getX() == 5 && i.getY() == 5 && i.getWidth() == 2 && i.getHeight() == 2);
    }

    @Test
    public void testNoIntersect1() {
        Rect a = new Rect(5, 5, 10, 10);
        Rect b = new Rect(-25, -25, 12, 12);
        Rect i = INTERSECTOR.intersection(a, b);
        assertTrue(i == null);
    }

    @Test
    public void testNoIntersect2() {
        Rect a = new Rect(5, 5, 10, 10);
        Rect b = new Rect(-5, -5, 10, 10);
        Rect i = INTERSECTOR.intersection(a, b);
        assertTrue(i == null);
    }

    @Test
    public void testFullyInside() {
        Rect a = new Rect(0, 0, 10, 10);
        Rect b = new Rect(5, 5, 2, 2);
        Rect i = INTERSECTOR.intersection(a, b);
        assertTrue(i.getX() == 5 && i.getY() == 5 && i.getWidth() == 2 && i.getHeight() == 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRect() {
        Rect a = new Rect(0, 0, 10, 10);
        Rect b = null;
        Rect i = INTERSECTOR.intersection(a, b);
    }
}
