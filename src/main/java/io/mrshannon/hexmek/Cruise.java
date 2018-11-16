package io.mrshannon.hexmek;

/**
 * Cruising movement, this is the slowest form of movement but allows backwards movement.
 */
public class Cruise extends Movement {

    /**
     * Construct a cruising movement.
     *
     * @param map the map that movement will be on
     * @param movementPoints number of movement points to initialize with
     */
    public Cruise(HexMap map, int movementPoints) {
        super(map, movementPoints);
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
        Hex newHex = facing.apply(hex);
        decrementMovementPoints(getMovementCost(newHex));
        visitHex(newHex);
        return newHex;
    }

    @Override
    public Hex moveBackward(Hex hex, Direction facing) throws MovementPointsExhaustedException {
        Hex newHex = facing.apply(-1, hex);
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
        return new Cruise(this);
    }

}
