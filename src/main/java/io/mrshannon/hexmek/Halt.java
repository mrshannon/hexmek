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

    @Override
    public int getToHitModifier() {
        return 0;
    }

    @Override
    public int getGunneryModifier() {
        return Movement.BASE_GUNNERY;
    }
}
