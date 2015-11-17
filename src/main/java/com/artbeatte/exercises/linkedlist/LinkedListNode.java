package com.artbeatte.exercises.linkedlist;

import com.artbeatte.exercises.testing.ExternalTestCase;
import com.artbeatte.exercises.testing.SystemTestRunner;
import com.artbeatte.exercises.testing.TestRunner;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author sarbs
 * @version 11/17/15
 */
public class LinkedListNode {
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

    /**
     * Detects if there are any node in a linked list points back to a node in the same list, thus creating a loop.
     *
     * @param start The start of the list
     * @return True if the list contains a loop, false if the list terminates normally
     */
    public static boolean detectLoop(LinkedListNode start) {
        throw new NotImplementedException();
    }

    public static void main(String args[]) {
        TestRunner tr = new SystemTestRunner();
        tr.addTestCase(new ExternalTestCase("shortStraight", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                LinkedListNode root = new LinkedListNode();
                LinkedListNode node = root;
                for (int i = 0; i < 50; i++) {
                    LinkedListNode newNode = new LinkedListNode();
                    node.setNext(newNode);
                    node = newNode;
                }

                return !LinkedListNode.detectLoop(root);
            }
        }));

        tr.addTestCase(new ExternalTestCase("longStraight", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
                LinkedListNode root = new LinkedListNode();
                LinkedListNode node = root;
                for (int i = 0; i < 500000; i++) {
                    LinkedListNode newNode = new LinkedListNode();
                    node.setNext(newNode);
                    node = newNode;
                }

                return !LinkedListNode.detectLoop(root);
            }
        }));

        tr.addTestCase(new ExternalTestCase("smallLoop", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
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

                return LinkedListNode.detectLoop(root);
            }
        }));

        tr.addTestCase(new ExternalTestCase("smallLoop2", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
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

                return LinkedListNode.detectLoop(root);
            }
        }));

        tr.addTestCase(new ExternalTestCase("smallLoopFar", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
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

                return LinkedListNode.detectLoop(root);
            }
        }));

        tr.addTestCase(new ExternalTestCase("bigLoop", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream outputStream) throws IOException {
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

                return LinkedListNode.detectLoop(root);
            }
        }));

        tr.runTests();
    }
}
