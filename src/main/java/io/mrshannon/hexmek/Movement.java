package io.mrshannon.hexmek;

import java.util.HashSet;

/**
 * Base of all movement types that are used to initiate a move for a unit.
 */
public abstract class Movement implements Cloneable {

    private int movementPoints;
    private HashSet<Hex> visitedHexes;

    /**
     * Base gunnery modifier.  This will be added to all gunnery (attack) modifier calculations.
     */
    public static final int BASE_GUNNERY = 4;

    /**
     * Construct a movement object.
     *
     * @param movementPoints how many movement points to initialize the mover with
     */
    public Movement(int movementPoints) {
        this.movementPoints = movementPoints;
        this.visitedHexes = new HashSet<>();
    }

    /**
     * Copy constructor.
     *
     * @param other movement to copy
     */
    public Movement(Movement other) {
        this.movementPoints = other.movementPoints;
        this.visitedHexes = new HashSet<>(other.visitedHexes);
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
     * Record a visit to a hex.
     *
     * @param hex visited hex grid, it will be copied
     */
    protected void visitHex(Hex hex) {
        visitedHexes.add(new Hex(hex));
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
    public abstract int getGunneryModifier();

    /**
     * Move the unit forward one unit.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if forwards movement is not supported for the movement type
     */
    public void moveForward() throws MovementPointsExhaustedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Move the unit backward one unit.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if backwards movement is not supported for the movement type
     */
    public void moveBackward() throws MovementPointsExhaustedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Rotate the unit to the right.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if right rotation is not supported for the movement type
     */
    public  void rotateRight() throws MovementPointsExhaustedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Rotate the unit to the left.
     *
     * @throws MovementPointsExhaustedException if no movement points remain for the movement
     * @throws UnsupportedOperationException if left rotation is not supported for the movement type
     */
    public void rotateLeft() throws MovementPointsExhaustedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Clone the movement object.
     *
      * @return new movement object
     */
    public abstract Object clone();

}
