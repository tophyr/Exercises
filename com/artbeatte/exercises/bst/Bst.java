package com.artbeatte.exercises.bst;

import com.artbeatte.exercises.testing.*;
import com.artbeatte.exercises.testing.ExternalMethodTestCase;
import com.artbeatte.exercises.testing.MethodTestCase;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author art.beatte
 * @version 10/18/15
 * @version 11/14/15
 */
public class Bst<T extends Comparable<T>> {

    private Node<T> mRoot;

    // region init
    public Bst() {
        mRoot = null;
    }

    public Bst(String data) {
        this();
        if (data != null && data.length() > 0) {
            StringTokenizer st = new StringTokenizer(data, " ");
            mRoot = deserialize(st);
        }
    }
    // endregion

    // region add
    public boolean add(T value) {
        boolean success = true;
        if (value == null) {
            success = false;
        } else if (mRoot == null) {
            mRoot = new Node<>(value);
        } else {
            mRoot =  add(mRoot, value);
        }
        return success;
    }

    private Node<T> add(Node<T> root, T value) {
        int comp = root.getValue().compareTo(value);
        if (comp < 0) {
            if (root.getLeftNode() == null) {
                root.setLeftNode(value);
            } else {
                root.setLeftNode(add(root.getLeftNode(), value));
            }
        } if (comp > 0) {
            if (root.getRightNode() == null) {
                root.setRightNode(value);
            } else {
                root.setRightNode(add(root.getRightNode(), value));
            }
        }
        return root;
    }
    // endregion

    // region remove
    public T remove(T value) {
        if (value == null || mRoot == null) return null;
        Node<T> found = find(mRoot, value);
        mRoot = remove(mRoot, value);
        return found == null ? null : found.getValue();
    }

    private Node<T> remove(Node<T> root, T value) {
        int comp = root.getValue().compareTo(value);
        if (comp < 0) {
            root.setLeftNode(remove(root.getLeftNode(), value));
        } else if (comp > 0) {
            root.setRightNode(remove(root.getRightNode(), value));
        } else {
            root = handleRemoval(root);
        }
        return root;
    }

    private Node<T> handleRemoval(Node<T> root) {
        if (root.getLeftNode() == null && root.getRightNode() == null) {
            root = null;
        } else if (root.getLeftNode() == null) {
            root = root.getRightNode();
        } else if(root.getRightNode() == null) {
            root = root.getLeftNode();
        } else {
            Node<T> rightsLeftMost = root.getRightNode();
            while (rightsLeftMost.getLeftNode() != null) {
                rightsLeftMost = rightsLeftMost.getLeftNode();
            }
            rightsLeftMost.setLeftNode(root.getRightNode());
            root = rightsLeftMost;
        }
        return root;
    }
    // endregion

    // region search
    public boolean contains(T value) {
        return find(mRoot, value) != null;
    }

    private Node<T> find(Node<T> root, T value) {
        if (root == null) return null;
        int comp = root.getValue().compareTo(value);
        if (comp < 0) {
            return find(root.getLeftNode(), value);
        } else if (comp > 0) {
            return find(root.getRightNode(), value);
        } else {
            return root;
        }
    }
    // endregion

    // region size
    public boolean isEmpty() {
        return mRoot == null;
    }

    public int size() {
        return count(mRoot);
    }

    private int count(Node root) {
        if (root == null) return 0;
        int leftCount = count(root.getLeftNode());
        int rightCount = count(root.getRightNode());
        return leftCount + rightCount + 1;
    }
    // endregion

