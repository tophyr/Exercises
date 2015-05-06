package com.artbeatte.exercise;

/**
 * @author art.beatte
 * @version 5/6/15.
 */
public class Bst<T extends Comparable<T>> {
    
    class Node {
        Node left, right;
        T value;
        
        Node(T value) {
            this.value = value;
        }
    }
    
    private Node root;
    
    
    public boolean add(T n) {
        Node newRoot = add(root, n);
        if (newRoot == null) {
            return false;
        }
        root = newRoot;
        return true;
    }
    
    // precondition: dupes will be replaced. 
    // precondition: other cannot be null
    private Node add(Node root, T other) {
        // non existence
        if (root == null) {
            return new Node(other);
        }
        
        if (other.compareTo(root.value) < 0) {
            root.left = add(root.left, other);
        } else {
            root.right = add(root.right, other);
        }
        // dupe values just return
        return root;
    }
    
    
    public boolean remove(T n) {
        Node newRoot = remove(root, n);
        if (newRoot == null) {
            return false;
        }
        root = newRoot;
        return true;
    }
    
    private Node remove(Node root, T other) {
        // non existence
        if (root == null) {
            return null;
        }
        
        if (other.compareTo(root.value) == 0) {
            Node left = root.left;
            Node right = root.right;
            if (left == null && right == null) {
                return null;
            } else if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            } else {
                Node rightsLeftMost = right;
                // get right's left most node
                while (rightsLeftMost.left != null) {
                    rightsLeftMost = rightsLeftMost.left;
                }
                rightsLeftMost.left = left;
                return right;
            }
        }
        
        if (other.compareTo(root.value) < 0) {
            root.left = remove(root.left, other);
        } else {
            root.right = remove(root.right, other);
        }
        return root;
    }
    
    
    public int size() {
        return count(root);
    }
    
    private int count(Node root) {
        if (root == null) {
            return 0;
        }

        int count = count(root.left);
        count += count(root.right);
        return ++count;
    }
    
    
    public boolean contains(T value) {
        return contains(root, value);
    }
    
    private boolean contains(Node root, T value) {
        if (root == null) {
            return false;
        }
        
        if (root.value.compareTo(value) < 0) {
            return contains(root.left, value);
        } else if (root.value.compareTo(value) > 0) {
            return contains(root.right, value);
        } else {
            return true;
        }
    }
}
