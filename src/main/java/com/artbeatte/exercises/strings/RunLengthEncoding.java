package com.artbeatte.exercises.strings;

import com.artbeatte.exercises.testrunner.MethodParameterTestCase;
import com.artbeatte.exercises.testrunner.MethodTestCase;
import com.artbeatte.exercises.testrunner.SystemTestRunner;
import com.artbeatte.exercises.testrunner.TestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author art.beatte
 * @version 10/22/15
 */
public class RunLengthEncoding {

    private String mValue;

    /**
     * @param value must only contain `a-z` and/or `0-9` as characters
     */
    public RunLengthEncoding(String value) {
        mValue = value;
    }

    /**
     * Encodes the given {@link String} using the following rules:
     * 1. Encoded {@link String} must not be longer than the given {@link String}.
     * 2. Encoded {@link String} will condense runs ('aaa' -> 'a3').
     * 3. Encoded {@link String} will convert all `0-9` characters into `A-J`.
     * @return the encoded {@link String}
     */
    public String encode() {
        if (mValue == null || mValue.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        char[] chars = mValue.toCharArray();
        for (int i = 0; i < chars.length; i ++) {
            char c = chars[i];
            int times = 1;
            while (i + 1 < chars.length && chars[i + 1] == c) {
                times++;
                i++;
            }
            if (c >= '0' && c <= '9') {
                int number = Integer.parseInt(String.valueOf(c));
                c = (char) ('A' + number);
            }
            sb.append(c);
            if (times > 1) sb.append(times);
        }
        return sb.toString();
    }

    @SuppressWarnings("unused | InfiniteLoopStatement")
    public String decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        char[] chars = encoded.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'A' && c <= 'J') {
                c = (char) (c - 'A' + '0');
            }
            int times = 1;
            String timesString = "";
            if (i + 1 < chars.length) {
                try {
                    int num = Integer.parseInt(String.valueOf(chars[i + 1]));
                    while (true) {
                        timesString += num;
                        i++;
                        if (i + 1 < chars.length) {
                            num = Integer.parseInt(String.valueOf(chars[i + 1]));
                        } else {
                            throw new NumberFormatException();
                        }
                    }
                } catch (NumberFormatException nfe) {
                    if (!timesString.isEmpty()) {
                        times = Integer.parseInt(timesString);
                    }
                }
            }
            for (int j = 0; j < times; j++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

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
