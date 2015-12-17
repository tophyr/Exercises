package com.artbeatte.exercises.runlengthencoding;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author art.beatte
 * @version 11/17/15
 */
public class RunLengthEncodingTest {

    public static final String EMPTY = "";
    public static final String ONE = "j";
    public static final String SHORT = "jweek";
    public static final String LONG = "jjwwwwweekk";
    public static final String NUMBERS = "12443";
    public static final String MIXED = "55jaccccccc34244k";
    public static final String TEST = "aabcccd1111111111d";

    public static final Map<String, String> TESTS = new HashMap<>();

    static {
        TESTS.put(EMPTY, "");
        TESTS.put(ONE, "j");
        TESTS.put(SHORT, "jwe2k");
        TESTS.put(LONG, "j2w5e2k2");
        TESTS.put(NUMBERS, "BCE2D");
        TESTS.put(MIXED, "F2jac7DECE2k");
        TESTS.put(TEST, "a2bc3dB10d");
    }

    public static final RunLengthEncoding RLE = new SampleRLE();

    @Test
    public void testEncode() {
        for (String test : TESTS.keySet()) {
            assertEquals("Encoding is wrong", TESTS.get(test), RLE.encode(test));
        }
    }

    @Test
    public void testDecode() {
        for (String test : TESTS.keySet()) {
            assertEquals("Decoding is wrong", test, RLE.decode(RLE.encode(test)));
        }
    }
}
