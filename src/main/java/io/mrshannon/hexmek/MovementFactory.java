package io.mrshannon.hexmek;

/**
 * Factory to store movement point spec's and construct movement objects.
 */
public class MovementFactory {

    private HexMap map;
    private int cruiseMovementPoints;

    /**
     * Create a movement factory.
     *
     * @param map the map that movement objects will be created for
     * @param cruiseMovementPoints movement points when cruising
     */
    public MovementFactory(HexMap map, int cruiseMovementPoints) {
        this.map = map;
        this.cruiseMovementPoints = cruiseMovementPoints;
    }

    /**
     * Get number of cruising movement points.
     *
     * @return cruising movement points
     */
    public int getCruiseMovementPoints() {
        return cruiseMovementPoints;
    }

    /**
     * Get number of flanking movement points.
     *
     * @return flanking movement points
     */
    public int getFlankMovementPoints() {
        return (int) Math.ceil(((double) cruiseMovementPoints)*1.5);
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
        return new Cruise(map, getCruiseMovementPoints());
    }

    /**
     * Create a flanking movement.  This is the fastest movement, but backwards movement is not allowed.
     *
     * @return the new movement object
     */
    public Flank createFlank() {
        return new Flank(map, getFlankMovementPoints());
    }

    /**
     * Apply damage to the movement factory, reducing the total movement points.
     */
    public void damageMobility() {
        if (cruiseMovementPoints > 0) {
            --cruiseMovementPoints;
        }
    }

    /**
     * Destroy the movement factory, reducing movement points to zero.
     */
    public void destroyMobility() {
        cruiseMovementPoints = 0;
    }

}
