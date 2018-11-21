package io.mrshannon.hexmek.models;

public interface Movable {

    /**
     * Get the current hex coordinate of the unit.
     *
     * @return current hex coordinate
     */
    Hex getHex();

    /**
     * Get the current facing direction of the unit.
     *
     * @return current facing direction
     */
    Direction getFacing();

    /**
     * Get remaining movement points.
     *
     * <br>
     *
     * Movement points are required to move.
     *
     * @return remaining movement points
     */
    int getMovementPoints();

    /**
     * Determine whether or not the object can be moved or not.
     *
     * @return true if the object can be moved, false if it can't
     */
    boolean canMove();

    /**
     * Begin halting movement (no movement), resting modifiers and movement points.
     */
    void halt();

    /**
     * Begin cruising movement (slowest, but with backwards movement), resting modifiers and movement points.
     */
    void cruise();

    /**
     * Begin flanking movement (fastest, no backwards movement), resting modifiers and movement points.
     */
    void flank();

    /**
     * Move unit forward one hexagonal coordinate.
     *
     * @throws MovementPointsExhaustedException if no movement points remain
     * @throws UnsupportedOperationException if current movement strategy does not support forward movement
     */
    void moveForward() throws MovementPointsExhaustedException;

    /**
     * Move unit backward one hexagonal coordinate.
     *
     * @throws MovementPointsExhaustedException if no movement points remain
     * @throws UnsupportedOperationException if current movement strategy does not support backward movement
     */
    void moveBackward() throws MovementPointsExhaustedException;

    /**
     * Rotate unit right (1 direction).
     *
     * @throws MovementPointsExhaustedException if no movement points remain
     * @throws UnsupportedOperationException if current movement strategy does not support rotation to the right
     */
    void rotateRight() throws MovementPointsExhaustedException;

    /**
     * Rotate unit right (1 direction).
     *
     * @throws MovementPointsExhaustedException if no movement points remain
     * @throws UnsupportedOperationException if current movement strategy does not support rotation to the left
     */
    void rotateLeft() throws MovementPointsExhaustedException;

}
