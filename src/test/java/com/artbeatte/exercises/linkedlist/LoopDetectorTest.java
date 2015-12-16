package com.artbeatte.exercises.linkedlist;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sarbs on 12/16/15.
 */
public class LoopDetectorTest {

    public static final LoopDetector DETECTOR = new SampleLoopDetector();

    @Test
    public void testShortStraight() throws Exception {
        LinkedListNode root = new LinkedListNode();
        LinkedListNode node = root;
        for (int i = 0; i < 50; i++) {
            LinkedListNode newNode = new LinkedListNode();
            node.setNext(newNode);
            node = newNode;
        }

        assertFalse(DETECTOR.detectLoop(root));
    }

    @Test
    public void testLongStraight() throws Exception {
        LinkedListNode root = new LinkedListNode();
        LinkedListNode node = root;
        for (int i = 0; i < 500000; i++) {
            LinkedListNode newNode = new LinkedListNode();
            node.setNext(newNode);
            node = newNode;
        }

        assertFalse(DETECTOR.detectLoop(root));
    }

    @Test
    public void testSmallLoop() throws Exception {
        LinkedListNode root = new LinkedListNode();
        LinkedListNode node = root;
        LinkedListNode reentry = null;
        for (int i = 0; i < 50; i++) {
            LinkedListNode newNode = new LinkedListNode();
            node.setNext(newNode);
            node = newNode;
            if (i == 45)
                reentry = node;
        }
        node.setNext(reentry);

        assertTrue(DETECTOR.detectLoop(root));
    }

    @Test
    public void testSmallLoop2() throws Exception {
        LinkedListNode root = new LinkedListNode();
        LinkedListNode node = root;
        LinkedListNode reentry = null;
        for (int i = 0; i < 50; i++) {
            LinkedListNode newNode = new LinkedListNode();
            node.setNext(newNode);
            node = newNode;
            if (i == 44)
                reentry = node;
        }
        node.setNext(reentry);

        assertTrue(DETECTOR.detectLoop(root));
    }

    @Test
    public void testSmallLoopFar() throws Exception {
        LinkedListNode root = new LinkedListNode();
        LinkedListNode node = root;
        LinkedListNode reentry = null;
        for (int i = 0; i < 50000; i++) {
            LinkedListNode newNode = new LinkedListNode();
            node.setNext(newNode);
            node = newNode;
            if (i == 49995)
                reentry = node;
        }
        node.setNext(reentry);

        assertTrue(DETECTOR.detectLoop(root));
    }

    @Test
    public void testBigLoop() throws Exception {
        LinkedListNode root = new LinkedListNode();
        LinkedListNode node = root;
        LinkedListNode reentry = null;
        for (int i = 0; i < 500000; i++) {
            LinkedListNode newNode = new LinkedListNode();
            node.setNext(newNode);
            node = newNode;
            if (i == 45)
                reentry = node;
        }
        node.setNext(reentry);

        assertTrue(DETECTOR.detectLoop(root));
    }
}
