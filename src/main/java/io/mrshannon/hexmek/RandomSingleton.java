package io.mrshannon.hexmek;

import java.util.Random;

/**
 * Provides a singleton of the Random object.
 */
public class RandomSingleton {

    private static Random random = null;

    private RandomSingleton() {
    }

    /**
     * Get the global random random.
     *
     * @return global random random
     */
    public static Random getRandom() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }
}
