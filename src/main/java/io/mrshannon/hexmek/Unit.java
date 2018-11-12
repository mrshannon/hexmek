package io.mrshannon.hexmek;

public abstract class Unit implements Originator {

    private char id;
    private Hex hex;
    private Direction facing;
    private MovementFactory movementFactory;
    private Movement currentMovement;

    /**
     * Construct a new unit.
     *
     * @param id identification of unit, should be unique to each unit
     * @param hex starting hex coordinate
     * @param facing initial facing direction
     * @param movementFactory factory to use to construct movement strategies
     */
    public Unit(char id, Hex hex, Direction facing, MovementFactory movementFactory) {
        this.id = id;
        this.hex = hex;
        this.facing = facing;
        this.movementFactory = movementFactory;
        this.currentMovement = movementFactory.createHalt();
    }

    /**
     * Get the ID of the unit.
     *
     * @return unique (per game) unit ID
     */
    public char getId() {
        return id;
    }

    /**
     * Get the current hex coordinate of the unit.
     *
     * @return current hex coordinate
     */
    public Hex getHex() {
        return hex;
    }

    /**
     * Get the current facing direction of the unit.
     *
     * @return current facing direction
     */
    public Direction getFacing() {
        return facing;
    }

    /**
     * Get remaining movement points.
     *
     * <br>
     *
     * Movement points are required to move.
     *
     * @return remaining movement points
     */
    public int getMovementPoints() {
        return currentMovement.getMovementPoints();
    }

    /**
     * Get the current to-hit modifier.
     *
     * @return current to hit modifier
     */
    public int getToHitModifier() {
        return currentMovement.getToHitModifier();
    }

    /**
     * Get the current gunnery/attack modifier.
     *
     * @return current attack modifier
     */
    public int getGunneryModifier() {
        return currentMovement.getGunneryModifier();
    }

    /**
     * Begin halting movement (no movement), resting modifiers and movement points.
     */
    public void halt() {
        currentMovement = movementFactory.createHalt();
    }

    /**
     * Begin cruising movement (slowest, but with backwards movement), resting modifiers and movement points.
     */
    public void cruise() {
        currentMovement = movementFactory.createCruise();
    }

    /**
     * Begin flanking movement (fastest, no backwards movement), resting modifiers and movement points.
     */
    public void flank() {
        currentMovement = movementFactory.createFlank();
    }

    /**
     * Move unit forward one hexagonal coordinate.
     *
     * @throws MovementPointsExhaustedException if no movement points remain
     * @throws UnsupportedOperationException if current movement strategy does not support forward movement
     */
    public void moveForward() throws MovementPointsExhaustedException {
        hex = currentMovement.moveForward(hex, facing);
    }

    /**
     * Move unit backward one hexagonal coordinate.
     *
     * @throws MovementPointsExhaustedException if no movement points remain
     * @throws UnsupportedOperationException if current movement strategy does not support backward movement
     */
    public void moveBackward() throws MovementPointsExhaustedException {
        hex = currentMovement.moveBackward(hex, facing);
    }

    /**
     * Rotate unit right (1 direction).
     *
     * @throws MovementPointsExhaustedException if no movement points remain
     * @throws UnsupportedOperationException if current movement strategy does not support rotation to the right
     */
    public void rotateRight() throws MovementPointsExhaustedException {
        facing = currentMovement.rotateRight(facing);
    }

    /**
     * Rotate unit right (1 direction).
     *
     * @throws MovementPointsExhaustedException if no movement points remain
     * @throws UnsupportedOperationException if current movement strategy does not support rotation to the left
     */
    public void rotateLeft() throws MovementPointsExhaustedException {
        facing = currentMovement.rotateLeft(facing);
    }

    /**
     * Save the current location and movement state of the unit.
     *
     * <br>
     *
     * This does not save damage or ammo state as it is only intended to for use in the movement phase.
     *
     * @return a memento containing the current state
     *
     * @see io.mrshannon.hexmek.Unit.UnitMemento
     */
    @Override
    public Memento save() {
        return new UnitMemento(this);
    }

    /**
     * Memento to save the location and movement state of a unit.
     *
     * <br>
     *
     * Does not save damage or ammo state.
     *
     * @see io.mrshannon.hexmek.Memento
     */
    public class UnitMemento implements Memento {

        private Unit originator;
        private Hex hex;
        private Direction facing;
        private Movement currentMovement;

        /**
         * Construct a unit memento.
         *
         * @param originator unit to create memento for
         */
        private UnitMemento(Unit originator) {
            this.originator = originator;
            this.hex = originator.hex;
            this.facing = originator.facing;
            this.currentMovement = (Movement) originator.currentMovement.clone();
        }

        /**
         * Restore the original unit to the saved state in the memento.
         */
        @Override
        public void restore() {
            originator.hex = hex;
            originator.facing = facing;
            originator.currentMovement = (Movement) currentMovement.clone();
        }
    }

}
