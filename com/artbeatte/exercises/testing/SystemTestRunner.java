package com.artbeatte.exercises.testing;

/**
 * @author art.beatte
 * @version 11/13/15
 */
public class SystemTestRunner extends BaseTestRunner {

    @Override
    public void runTests() {
        int failures = 0;
        System.out.print(getBanner("   Starting Tests    "));
        for (TestCase test : mTestCases) {
            boolean success = test.execute();
            if (!success) failures ++;
            System.out.println(test.getName() + " test: " + (success ? "PASSES" : "FAILS"));
        }
        System.out.print(getResults(failures, mTestCases.size()));
        System.out.print(getBanner("  Testing Completed  "));
    }
}