    // region serialization
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        serialize(mRoot, sb);
        return sb.toString();
    }

    private void serialize(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
            return;
        }
        sb.append(root.getValue()).append(" ");
        serialize(root.getLeftNode(), sb);
        serialize(root.getRightNode(), sb);
    }

    private Node<T> deserialize(StringTokenizer st) {
        if (!st.hasMoreElements()) return null;
        String value = st.nextToken();
        if (value.equals("#")) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Node<T> root = new Node<>((T) value);
        root.setLeftNode(deserialize(st));
        root.setRightNode(deserialize(st));
        return root;
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
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
    public int getEdgeWidth() {
        int[] xy=  new int[2];
        getEdgeWidth(mRoot, 0, xy);
        return Math.abs(xy[1] - xy[0]);
    }

    /**
     * Calculates the edge width
     * @param root the root {@link Node}
     * @param center the distance from the root
     * @param xy a {@code int[2]} containing the left and right most values.
     */
    private void getEdgeWidth(Node root, int center, int[] xy) {
        if (root == null) return;
        if (center < xy[0]) xy[0] = center;
        if (center > xy[1]) xy[1] = center;
        getEdgeWidth(root.getLeftNode(), center - 1, xy);
        getEdgeWidth(root.getRightNode(), center + 1, xy);
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
    @SuppressWarnings("unused")
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
        final Bst<Integer> bst = new Bst<>();
        final boolean[] sizeSuccess = {false};

        TestRunner testRunner = new SystemTestRunner();
        // alternately:      `= new FileTestRunner("com/artbeatte/exercises/testing/testFile.txt");`
        testRunner.addTestCase(new ExternalMethodTestCase("add", new ExternalMethodTestCase.ExternalTest() {
            @Override
            public boolean execute() {
                // setup
                boolean addSuccess;
                System.out.println("Initial state: " + bst.serialize());

                System.out.print("Populating");
                sizeSuccess[0] = bst.size() == 0;
                bst.add(5);
                System.out.print(".");
                sizeSuccess[0] = sizeSuccess[0] && bst.size() == 1;
                addSuccess = bst.contains(5);
                bst.add(34);
                System.out.print(".");
                bst.add(10);
                System.out.print(".");
                sizeSuccess[0] = sizeSuccess[0] && bst.size() == 3;
                addSuccess = addSuccess && bst.contains(34);
                addSuccess = addSuccess && bst.contains(10);
                bst.add(2);
                System.out.print(".");
                bst.add(200);
                System.out.print(".");
                bst.add(44);
                System.out.print(".");
                System.out.println();
                sizeSuccess[0] = sizeSuccess[0] && bst.size() == 6;
                addSuccess = addSuccess && bst.contains(2);
                addSuccess = addSuccess && bst.contains(200);
                addSuccess = addSuccess && bst.contains(44);
                System.out.println();

                return addSuccess;
            }
        }));
        testRunner.addTestCase(new ExternalMethodTestCase("size", new ExternalMethodTestCase.ExternalTest() {
            @Override
            public boolean execute() {
                return sizeSuccess[0];
            }
        }));
        testRunner.addTestCase(new ExternalMethodTestCase("serialize", new ExternalMethodTestCase.ExternalTest() {
            @Override
            public boolean execute() {
                System.out.println();
                System.out.println("State: " + bst.serialize());
                boolean success = bst.serialize().contentEquals(new Bst<Integer>(bst.serialize()).serialize());
                System.out.println("State: " + bst.serialize());
                System.out.println();

                return success;
            }
        }));
//        testRunner.addTestCase(new MethodTestCase<>(bst, "serialize", new Bst<Integer>(bst.serialize()).serialize()));
        testRunner.addTestCase(new ExternalMethodTestCase("remove", new ExternalMethodTestCase.ExternalTest() {
            @Override
            public boolean execute() {
                // teardown
                System.out.println();
                System.out.print("Resetting");
                bst.remove(10);
                System.out.print(".");
                bst.remove(44);
                System.out.print(".");
                bst.remove(200);
                System.out.print(".");
                bst.remove(34);
                System.out.print(".");
                bst.remove(2);
                System.out.print(".");
                bst.remove(5);
                System.out.println();
                System.out.println();
                System.out.println("State: " + bst.serialize());
                System.out.println();

                return bst.isEmpty();
            }
        }));
        testRunner.addTestCase(new MethodTestCase<>(LARGE, "getDepth", 4));
        testRunner.addTestCase(new MethodTestCase<>(LARGE, "getEdgeWidth", 4));
        testRunner.addTestCase(new MethodTestCase<>(LARGE, "getMaxWidth", 3));

        testRunner.addTestCase(new MethodTestCase<>(EMPTY, "getDepth", 0));
        testRunner.addTestCase(new MethodTestCase<>(EMPTY, "getEdgeWidth", 0));
        testRunner.addTestCase(new MethodTestCase<>(EMPTY, "getMaxWidth", 0));

        testRunner.addTestCase(new MethodTestCase<>(SMALL, "getDepth", 2));
        testRunner.addTestCase(new MethodTestCase<>(SMALL, "getEdgeWidth", 2));
        testRunner.addTestCase(new MethodTestCase<>(SMALL, "getMaxWidth", 2));

        testRunner.addTestCase(new MethodTestCase<>(SINGLE, "getDepth", 1));
        testRunner.addTestCase(new MethodTestCase<>(SINGLE, "getEdgeWidth", 0));
        testRunner.addTestCase(new MethodTestCase<>(SINGLE, "getMaxWidth", 1));

        testRunner.addTestCase(new MethodTestCase<>(X_LARGE, "getDepth", 7));
        testRunner.addTestCase(new MethodTestCase<>(X_LARGE, "getEdgeWidth", 5));
        testRunner.addTestCase(new MethodTestCase<>(X_LARGE, "getMaxWidth", 3));

        testRunner.addTestCase(new MethodTestCase<>(STRING, "getDepth", 2));
        testRunner.addTestCase(new MethodTestCase<>(STRING, "getEdgeWidth", 2));
        testRunner.addTestCase(new MethodTestCase<>(STRING, "getMaxWidth", 2));
        testRunner.runTests();
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
    private static Bst<Integer> LARGE = new Bst<>();
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
    private static Bst<Integer> SMALL = new Bst<>();
    static {
        SMALL.add(2);
        SMALL.add(1);
        SMALL.add(3);
    }

    private static Bst<Integer> EMPTY = new Bst<>();

    /**
     *  0
     */
    private static Bst<Integer> SINGLE = new Bst<>();
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
    private static Bst<Integer> X_LARGE = new Bst<>();
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
    private static Bst<String> STRING = new Bst<>();
    static {
        STRING.add("Beta");
        STRING.add("Alpha");
        STRING.add("Gamma");
    }
}
