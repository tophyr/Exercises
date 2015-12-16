package com.artbeatte.exercises.linkedlist;

/**
 * Created by sarbs on 12/16/15.
 */
public class SampleLoopDetector extends LoopDetector {
    @Override
    public boolean detectLoop(LinkedListNode start) {
        LinkedListNode one = start, two = start;

        while (two != null) {
            two = two.getNext();
            if (two == null)
                return false;

            two = two.getNext();

            one = one.getNext();

            if (one == two)
                return true;
        }

        return false;
    }
}
