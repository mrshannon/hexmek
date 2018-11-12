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
     * @return the new movement object
     */
    public Halt createHalt() {
        return new Halt();
    }

    /**
     * Create a cruising movement, all movements allowed.
     *
     * @return the new movement object
     */
    public Cruise createCruise() {
        return new Cruise(cruiseMovementPoints);
    }

    /**
     * Create a flanking movement.  This is the fastest movement, but backwards movement is not allowed.
     *
     * @return the new movement object
     */
    public Flank createFlank() {
        return new Flank(flankMovementPoints);
    }

}
