package com.artbeatte.exercises.bst;

/**
 * @author art.beatte
 * @version 11/13/15
 */
class Node<T extends Comparable<T>> {

    private T mData;
    private Node<T> mLeft;
    private Node<T> mRight;

    public Node(T data) {
        this.mData = data;
    }

    public T getData() {
        return mData;
    }

    public Node<T> getLeftNode() {
        return mLeft;
    }

    public void setLeftNode(T left) {
        this.mLeft = new Node<>(left);
    }

    void setLeftNode(Node<T> left) {
        this.mLeft = left;
    }

    public Node<T> getRightNode() {
        return mRight;
    }

    public void setRightNode(T right) {
        this.mRight = new Node<>(right);
    }

    void setRightNode(Node<T> right) {
        this.mRight = right;
    }
}
