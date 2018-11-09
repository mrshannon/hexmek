package io.mrshannon.hexmek;

/**
 * Flanking movement, this is the fastest form of movement but does not allow backwards movement.
 */
public class Flanker extends Mover {

    /**
     * Create a flanking movement.
     *
     * @param unit the unit to move
     */
    public Flanker(Unit unit) {
        super(unit, unit.getFlankingMovementPoints());
    }

    /**
     * Backwards movement is not supported for flanking movements.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public void moveBackward() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Moving backwards is not supported when flanking.");
    }
}
