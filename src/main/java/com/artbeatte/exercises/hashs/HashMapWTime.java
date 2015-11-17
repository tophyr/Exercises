package com.artbeatte.exercises.hashs;

import com.artbeatte.exercises.testing.ExternalTestCase;
import com.artbeatte.exercises.testing.MethodTestCase;
import com.artbeatte.exercises.testing.SystemTestRunner;
import com.artbeatte.exercises.testing.TestRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author art.beatte
 * @version 10/18/15
 */
public class HashMapWTime<K, V> {

    private HashMap<K, TreeMap<Float, V>> mBuckets;

    public HashMapWTime() {
        mBuckets = new HashMap<>();
    }

    public V put(K key, float time, V value) {
        TreeMap<Float, V> values = mBuckets.get(key);
        if (values == null) {
            values = new TreeMap<>();
            mBuckets.put(key, values);
        }
        return values.put(time, value);
    }

    /**
     * Returns the value associated with time. If time is not present returns value associated with the next largest
     * value of time.
     * @return the value for time or times next largest present value
     */
    public V get(K key, float time) {
        TreeMap<Float, V> values = mBuckets.get(key);
        if (values == null) return null;
        Float floorTime = values.floorKey(time);
        return floorTime == null ? null : values.get(floorTime);
    }

    public V remove(K key, float time) {
        TreeMap<Float, V> values = mBuckets.get(key);
        return values == null ? null : values.remove(time);
    }

    public boolean isEmpty() {
        if (mBuckets.isEmpty()) return true;
        boolean isEmpty = true;
        Iterator<K> itr = mBuckets.keySet().iterator();
        while (itr.hasNext() && isEmpty) {
            isEmpty = mBuckets.get(itr.next()).isEmpty();
        }
        return isEmpty;
    }

    public static void main(String[] args) {
        TestRunner testRunner = new SystemTestRunner();
        for (final HashMapWTime<Integer, String> test : TESTS) {
            testRunner.addTestCase(new ExternalTestCase("get", new ExternalTestCase.ExternalTest() {
                @Override
                public boolean execute(OutputStream outputStream) throws IOException {
                    return test.get(3, 200).contentEquals("Two");
                }
            }));
            testRunner.addTestCase(new MethodTestCase<>(test, "isEmpty", false));
            testRunner.addTestCase(new ExternalTestCase("remove", new ExternalTestCase.ExternalTest() {
                @Override
                public boolean execute(OutputStream outputStream) throws IOException {
                    outputStream.write("\nEmptying TimeMap\n".getBytes());
                    test.remove(3, 300);
                    outputStream.write(".".getBytes());
                    test.remove(3, 100);
                    outputStream.write(".".getBytes());
                    test.remove(3, 200);
                    outputStream.write(".".getBytes());
                    test.remove(1, 300);
                    outputStream.write(".".getBytes());
                    test.remove(1, 100);
                    outputStream.write(".".getBytes());
                    test.remove(1, 200);
                    outputStream.write(".".getBytes());
                    // test removing non-present value
                    test.remove(2, 320);
                    test.remove(2, 300);
                    outputStream.write(".".getBytes());
                    test.remove(2, 100);
                    outputStream.write(".".getBytes());
                    test.remove(2, 200);
                    outputStream.write(".\n\n".getBytes());

                    return test.isEmpty();
                }
            }));
        }
        testRunner.runTests();
    }

    private static final HashMapWTime<Integer, String> TIME_MAP = new HashMapWTime<>();
    static {
        TIME_MAP.put(3, 300, "Three");
        TIME_MAP.put(3, 100, "One");
        TIME_MAP.put(3, 200, "Two");
        TIME_MAP.put(1, 300, "Three");
        TIME_MAP.put(1, 100, "One");
        TIME_MAP.put(1, 200, "Two");
        TIME_MAP.put(2, 300, "Three");
        TIME_MAP.put(2, 100, "One");
        TIME_MAP.put(2, 200, "Two");
    }

    private static final List<HashMapWTime<Integer, String>> TESTS = new ArrayList<>();
    static {
        TESTS.add(TIME_MAP);
    }
}
