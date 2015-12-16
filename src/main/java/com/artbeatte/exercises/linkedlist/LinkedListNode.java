package com.artbeatte.exercises.linkedlist;

/**
 * @author sarbs
 * @version 11/17/15
 */
public final class LinkedListNode {
    private static int traversals;
    private static void incTraversals() {
        if (++traversals > (2 << 24))
            throw new RuntimeException("fail");
    }

    private LinkedListNode next;

    public LinkedListNode getNext() {
        incTraversals();
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }
}
