package io.mrshannon.hexmek;

/**
 * Cruising movement, this is the slowest form of movement but allows backwards movement.
 */
public class Cruise extends Movement {

    /**
     * Construct a cruising movement.
     *
     * @param movementPoints number of movement points to initialize with
     */
    public Cruise(int movementPoints) {
        super(movementPoints);
    }

    /**
     * Copy constructor.
     *
     * @param other cruise object to copy
     */
    public Cruise(Cruise other) {
        super(other);
    }

    @Override
    public int getGunneryModifier() {
        return super.getGunneryModifier() + 1;
    }

    @Override
    public Hex moveForward(Hex hex, Direction facing) throws MovementPointsExhaustedException {
        decrementMovementPoints();
        Hex newHex = facing.apply(hex);
        visitHex(newHex);
        return newHex;
    }

    @Override
    public Hex moveBackward(Hex hex, Direction facing) throws MovementPointsExhaustedException {
        decrementMovementPoints();
        Hex newHex = facing.apply(-1, hex);
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
        return new Cruise(this);
    }

}
