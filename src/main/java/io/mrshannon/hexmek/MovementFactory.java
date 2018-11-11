package io.mrshannon.hexmek;

/**
 * Factory to store movement point spec's and construct movement objects.
 */
public class MovementFactory {

    private int cruiseMovementPoints;
    private int flankMovementPoints;

    /**
     * Create a movement factory.
     *
     * @param cruiseMovementPoints movement points when cruising
     * @param flankMovementPoints movement points when flanking
     */
    public MovementFactory(int cruiseMovementPoints, int flankMovementPoints) {
        this.cruiseMovementPoints = cruiseMovementPoints;
        this.flankMovementPoints = flankMovementPoints;
    }

    /**
     * Create a halting movement, no movement allowed.
     *
     * @param unit unit to halt
     * @return the new movement object
     */
    public Halt createHalt(Unit unit) {
        return new Halt();
    }

    /**
     * Create a cruising movement, all movements allowed.
     *
     * @param unit unit to move
     * @return the new movement object
     */
    public Cruise createCruise(Unit unit) {
        return new Cruise(unit, cruiseMovementPoints);
    }

    /**
     * Create a flanking movement.  This is the fastest movement, but backwards movement is not allowed.
     *
     * @param unit unit to move
     * @return the new movement object
     */
    public Flank createFlank(Unit unit) {
        return new Flank(unit, flankMovementPoints);
    }

}
