package io.mrshannon.hexmek;

import java.util.Random;

/**
 * Provides a singleton of the Random object.
 */
public class RandomSingleton {

    private static Random instance = null;

    private RandomSingleton() {
    }

    /**
     * Get the global random instance.
     *
     * @return global random instance
     */
    public static Random getInstance() {
        if (instance == null) {
            instance = new Random();
        }
        return instance;
    }
}
