package com.coditory.sandbox.generators;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class StringGenerator {
    private static final Random random = new Random();

    static String alphaString(int length) {
        return alphaString(length, 'a', 'z');
    }

    static String alphaString(int length, char firstChar, char lastChar) {
        return random.ints(firstChar, lastChar + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
