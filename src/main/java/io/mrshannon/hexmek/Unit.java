package io.mrshannon.hexmek;

public abstract class Unit implements Originator {

    private String id;
    private Hex hex;
    private Direction facing;
    private MovementFactory movementFactory;
    private Movement currentMovement;

    public Unit(String id, Hex hex, Direction facing, MovementFactory movementFactory) {
        this.id = id;
        this.hex = hex;
        this.facing = facing;
        this.movementFactory = movementFactory;
        this.currentMovement = movementFactory.createHalt(this);
    }

    public String getId() {
        return id;
    }

    public Hex getHex() {
        return hex;
    }

    public Direction getFacing() {
        return facing;
    }

    public int getToHitModifier() {
        return currentMovement.getToHitModifier();
    }

    public int getGunneryModifier() {
        return currentMovement.getGunneryModifier();
    }

    public Halt halt() {
        Halt haltInstance = movementFactory.createHalt(this);
        currentMovement = haltInstance;
        return haltInstance;
    }

    public Cruise cruise() {
        Cruise cruiseInstance = movementFactory.createCruise(this);
        currentMovement = cruiseInstance;
        return cruiseInstance;
    }

    public Flank flank() {
        Flank flankInstance = movementFactory.createFlank(this);
        currentMovement = flankInstance;
        return flankInstance;
    }

    @Override
    public Memento save() {
        return new UnitMemento(this);
    }

    public class UnitMemento implements Memento {

        private Unit originator;
        private Hex hex;
        private Direction facing;
        private Movement currentMovement;

        UnitMemento(Unit originator) {
            this.originator = originator;
            this.hex = originator.hex;
            this.facing = originator.facing;
            this.currentMovement = (Movement) originator.currentMovement.clone();
        }

        @Override
        public void restore() {
            originator.hex = hex;
            originator.facing = facing;
            originator.currentMovement = (Movement) currentMovement.clone();
        }
    }

}
