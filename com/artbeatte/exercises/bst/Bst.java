package com.artbeatte.exercises.bst;

import java.util.StringTokenizer;

/**
 * @author art.beatte
 * @version 10/18/15
 */
public class Bst<T extends Comparable<T>> {

    private Node<T> mRoot;

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
            mRoot = new Node<>(value);
        } else {
            mRoot =  add(mRoot, value);
        }
        return ret;
    }

    private Node<T> add(Node<T> root, T value) {
        int comp = root.getData().compareTo(value);
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

    public T remove(T value) {
        if (value == null || mRoot == null) return null;
        Node<T> found = find(mRoot, value);
        mRoot = remove(mRoot, value);
        return found == null ? null : found.getData();
    }

    private Node<T> remove(Node<T> root, T value) {
        int comp = root.getData().compareTo(value);
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

    public boolean contains(T value) {
        return find(mRoot, value) != null;
    }

    private Node<T> find(Node<T> root, T value) {
        if (root == null) return null;
        int comp = root.getData().compareTo(value);
        if (comp < 0) {
            return find(root.getLeftNode(), value);
        } else if (comp > 0) {
            return find(root.getRightNode(), value);
        } else {
            return root;
        }
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
        sb.append(root.getData()).append(" ");
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
