package com.artbeatte.exercises.bst;

import java.util.StringTokenizer;

/**
 * @author art.beatte
 * @version 10/18/15
 */
public class Bst<T extends Comparable<T>> {

    private class Node {
        T data;
        Node left;
        Node right;

        Node() {
            data = null;
            left = null;
            right = null;
        }

        Node(T data) {
            this();
            this.data = data;
        }
    }

    private Node mRoot;

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

    public boolean add(T value) {
        boolean ret = true;
        if (value == null) {
            ret = false;
        } else if (mRoot == null) {
            mRoot = new Node(value);
        } else {
            mRoot =  add(mRoot, value);
        }
        return ret;
    }

    private Node add(Node root, T value) {
        int comp = root.data.compareTo(value);
        if (comp < 0) {
            if (root.left == null) {
                root.left = new Node(value);
            } else {
                root.left =  add(root.left, value);
            }
        } if (comp > 0) {
            if (root.right == null) {
                root.right = new Node(value);
            } else {
                root.right = add(root.right, value);
            }
        }
        return root;
    }

    public T remove(T value) {
        if (value == null || mRoot == null) return null;
        Node found = find(mRoot, value);
        mRoot = remove(mRoot, value);
        return found == null ? null : found.data;
    }

    private Node remove(Node root, T value) {
        int comp = root.data.compareTo(value);
        if (comp < 0) {
            root.left = remove(root.left, value);
        } else if (comp > 0) {
            root.right = remove(root.right, value);
        } else {
            root = handleRemoval(root);
        }
        return root;
    }

    private Node handleRemoval(Node root) {
        if (root.left == null && root.right == null) {
            root = null;
        } else if (root.left == null) {
            root = root.right;
        } else if(root.right == null) {
            root = root.left;
        } else {
            Node rightsLeftMost = root.right;
            while (rightsLeftMost.left != null) {
                rightsLeftMost = rightsLeftMost.left;
            }
            rightsLeftMost.left = root.right;
            root = rightsLeftMost;
        }
        return root;
    }

    public boolean contains(T value) {
        return find(mRoot, value) != null;
    }

    private Node find(Node root, T value) {
        if (root == null) return null;
        int comp = root.data.compareTo(value);
        if (comp < 0) {
            return find(root.left, value);
        } else if (comp > 0) {
            return find(root.right, value);
        } else {
            return root;
        }
    }

    public int size() {
        return count(mRoot);
    }

    private int count(Node root) {
        if (root == null) return 0;
        int leftCount = count(root.left);
        int rightCount = count(root.right);
        return leftCount + rightCount + 1;
    }

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
        sb.append(root.data).append(" ");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    private Node deserialize(StringTokenizer st) {
        if (!st.hasMoreElements()) return null;
        String value = st.nextToken();
        if (value.equals("#")) {
            return null;
        }
        Node root = new Node((T) value);
        root.left = deserialize(st);
        root.right = deserialize(st);
        return root;
    }

    public boolean isEmpty() {
        return mRoot == null;
    }

    public static void main(String[] args) {
        // print header
        System.out.println();
        System.out.println("====================");
        System.out.println("Let The Games Begin!");
        System.out.println("====================");
        System.out.println();

        Bst<Integer> bst = new Bst<>();
        // setup
        boolean addSuccess;
        boolean sizeSuccess;
        System.out.println("Initial state: " + bst.serialize());

        System.out.print("Populating");
        sizeSuccess = bst.size() == 0;
        bst.add(5);
        System.out.print(".");
        sizeSuccess = sizeSuccess && bst.size() == 1;
        addSuccess = bst.contains(5);
        bst.add(34);
        System.out.print(".");
        bst.add(10);
        System.out.print(".");
        sizeSuccess = sizeSuccess && bst.size() == 3;
        addSuccess = addSuccess && bst.contains(34);
        addSuccess = addSuccess && bst.contains(10);
        bst.add(2);
        System.out.print(".");
        bst.add(200);
        System.out.print(".");
        bst.add(44);
        System.out.print(".");
        System.out.println();
        sizeSuccess = sizeSuccess && bst.size() == 6;
        addSuccess = addSuccess && bst.contains(2);
        addSuccess = addSuccess && bst.contains(200);
        addSuccess = addSuccess && bst.contains(44);

        // test
        System.out.println("Add: *** TEST " + (addSuccess ? "PASSES" : "FAILS") + " ***");
        System.out.println("Size: *** TEST " + (sizeSuccess ? "PASSES" : "FAILS") + " ***");

        System.out.println("State: " + bst.serialize());
        addSuccess = bst.serialize().contentEquals(new Bst<Integer>(bst.serialize()).serialize());
        System.out.println("Serialization: *** TEST " + (addSuccess ? "PASSES" : "FAILS") + " ***");
        System.out.println("State: " + bst.serialize());

        // teardown
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

        System.out.println("Remove: *** TEST " + (bst.isEmpty() ? "PASSES" : "FAILS") + " ***");
        System.out.println("State: " + bst.serialize());

        // print footer
        System.out.println();
        System.out.println("====================");
        System.out.println("        Fin");
        System.out.println("====================");
        System.out.println();
    }

}
