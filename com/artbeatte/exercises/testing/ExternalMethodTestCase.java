package com.artbeatte.exercises.testing;

/**
 * @author art.beatte
 * @version 11/13/15
 */
public class ExternalMethodTestCase implements TestCase {

    public interface ExternalTest {
        /**
         * External TestCase Case to be ran
         * @return true if the test was successful
         */
        boolean execute();
    }

    private final String mName;
    private final ExternalTest mExternalTest;

    public ExternalMethodTestCase(String testName, ExternalTest externalTest) {
        mName = testName;
        mExternalTest = externalTest;
    }

    @Override
    public String getName() {
        return mName;
    }

    public boolean execute() {
        return mExternalTest.execute();
    }
}
