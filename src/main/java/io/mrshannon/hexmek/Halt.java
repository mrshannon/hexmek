package io.mrshannon.hexmek;

/**
 * Do not move.
 */
public class Halt extends Movement {

    /**
     * Construct a halt movement object.
     *
     * @param map the map that movement will be on
     */
    public Halt(HexMap map) {
        super(map, 0);
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
        return super.getGunneryModifier();
    }

    @Override
    public Object clone() {
        return new Halt(this);
    }
}
