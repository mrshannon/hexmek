package io.mrshannon.hexmek;

/**
 * Flanking movement, this is the fastest form of movement but does not allow backwards movement.
 */
public class Flank extends Movement {

    /**
     * Construct a flanking movement.
     *
     * @param movementPoints number of movement points to begin with
     */
    public Flank(int movementPoints) {
        super(movementPoints);
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
        decrementMovementPoints();
        Hex newHex = facing.apply(hex);
        visitHex(newHex);
        return newHex;
    }

    @Override
    public Direction rotateRight(Direction facing) throws MovementPointsExhaustedException {
        decrementMovementPoints();
        return facing.rotateRight();
    }

    @Override
    public Direction rotateLeft(Direction facing) throws MovementPointsExhaustedException {
        decrementMovementPoints();
        return facing.rotateLeft();
    }

    @Override
    public Object clone() {
        return new Flank(this);
    }

}
