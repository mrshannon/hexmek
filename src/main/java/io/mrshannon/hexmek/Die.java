package io.mrshannon.hexmek;

import java.util.Random;

/**
 * This class represents a single die of any Dn size.
 *
 * Dice are always constructed with an initial roll.
 *
 * @author Michael R. Shannon
 */
public class Die {

    private int faces;
    private int value;
    private Random random;

    /**
     * Create a die with its own randomizer.
     *
     * @param faces   Number of faces of the die, must be positive.
     * @throws IllegalArgumentException if the size is not positive.
     */
    public Die(int faces) {
        this(faces, new Random());
    }

    /**
     * Create a die with a randomizer via dependency injection.
     *
     * @param faces   Number of faces of the die, must be positive.
     * @param random Randomizer instance to use.
     * @throws IllegalArgumentException if the size is not positive.
     */
    public Die(int faces, Random random) {
        if (faces < 1) {
            throw new IllegalArgumentException("Number of die faces must be a positive integer.");
        }
        this.faces = faces;
        this.random = random;
        roll();
    }

    /**
     * Get string representing the die in the form of "d{faces} = {value}".
     *
     * @return Representative string.
     */
    @Override
    public String toString() {
        return String.format("d%d = %d", faces, value);
    }

    /**
     * Roll the die, generating a new value.
     */
    public void roll() {
        value = random.nextInt(faces) + 1;
    }

    /**
     * Get the number of faces of the die.
     *
     * @return Number of faces of the die.
     */
    public int getFaces() {
        return faces;
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
