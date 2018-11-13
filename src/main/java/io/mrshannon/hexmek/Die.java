package io.mrshannon.hexmek;

/**
 * This class represents a single die of any Dn size.
 * <p>
 * Dice are always constructed with an initial roll.
 *
 * @author Michael R. Shannon
 */
public class Die {

    private int faces;
    private int value;

    /**
     * Create a die with its own randomizer.
     *
     * @param faces  number of faces of the die, must be positive
     * @throws IllegalArgumentException if the size is not positive
     */
    public Die(int faces) {
        if (faces < 1) {
            throw new IllegalArgumentException("Number of die faces must be a positive integer.");
        }
        this.faces = faces;
        roll();
    }

    /**
     * Get string representing the die in the form of "d{faces} = {value}".
     *
     * @return representative string
     */
    @Override
    public String toString() {
        return String.format("d%d = %d", faces, value);
    }

    /**
     * Roll the die, generating a new random value.
     */
    public void roll() {
        value = RandomSingleton.getInstance().nextInt(faces) + 1;
    }

    /**
     * Get the number of faces of the die.
     *
     * @return number of faces of the die
     */
    public int getFaces() {
        return faces;
    }

    /**
     * Get the current value of the die.
     *
     * @return value of die
     */
    public int getValue() {
        return value;
    }

}
