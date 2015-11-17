package com.artbeatte.exercises.bst;

/**
 * @author art.beatte
 * @version 11/17/15
 */
public class BstTestCase {

    /**
     *     0
     *    / \
     *   0  0
     *  /  / \
     * 0  0   0
     *   /   /
     *  0   0
     */
    public static Bst<Integer> LARGE = new Bst<>();
    static {
        LARGE.add(3);
        LARGE.add(6);
        LARGE.add(8);
        LARGE.add(7);
        LARGE.add(5);
        LARGE.add(4);
        LARGE.add(2);
        LARGE.add(1);
    }

    /**
     *   0
     *  / \
     * 0   0
     */
    public static Bst<Integer> SMALL = new Bst<>();
    static {
        SMALL.add(2);
        SMALL.add(1);
        SMALL.add(3);
    }

    public static Bst<Integer> EMPTY = new Bst<>();

    /**
     *  0
     */
    public static Bst<Integer> SINGLE = new Bst<>();
    static {
        SINGLE.add(14);
    }

    /**
     *      0
     *    /  \
     *   0    0
     *  /    / \
     * 0    0   0
     *      /   /
     *     0   0
     *    / \   \
     *   0  0   0
     *  /    \
     * 0     0
     *       \
     *        0
     */
    public static Bst<Integer> X_LARGE = new Bst<>();
    static {
        X_LARGE.add(3);
        X_LARGE.add(2);
        X_LARGE.add(1);

        X_LARGE.add(11);
        X_LARGE.add(10);
        X_LARGE.add(6);
        X_LARGE.add(7);
        X_LARGE.add(8);
        X_LARGE.add(9);
        X_LARGE.add(5);
        X_LARGE.add(4);

        X_LARGE.add(14);
        X_LARGE.add(12);
        X_LARGE.add(13);
    }

    /**
     *   0
     *  / \
     * 0  0
     */
    public static Bst<String> STRING = new Bst<>();
    static {
        STRING.add("Beta");
        STRING.add("Alpha");
        STRING.add("Gamma");
    }
}
