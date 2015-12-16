package com.artbeatte.exercises.runlengthencoding;

/**
 * @author art.beatte
 * @version 10/22/15
 */
public class SampleRLE extends RunLengthEncoding {

    @Override
    public String encode(String value) {
        if (value == null || value.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        char[] chars = value.toCharArray();
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

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
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
}
