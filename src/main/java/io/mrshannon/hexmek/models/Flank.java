package io.mrshannon.hexmek.models;

/**
 * Flanking movement, this is the fastest form of movement but does not allow backwards movement.
 */
public class Flank extends Movement {

    /**
     * Construct a flanking movement.
     *
     * @param map the map that movement will be on
     * @param movementPoints number of movement points to begin with
     */
    public Flank(HexMap map, int movementPoints) {
        super(map, movementPoints);
    }

    /**
     * Copy constructor.
     *
     * @param other flank object to copy
     */
    public Flank(Flank other) {
        super(other);
    }

    @Override
    public int getGunneryModifier() {
        return super.getGunneryModifier() + 2;
    }

    @Override
    public Hex moveForward(Hex hex, Direction facing) throws MovementPointsExhaustedException {
        Hex newHex = facing.apply(hex);
        decrementMovementPoints(getMovementCost(newHex));
        visitHex(newHex);
        return newHex;
    }

    @Override
    public Direction rotateRight(Direction facing) throws MovementPointsExhaustedException {
        decrementMovementPoints(1);
        return facing.rotateRight();
    }

    @Override
    public Direction rotateLeft(Direction facing) throws MovementPointsExhaustedException {
        decrementMovementPoints(1);
        return facing.rotateLeft();
    }

    @Override
    public Object clone() {
        return new Flank(this);
    }

}
