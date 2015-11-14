package com.artbeatte.exercises.tree_manipulation;

import com.artbeatte.exercises.bst.Node;

import java.awt.*;
import java.util.HashMap;

/**
 * @author art.beatte
 * @version 11/11/15
 */
public class BST<T extends Comparable<T>> {

    private Node<T> mRoot;

    /* constructor */
    public BST() { }

    // region add
    public void add(T value) {
        if (mRoot == null) {
            mRoot = new Node<>(value);
        } else {
            add(mRoot, value);
        }
    }

    private void add(Node<T> root, T value) {
        if (value.compareTo(root.getValue()) < 0) {
            if (root.getLeftNode() == null) {
                root.setLeftNode(value);
            } else {
                add(root.getLeftNode(), value);
            }
        } else {
            if (root.getRightNode() == null) {
                root.setRightNode(value);
            } else {
                add(root.getRightNode(), value);
            }
        }
    }
    // endregion

    // region depth
    /**
     * Depth is the deepest level reached by the tree
     *   0
     *  / \
     * 0   0
     *    / \
     *   0   0
     * The preceding tree has a depth of 3.
     * @return the depth
     */
    public int getDepth() {
        return getDepth(mRoot);
    }

    private int getDepth(Node root) {
        if (root == null) return 0;
        int lDepth = getDepth(root.getLeftNode());
        int rDepth = getDepth(root.getRightNode());
        return 1 + Math.max(lDepth , rDepth);
    }
    // endregion

    // region width
    /**
     * Width is defined as the number of edges between the left most and right most nodes.
     *   0
     *  / \
     * 0   0
     *    / \
     *   0   0
     *  /
     * 0
     * The proceeding tree has a width of 3.
     *  @return the width
     */
    public int getEdgeWidth() {
        Point vals = new Point();
        getEdgeWidth(mRoot, 0, vals);
        return Math.abs(vals.y - vals.x);
    }

    // returns the depth of stuff
    private void getEdgeWidth(Node root, int center, Point point) {
        if (root == null) return;
        if (center < point.x) point.x = center;
        if (center > point.y) point.y = center;
        getEdgeWidth(root.getLeftNode(), center - 1, point);
        getEdgeWidth(root.getRightNode(), center + 1, point);
    }
    // endregion

    // region max width
    /**
     * Max Width is defined as the most number of nodes of the same depth.
     *     0
     *    / \
     *   0  0
     *  /  / \
     * 0  0   0
     *   /   /
     *  0   0
     * The preceding tree has a max width of 3.
     * @return the max width
     */
    public int getMaxWidth() {
        HashMap<Integer, Integer> depths = new HashMap<>();
        getMaxWidth(mRoot, 0, depths);
        int width = 0;
        for (Integer key : depths.keySet()) {
            if (depths.get(key) > width) width = depths.get(key);
        }
        return width;
    }

    public void getMaxWidth(Node root, int depth, HashMap<Integer, Integer> depths) {
        if (root == null) return;
        Integer recordedDepth = depths.get(depth);
        if (recordedDepth == null) {
            depths.put(depth, 1);
        } else {
            depths.put(depth, ++recordedDepth);
        }
        getMaxWidth(root.getLeftNode(), depth + 1, depths);
        getMaxWidth(root.getRightNode(), depth + 1, depths);
    }
    // endregion

    public static void main(String[] args) {
        BST<Integer> bst = LARGE;
        System.out.println("--- LARGE BST TESTS ---");
        System.out.println("Depth Test:        " + (bst.getDepth() == 4 ? "PASSES" : "FAILS"));
        System.out.println("EdgeWidth Test:    " + (bst.getEdgeWidth() == 4 ? "PASSES" : "FAILS"));
        System.out.println("MaxWidth Test:     " + (bst.getMaxWidth() == 3 ? "PASSES" : "FAILS"));
        System.out.println();

        bst = SMALL;
        System.out.println("--- SMALL BST TESTS ---");
        System.out.println("Depth Test:        " + (bst.getDepth() == 2 ? "PASSES" : "FAILS"));
        System.out.println("EdgeWidth Test:    " + (bst.getEdgeWidth() == 2 ? "PASSES" : "FAILS"));
        System.out.println("MaxWidth Test:     " + (bst.getMaxWidth() == 2 ? "PASSES" : "FAILS"));
        System.out.println();
    }

    /**
     *     0
     *    / \
     *   0  0
     *  /  / \
     * 0  0   0
     *   /   /
     *  0   0
     */
    private static BST<Integer> LARGE = new BST<>();
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
    private static BST<Integer> SMALL = new BST<>();
    static {
        SMALL.add(2);
        SMALL.add(1);
        SMALL.add(3);
    }
}
