package com.artbeatte.exercises.hashs;

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
        // setup
        HashMapWTime<Integer, String> timeMap = new HashMapWTime<>();
        timeMap.put(3, 300, "Three");
        timeMap.put(3, 100, "One");
        timeMap.put(3, 200, "Two");
        timeMap.put(1, 300, "Three");
        timeMap.put(1, 100, "One");
        timeMap.put(1, 200, "Two");
        timeMap.put(2, 300, "Three");
        timeMap.put(2, 100, "One");
        timeMap.put(2, 200, "Two");
        // exercise
        // print header
        System.out.println("====================");
        System.out.println("Let The Games Begin!");
        System.out.println("====================");
        System.out.println();
        // print results
        System.out.print("Should return 'Two': " + timeMap.get(3, 200));
        System.out.println(" *** TEST " + (timeMap.get(3, 200).contentEquals("Two") ? "PASSES" : "FAILS") + " ***");
        System.out.print("Should return 'Two': " + timeMap.get(3, 220));
        System.out.println(" *** TEST " + (timeMap.get(3, 220).contentEquals("Two") ? "PASSES" : "FAILS") + " ***");
        System.out.print("Should return 'Two': " + timeMap.get(3, 200));
        System.out.println(" *** TEST " + (timeMap.get(3, 200).contentEquals("Two") ? "PASSES" : "FAILS") + " ***");
        // teardown
        System.out.println();
        System.out.println("TimeMap is: " + (timeMap.isEmpty() ? "empty" : "non-empty"));
        System.out.print("Emptying TimeMap");
        timeMap.remove(3, 300);
        System.out.print(".");
        timeMap.remove(3, 100);
        System.out.print(".");
        timeMap.remove(3, 200);
        System.out.print(".");
        timeMap.remove(1, 300);
        System.out.print(".");
        timeMap.remove(1, 100);
        System.out.print(".");
        timeMap.remove(1, 200);
        System.out.print(".");
        // test removing non-present value
        timeMap.remove(2, 320);
        timeMap.remove(2, 300);
        System.out.print(".");
        timeMap.remove(2, 100);
        System.out.print(".");
        timeMap.remove(2, 200);
        System.out.print(".");
        System.out.println();
        // end
        System.out.print("TimeMap is: " + (timeMap.isEmpty() ? "empty" : "non-empty"));
        System.out.println(" *** TESTSUITE " + (timeMap.isEmpty() ? "PASSES" : "FAILS") + " ***");

        System.out.println();
        System.out.println("====================");
        System.out.println("     Good Bye!");
        System.out.println("====================");
    }
}
