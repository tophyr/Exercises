package com.artbeatte.exercises.testing;

/**
 * @author art.beatte
 * @version 11/13/15
 */
public interface TestCase {

    /**
     * @return the name of the TestCase
     */
    String getName();

    /**
     * Executes the test
     * @return true if the test was successful.
     */
    boolean execute();
}
