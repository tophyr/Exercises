package com.artbeatte.exercises.testrunner.tests;

import com.artbeatte.exercises.strings.RunLengthEncoding;
import com.artbeatte.exercises.testrunner.MethodParameterTestCase;
import com.artbeatte.exercises.testrunner.MethodTestCase;
import com.artbeatte.exercises.testrunner.SystemTestRunner;
import com.artbeatte.exercises.testrunner.TestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author art.beatte
 * @version 11/17/15
 */
public class RunLengthEncodingTest {

    public static void main(String[] args) {
        TestRunner testRunner = new SystemTestRunner();
        for (String test : TESTS.keySet()) {
            RunLengthEncoding rle = new RunLengthEncoding(test);
            testRunner.addTestCase(new MethodTestCase<>(rle, "encode", TESTS.get(test)));
            testRunner.addTestCase(
                    new MethodParameterTestCase<>(rle, "decode", String.class, rle.encode(), test));
        }
        testRunner.runTests();
    }

    private static final String EMPTY = "";
    private static final String ONE = "j";
    private static final String SHORT = "jweek";
    private static final String LONG = "jjwwwwweekk";
    private static final String NUMBERS = "12443";
    private static final String MIXED = "55jaccccccc34244k";
    private static final String TEST = "aabcccd1111111111d";
    private static final Map<String, String> TESTS = new HashMap<>();
    static {
        TESTS.put(EMPTY, "");
        TESTS.put(ONE, "j");
        TESTS.put(SHORT, "jwe2k");
        TESTS.put(LONG, "j2w5e2k2");
        TESTS.put(NUMBERS, "BCE2D");
        TESTS.put(MIXED, "F2jac7DECE2k");
        TESTS.put(TEST, "a2bc3dB10d");
    }
}
