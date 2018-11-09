package io.mrshannon.hexmek;

/**
 * Base of all movement types that are used to initiate a move for a unit.
 */
public abstract class Mover {

    protected Unit unit;
    private int movementPoints;


    /**
     * Construct a mover.
     *
     * @param unit the unit to create the mover for
     * @param movementPoints how many movement points to initialize the mover with
     */
    public Mover(Unit unit, int movementPoints) {
        this.unit = unit;
        this.movementPoints = movementPoints;
    }

    /**
     * Decrement the internal movement point counter.
     *
     * @throws MovementPointsExhaustedException if there are no movement points before the decrement
     */
    protected void decrementMovementPoints() throws MovementPointsExhaustedException {
        if (movementPoints-- == 0) {
            throw new MovementPointsExhaustedException("Out of movement points.");
        }
    }

    /**
     * Get the number of movement points remaining.
     *
     * @return remaining movement points
     */
    public int getMovementPoints() {
        return movementPoints;
    }

    /**
     * Move the unit forward one unit.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     */
    public void moveForward() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().apply(1, unit.getHex());
    }

    /**
     * Move the unit backward one unit.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if backwards movement is not supported for the movement type
     */
    public abstract void moveBackward() throws MovementPointsExhaustedException, UnsupportedOperationException;

    /**
     * Rotate the unit to the right.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     */
    void rotateRight() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().rotateRight();
    }

    /**
     * Rotate the unit to the left.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     */
    void rotateLeft() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().rotateRight();
    }

}
