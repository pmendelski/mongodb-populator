package com.coditory.sandbox.generators;

import java.util.concurrent.ThreadLocalRandom;

public class IntGenerator {
    static public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    static public int randomInt(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }
}
