package io.mrshannon.hexmek.models;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract unit, providing full implementation of movement methods.
 *
 * @see Unit
 */
public abstract class AbstractUnit implements Unit {

    private String type;
    private char id;
    private Hex hex;
    private Direction facing;
    private Movement currentMovement;
    private MovementFactory movementFactory;

    /**
     * Construct a new unit.
     *
     * @param type type of unit
     * @param id identification of unit, should be unique to each unit
     * @param hex starting hex coordinate
     * @param facing initial facing direction
     * @param movementFactory factory to use to construct movement strategies
     */
    public AbstractUnit(String type, char id, Hex hex, Direction facing, MovementFactory movementFactory) {
        this.type = type;
        this.id = id;
        this.hex = hex;
        this.facing = facing;
        this.movementFactory = movementFactory;
        this.currentMovement = movementFactory.createHalt();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public char getId() {
        return id;
    }

    @Override
    public Hex getHex() {
        return hex;
    }

    @Override
    public Direction getFacing() {
        return facing;
    }

    @Override
    public int getMovementPoints() {
        return currentMovement.getMovementPoints();
    }

    @Override
    public int getCruiseMovementPoints() {
        return movementFactory.getCruiseMovementPoints();
    }

    @Override
    public int getFlankMovementPoints() {
        return movementFactory.getFlankMovementPoints();
    }

    @Override
    public int getToHitModifier() {
        return currentMovement.getToHitModifier();
    }

    @Override
    public int getGunneryModifier() {
        return currentMovement.getGunneryModifier();
    }

    @Override
    public boolean canMove() {
        return !isDestroyed() && movementFactory.getCruiseMovementPoints() > 0;
    }

    /**
     * Damage the unit's movement.
     */
    protected void damageMobility() {
        movementFactory.damageMobility();
    }

    /**
     * Destroy the unit's movement.
     */
    protected void destroyMobility() {
        movementFactory.destroyMobility();
    }

    @Override
    public void halt() {
        currentMovement = movementFactory.createHalt();
    }

    @Override
    public void cruise() {
        currentMovement = movementFactory.createCruise();
    }

    @Override
    public void flank() {
        currentMovement = movementFactory.createFlank();
    }

    @Override
    public void moveForward() throws MovementPointsExhaustedException {
        hex = currentMovement.moveForward(hex, facing);
    }

    @Override
    public void moveBackward() throws MovementPointsExhaustedException {
        hex = currentMovement.moveBackward(hex, facing);
    }

    @Override
    public void rotateRight() throws MovementPointsExhaustedException {
        facing = currentMovement.rotateRight(facing);
    }

    @Override
    public void rotateLeft() throws MovementPointsExhaustedException {
        facing = currentMovement.rotateLeft(facing);
    }

    @Override
    public List<Weapon> getWeapons() {
        return getComponents().stream().filter(c -> !c.isDestroyed()).map(
                Component::getWeapons).flatMap(List::stream).collect(Collectors.toList());
    }

    @Override
    public boolean canFire() {
        return getWeapons().size() > 0;
    }

    @Override
    public int getArmour() {
        return getComponents().stream().mapToInt(Component::getArmour).sum();
    }

    @Override
    public int getMaxArmour() {
        return getComponents().stream().mapToInt(Component::getMaxArmour).sum();
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
     * @see AbstractUnit.UnitMemento
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
     * @see Memento
     */
    public class UnitMemento implements Memento {

        private AbstractUnit originator;
        private Hex hex;
        private Direction facing;
        private Movement currentMovement;

        /**
         * Construct a unit memento.
         *
         * @param originator unit to create memento for
         */
        private UnitMemento(AbstractUnit originator) {
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
