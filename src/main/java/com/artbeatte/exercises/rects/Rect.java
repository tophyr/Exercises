package com.artbeatte.exercises.rects;

/**
 * @author sarbs
 * @version 11/16/15
 */
public final class Rect {
    private final double x;
    private final double y;
    private final double width;
    private final double height;

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
}
