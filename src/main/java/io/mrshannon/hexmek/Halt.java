package io.mrshannon.hexmek;

/**
 * Do not move.
 */
public class Halt extends Movement {

    /**
     * Construct a halt movement object.
     */
    public Halt() {
        super(0);
    }

    /**
     * Copy constructor.
     *
     * @param other halt object to copy.
     */
    public Halt(Halt other) {
        super(other);
    }

    @Override
    public int getToHitModifier() {
        return 0;
    }

    @Override
    public int getGunneryModifier() {
        return Movement.BASE_GUNNERY;
    }

    @Override
    public Object clone() {
        return new Halt(this);
    }
}
