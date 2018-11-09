package io.mrshannon.hexmek;

/**
 * Cruising movement, this is the slowest form of movement and allows backwards movement.
 */
public class Cruiser extends Mover {

    /**
     * Create a cruising movement.
     *
     * @param unit the unit to move
     */
    public Cruiser(Unit unit) {
        super(unit, unit.getCruisingMovementPoints());
    }

    /**
     * Move the unit backward one unit.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     */
    @Override
    public void moveBackward() throws MovementPointsExhaustedException {
        decrementMovementPoints();
        unit.getFacing().apply(1, unit.getHex());
    }

}
