package io.mrshannon.hexmek;

/**
 * Cruising movement, this is the slowest form of movement and allows backwards movement.
 */
public class Cruise extends Movement {

    private Unit unit;

    /**
     * Construct a cruising movement.
     *
     * @param unit unit to move
     * @param movementPoints number of movement points to initialize with
     */
    public Cruise(Unit unit, int movementPoints) {
        super(movementPoints);
        this.unit = unit;
        visitHex(unit.getHex());
    }

    @Override
    public int getGunneryModifier() {
        return Movement.BASE_GUNNERY + 1;
    }

    @Override
    public void moveForward() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().apply(1, unit.getHex());
        visitHex(unit.getHex());
    }

    @Override
    public void moveBackward() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().apply(-1, unit.getHex());
        visitHex(unit.getHex());
    }

    @Override
    public void rotateRight() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().rotateRight();
    }

    @Override
    public void rotateLeft() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().rotateLeft();
    }

}
