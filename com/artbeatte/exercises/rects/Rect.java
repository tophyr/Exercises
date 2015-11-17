package com.artbeatte.exercises.rects;

import com.artbeatte.exercises.testing.ExternalTestCase;
import com.artbeatte.exercises.testing.SystemTestRunner;
import com.artbeatte.exercises.testing.TestRunner;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sarbs on 11/16/15.
 */
public class Rect {
    private final double x;
    private final double y;
    private final double width;
    private final double height;

    /**
     * Creates a Rect object.
     * @param x The x-coordinate of the upper left corner.
     * @param y The y-coordinate of the upper left corner.
     * @param width The width of the rectangle. Must be >0.
     * @param height The height of the rectangle. Must be >0.
     */
    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Calculates the intersection (overlapping area) of two rectangles.
     *
     * @param a A rectangle
     * @param b Another rectangle
     * @return A new Rect object holding the overlapping area of the two given rectangles,
     *         OR null if the two rectangles do not intersect.
     * @throws IllegalArgumentException if either parameter is null.
     */
    public static Rect intersection(Rect a, Rect b)  {
        throw new NotImplementedException();
    }

    public static void main(String[] args) {
        TestRunner testRunner = new SystemTestRunner();

        testRunner.addTestCase(new ExternalTestCase("validRect1", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                new Rect(0, 0, 10, 10);
                return true;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("validRect2", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                new Rect(100000, 100000, 500000, 50000);
                return true;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("validRect3", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                new Rect(.00001, .000005, .000003, .00000008);
                return true;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("validRect4", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                new Rect(-1, -10, 5, 10);
                return true;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("validRect5", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                new Rect(-501925, 291, .000000005, 102957102);
                return true;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("invalidRect1", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                try {
                    new Rect(0, 0, 0, 0);
                } catch (Exception e) {
                    return true;
                }
                return false;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("invalidRect2", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                try {
                    new Rect(-10, -10, -10, -10);
                } catch (Exception e) {
                    return true;
                }
                return false;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("invalidRect3", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                try {
                    new Rect(81028, 1028, 6838, Double.NaN);
                } catch (Exception e) {
                    return true;
                }
                return false;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("invalidRect4", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                try {
                    new Rect(Double.NEGATIVE_INFINITY, 2957, Double.POSITIVE_INFINITY, 59);
                } catch (Exception e) {
                    return true;
                }
                return false;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("intersectValid1", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                Rect a = new Rect(0, 0, 10, 10);
                Rect b = new Rect(5, 5, 10, 10);
                Rect i = intersection(a, b);
                return (i.getX() == 5 && i.getY() == 5 && i.getWidth() == 5 && i.getHeight() == 5);
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("intersectValid2", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                Rect a = new Rect(-5, -5, 12, 12);
                Rect b = new Rect(5, 5, 10, 10);
                Rect i = intersection(a, b);
                return (i.getX() == 5 && i.getY() == 5 && i.getWidth() == 2 && i.getHeight() == 2);
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("intersectValid2", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                Rect a = new Rect(5, 5, 10, 10);
                Rect b = new Rect(-5, -5, 12, 12);
                Rect i = intersection(a, b);
                return (i.getX() == 5 && i.getY() == 5 && i.getWidth() == 2 && i.getHeight() == 2);
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("noIntersect1", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                Rect a = new Rect(5, 5, 10, 10);
                Rect b = new Rect(-25, -25, 12, 12);
                Rect i = intersection(a, b);
                return i == null;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("noIntersect2", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                Rect a = new Rect(5, 5, 10, 10);
                Rect b = new Rect(-5, -5, 10, 10);
                Rect i = intersection(a, b);
                return i == null;
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("fullyInside", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                Rect a = new Rect(0, 0, 10, 10);
                Rect b = new Rect(5, 5, 2, 2);
                Rect i = intersection(a, b);
                return (i.getX() == 5 && i.getY() == 5 && i.getWidth() == 2 && i.getHeight() == 2);
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("fullyInside", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                Rect a = new Rect(0, 0, 10, 10);
                Rect b = new Rect(5, 5, 2, 2);
                Rect i = intersection(a, b);
                return (i.getX() == 5 && i.getY() == 5 && i.getWidth() == 2 && i.getHeight() == 2);
            }
        }));

        testRunner.addTestCase(new ExternalTestCase("nullRect", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                Rect a = new Rect(0, 0, 10, 10);
                Rect b = null;
                try {
                    Rect i = intersection(a, b);
                } catch (IllegalArgumentException ex) {
                    return true;
                }
                return false;
            }
        }));

        testRunner.runTests();
    }
}
