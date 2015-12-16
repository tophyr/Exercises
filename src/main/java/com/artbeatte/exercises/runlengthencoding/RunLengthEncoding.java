package com.artbeatte.exercises.runlengthencoding;

/**
 * Created by sarbs on 12/16/15.
 */
public abstract class RunLengthEncoding {

    /**
     * Encodes the given {@link String} using the following rules:
     * 1. Encoded {@link String} must not be longer than the given {@link String}.
     * 2. Encoded {@link String} will condense runs ('aaa' -> 'a3').
     * 3. Encoded {@link String} will convert all `0-9` characters into `A-J`.
     * @return the encoded {@link String}
     */
    public abstract String encode(String value);

    public abstract String decode(String encoded);
}
