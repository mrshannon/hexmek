package io.mrshannon.hexmek;

/**
 * Flanking movement, this is the fastest form of movement but does not allow backwards movement.
 */
public class Flank extends Movement {

    private Unit unit;

    /**
     * Construct a flanking movement.
     *
     * @param unit unit to move
     * @param movementPoints number of movement points to begin with
     */
    public Flank(Unit unit, int movementPoints) {
        super(movementPoints);
        this.unit = unit;
        visitHex(unit.getHex());
    }

    /**
     * Copy constructor.
     *
     * @param other flank object to copy
     */
    public Flank(Flank other) {
        super(other);
        this.unit = other.unit;
    }

    @Override
    public int getGunneryModifier() {
        return Movement.BASE_GUNNERY + 2;
    }

    @Override
    public void moveForward() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().apply(1, unit.getHex());
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

    @Override
    public Object clone() {
        return new Flank(this);
    }

}
