package com.artbeatte.exercises.testing;

import java.util.ArrayList;
import java.util.List;

/**
 * @author art.beatte
 * @version 11/13/15
 */
public class SystemTestRunner {

    List<TestCase> mTestCases;

    public SystemTestRunner() {
        mTestCases = new ArrayList<>();
    }

    public void addTestCase(TestCase test) {
        mTestCases.add(test);
    }

    public void runTests() {
        int failures = 0;
        printBanner("   Starting Tests    ");
        for (TestCase test : mTestCases) {
            boolean success = test.execute();
            if (!success) failures ++;
            System.out.println(test.getName() + " test: " + (success ? "PASSES" : "FAILS"));
        }
        printResults(failures, mTestCases.size());
        printBanner("  Testing Completed  ");
    }

    private static void printBanner(String msg) {
        String divider = "";
        for (int i = 0; i < msg.length(); i ++) {
            divider += "=";
        }
        System.out.println();
        System.out.println(divider);
        System.out.println(msg);
        System.out.println(divider);
        System.out.println();
    }

    private static void printResults(int numFailures, int numTotalTests) {
        String results;
        if (numFailures > 0) {
            results = " TestCase Failures: " + numFailures + "/" + numTotalTests + "  ";
        } else {
            results = "All Tests Pass! (" + numTotalTests + "/" + numTotalTests + ")";
        }
        String divider = "";
        for (int i = 0; i < results.length(); i ++) {
            divider += "-";
        }
        System.out.println();
        System.out.println(divider);
        System.out.print(results);
    }
}
