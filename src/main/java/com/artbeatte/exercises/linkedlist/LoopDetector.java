package com.artbeatte.exercises.linkedlist;

/**
 * Created by sarbs on 12/16/15.
 */
public abstract class LoopDetector {
    /**
     * Detects if there are any node in a linked list points back to a node in the same list, thus creating a loop.
     *
     * @param start The start of the list
     * @return True if the list contains a loop, false if the list terminates normally
     */
    public abstract boolean detectLoop(LinkedListNode start);
}
