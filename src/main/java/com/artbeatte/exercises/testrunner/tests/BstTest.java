package com.artbeatte.exercises.testrunner.tests;

import com.artbeatte.exercises.bst.Bst;
import com.artbeatte.exercises.testrunner.ExternalTestCase;
import com.artbeatte.exercises.testrunner.MethodTestCase;
import com.artbeatte.exercises.testrunner.SystemTestRunner;
import com.artbeatte.exercises.testrunner.TestRunner;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author art.beatte
 * @version 11/17/15
 */
public class BstTest {

    public static void main(String[] args) {
        final Bst<Integer> bst = new Bst<>();
        final boolean[] sizeSuccess = {false};

        TestRunner testRunner = new SystemTestRunner();
        // alternately:      `= new FileTestRunner("testFile.txt");`
        testRunner.addTestCase(new ExternalTestCase("add", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                // setup
                boolean addSuccess;
                os.write(("Initial state: " + bst.serialize() + "\n").getBytes());

                os.write("Populating".getBytes());
                sizeSuccess[0] = bst.size() == 0;
                bst.add(5);
                os.write(".".getBytes());
                sizeSuccess[0] = sizeSuccess[0] && bst.size() == 1;
                addSuccess = bst.contains(5);
                bst.add(34);
                os.write(".".getBytes());
                bst.add(10);
                os.write(".".getBytes());
                sizeSuccess[0] = sizeSuccess[0] && bst.size() == 3;
                addSuccess = addSuccess && bst.contains(34);
                addSuccess = addSuccess && bst.contains(10);
                bst.add(2);
                os.write(".".getBytes());
                bst.add(200);
                os.write(".".getBytes());
                bst.add(44);
                os.write(".".getBytes());
                os.write("\n".getBytes());
                sizeSuccess[0] = sizeSuccess[0] && bst.size() == 6;
                addSuccess = addSuccess && bst.contains(2);
                addSuccess = addSuccess && bst.contains(200);
                addSuccess = addSuccess && bst.contains(44);
                os.write("\n".getBytes());

                return addSuccess;
            }
        }));
        testRunner.addTestCase(new ExternalTestCase("size", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) {
                return sizeSuccess[0];
            }
        }));
        testRunner.addTestCase(new ExternalTestCase("serialize", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                os.write("\n".getBytes());
                os.write(("State: " + bst.serialize() + "\n").getBytes());
                boolean success = bst.serialize().contentEquals(new Bst<Integer>(bst.serialize()).serialize());
                os.write(("State: " + bst.serialize() + "\n").getBytes());
                os.write("\n".getBytes());

                return success;
            }
        }));
//        testRunner.addTestCase(new MethodTestCase<>(bst, "serialize", new Bst<Integer>(bst.serialize()).serialize()));
        testRunner.addTestCase(new ExternalTestCase("remove", new ExternalTestCase.ExternalTest() {
            @Override
            public boolean execute(OutputStream os) throws IOException {
                // teardown
                os.write("\n".getBytes());
                os.write("Resetting".getBytes());
                bst.remove(10);
                os.write(".".getBytes());
                bst.remove(44);
                os.write(".".getBytes());
                bst.remove(200);
                os.write(".".getBytes());
                bst.remove(34);
                os.write(".".getBytes());
                bst.remove(2);
                os.write(".".getBytes());
                bst.remove(5);
                os.write("\n".getBytes());
                os.write("\n".getBytes());
                os.write(("State: " + bst.serialize() + "\n").getBytes());
                os.write("\n".getBytes());

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
