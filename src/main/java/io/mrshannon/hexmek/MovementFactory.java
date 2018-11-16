package io.mrshannon.hexmek;

/**
 * Factory to store movement point spec's and construct movement objects.
 */
public class MovementFactory {

    private HexMap map;
    private int cruiseMovementPoints;
    private int flankMovementPoints;

    /**
     * Create a movement factory.
     *
     * @param map the map that movement objects will be created for
     * @param cruiseMovementPoints movement points when cruising
     * @param flankMovementPoints movement points when flanking
     */
    public MovementFactory(HexMap map, int cruiseMovementPoints, int flankMovementPoints) {
        this.map = map;
        this.cruiseMovementPoints = cruiseMovementPoints;
        this.flankMovementPoints = flankMovementPoints;
    }

    /**
     * Create a halting movement, no movement allowed.
     *
     * @return the new movement object
     */
    public Halt createHalt() {
        return new Halt(map);
    }

    /**
     * Create a cruising movement, all movements allowed.
     *
     * @return the new movement object
     */
    public Cruise createCruise() {
        return new Cruise(map, cruiseMovementPoints);
    }

    /**
     * Create a flanking movement.  This is the fastest movement, but backwards movement is not allowed.
     *
     * @return the new movement object
     */
    public Flank createFlank() {
        return new Flank(map, flankMovementPoints);
    }

}
