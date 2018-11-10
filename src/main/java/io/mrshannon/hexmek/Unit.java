package io.mrshannon.hexmek;

public abstract class Unit implements Originator {

    private int cruisingMovementPoints;
    private int flankingMovementPoints;

    private String id;
    private Hex hex;
    private Direction facing;
    private int toHitModifier;
    private int gunneryModifier;

    public Unit(String id, Hex hex, Direction facing, int cruisingMovementPoints, int flankingMovementPoints) {
        this.id = id;
        this.hex = hex;
        this.facing = facing;
        this.cruisingMovementPoints = cruisingMovementPoints;
        this.flankingMovementPoints = flankingMovementPoints;
        this.gunneryModifier = 4;
        this.toHitModifier = 0;
    }

    public int getCruisingMovementPoints() {
        return cruisingMovementPoints;
    }

    public int getFlankingMovementPoints() {
        return flankingMovementPoints;
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
        return toHitModifier;
    }

    public int getGunneryModifier() {
        return gunneryModifier;
    }

    public Cruiser cruise() {
        return new Cruiser(this);
    }

    public Flanker flank() {
        return new Flanker(this);
    }

    @Override
    public Memento save() {
        return new UnitMemento(this);
    }

    public class UnitMemento implements Memento {

        private Unit originator;
        private Hex hex;
        private Direction facing;
        private int toHitModifier;
        private int gunneryModifier;

        UnitMemento(Unit originator) {
            this.originator = originator;
            this.hex = originator.hex;
            this.facing = originator.facing;
            this.toHitModifier = originator.toHitModifier;
            this.gunneryModifier = originator.gunneryModifier;
        }

        @Override
        public void restore() {
            originator.hex = hex;
            originator.facing = facing;
            originator.toHitModifier = toHitModifier;
            originator.gunneryModifier = gunneryModifier;
        }
    }

}
