package io.mrshannon.hexmek;

import java.util.HashSet;

/**
 * Base of all movement types that are used to initiate a move for a unit.
 */
public abstract class Movement implements Cloneable {

    private HexMap map;
    private int movementPoints;
    private HashSet<Hex> visitedHexes;

    /**
     * Base gunnery modifier.  This will be added to all gunnery (attack) modifier calculations.
     */
    public static final int BASE_GUNNERY = 4;

    /**
     * Construct a movement object.
     *
     * @param map the map that movement will be on
     * @param movementPoints how many movement points to initialize the mover with
     */
    public Movement(HexMap map, int movementPoints) {
        this.map = map;
        this.movementPoints = movementPoints;
        this.visitedHexes = new HashSet<>();
    }

    /**
     * Copy constructor.
     *
     * @param other movement to copy
     */
    public Movement(Movement other) {
        this.map = map;
        this.movementPoints = other.movementPoints;
        this.visitedHexes = new HashSet<>(other.visitedHexes);
    }

    /**
     * Get the movement cost to move into the given hex coordinate.
     *
     * @param hex the hex coordinate to get the movement cost for
     * @return movement cost for the given {@code hex}
     */
    public int getMovementCost(Hex hex) {
        return map.getTile(hex).getMovementCost() + 1;
    }

    /**
     * Decrement the internal movement point counter.
     *
     * @param movementCost how many movement points to use up
     * @throws MovementPointsExhaustedException if there are no movement points before the decrement
     */
    public void decrementMovementPoints(int movementCost) throws MovementPointsExhaustedException {
        if ((movementPoints - movementCost) < 0) {
            throw new MovementPointsExhaustedException("Out of movement points.");
        }
        movementPoints -= movementCost;
    }

    /**
     * Record a visit to a hex.
     *
     * @param hex visited hex grid
     */
    protected void visitHex(Hex hex) {
        visitedHexes.add(hex);
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
     * Get the to hit modifier created by the movement, so far.
     *
     * @return to hit modifier
     */
    public int getToHitModifier() {
        int hexes = visitedHexes.size();
        if (hexes < 3) {
            return 0;
        }
        if (hexes < 5) {
            return 1;
        }
        if (hexes < 7) {
            return 2;
        }
        if (hexes < 10) {
            return 3;
        }
        if (hexes < 18) {
            return 4;
        }
        if (hexes < 25) {
            return 5;
        }
        return 6;
    }

    /**
     * Get the gunnery modifier created by the movement so far.
     *
     * @return gunnery (attack) modifier
     */
    public int getGunneryModifier() {
        return Movement.BASE_GUNNERY;
    }

    /**
     * Move unit forward one unit.
     *
     * @param hex current hex coordinate
     * @param facing current facing direction
     * @return new hex coordinate
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if forwards movement is not supported for the movement type
     */
    public Hex moveForward(Hex hex, Direction facing) throws MovementPointsExhaustedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Move unit backward one unit.
     *
     * @param hex current hex coordinate
     * @param facing current facing direction
     * @return new hex coordinate
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if backwards movement is not supported for the movement type
     */
    public Hex moveBackward(Hex hex, Direction facing) throws MovementPointsExhaustedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Rotate unit to the right.
     *
     * @param facing current facing direction
     * @return new facing direction
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if right rotation is not supported for the movement type
     */
    public Direction rotateRight(Direction facing) throws MovementPointsExhaustedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Rotate unit to the left.
     *
     * @param facing current facing direction
     * @return new facing direction
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if left rotation is not supported for the movement type
     */
    public Direction rotateLeft(Direction facing) throws MovementPointsExhaustedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Clone the movement object.
     *
      * @return new movement object
     */
    public abstract Object clone();

}
