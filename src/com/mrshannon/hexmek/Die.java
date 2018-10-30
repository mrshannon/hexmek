package com.mrshannon.hexmek;

import java.util.Random;

/**
 * This class represents a single die of any Dn size.
 *
 * Dice are always constructed with an initial roll.
 *
 * @author Michael R. Shannon
 */
public class Die {

    private int size;
    private int value;
    private Random random;

    /**
     * Create a die with a randomizer via dependency injection.
     *
     * @param size   Size of the die, must be positive.
     * @param random Randomizer instance to use.
     * @throws IllegalArgumentException if the size is not positive.
     */
    public Die(int size, Random random) {
        if (size < 1) {
            throw new IllegalArgumentException("Die size must be a positive integer.");
        }
        this.size = size;
        this.random = random;
        roll();
    }

    /**
     * Create a die with its own randomizer.
     *
     * @param size   Size of the die, must be positive.
     * @throws IllegalArgumentException if the size is not positive.
     */
    public Die(int size) {
        this(size, new Random());
    }

    /**
     * Roll the die, generating a new value.
     */
    public void roll() {
        value = random.nextInt(size - 1) + 1;
    }

    /**
     * Get the size of the die.
     *
     * @return Numerical size of the die.
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the current value of the die.
     *
     * @return Value of die.
     */
    public int getValue() {
        return value;
    }

}
