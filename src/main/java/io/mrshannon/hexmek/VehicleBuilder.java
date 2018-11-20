package io.mrshannon.hexmek;

import java.util.ArrayList;

/**
 * A vehicle builder.  This implements the builder pattern to assist in making vehicle units.
 */
public class VehicleBuilder implements UnitBuilder {

    private String type;
    private int movementPoints;

    private int turretArmour;
    private int frontArmour;
    private int rearArmour;
    private int rightSideArmour;
    private int leftSideArmour;

    private ArrayList<Weapon> turretWeapons;
    private ArrayList<Weapon> frontWeapons;
    private ArrayList<Weapon> rearWeapons;
    private ArrayList<Weapon> rightSideWeapons;
    private ArrayList<Weapon> leftSideWeapons;

    /**
     * Construct a new vehicle builder.
     *
     * @param type the type of the vehicle to build
     * @param movementPoints cruising (walking) movement points for the vehicle
     */
    public VehicleBuilder(String type, int movementPoints) {
        this.type = type;
        this.movementPoints = movementPoints;

        this.turretArmour = 1;
        this.frontArmour = 1;
        this.rearArmour = 1;
        this.rightSideArmour = 1;
        this.leftSideArmour = 1;

        this.turretWeapons = new ArrayList<>();
        this.frontWeapons = new ArrayList<>();
        this.rearWeapons = new ArrayList<>();
        this.rightSideWeapons = new ArrayList<>();
        this.leftSideWeapons = new ArrayList<>();
    }

    /**
     * Set the turret armour value.
     *
     * @param armour turret armour points
     * @return the builder
     */
    public VehicleBuilder turret(int armour) {
        turretArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the turret.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public VehicleBuilder turret(Weapon weapon) {
        turretWeapons.add(weapon);
        return this;
    }

    /**
     * Set the front armour value.
     *
     * @param armour front armour points
     * @return the builder
     */
    public VehicleBuilder front(int armour) {
        frontArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the front.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public VehicleBuilder front(Weapon weapon) {
        frontWeapons.add(weapon);
        return this;
    }

    /**
     * Set the rear armour value.
     *
     * @param armour rear armour points
     * @return the builder
     */
    public VehicleBuilder rear(int armour) {
        rearArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the rear.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public VehicleBuilder rear(Weapon weapon) {
        rearWeapons.add(weapon);
        return this;
    }

    /**
     * Set the right side armour value.
     *
     * @param armour right side armour points
     * @return the builder
     */
    public VehicleBuilder rightSide(int armour) {
        rightSideArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the rear.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public VehicleBuilder rightSide(Weapon weapon) {
        rightSideWeapons.add(weapon);
        return this;
    }

    /**
     * Set the left side armour value.
     *
     * @param armour left side armour points
     * @return the builder
     */
    public VehicleBuilder leftSide(int armour) {
        leftSideArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the rear.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public VehicleBuilder leftSide(Weapon weapon) {
        leftSideWeapons.add(weapon);
        return this;
    }

    /**
     * Build the vehicle.  This uses all of the settings set on the builder to this point.
     *
     * <br>
     *
     * Multiple vehicles's can be built from a single builder.
     *
     * @param id unique, single character id of unit
     * @param map map to place the unit on
     * @param hex location to put the unit
     * @param facing the facing direction of the new unit
     * @return a new vehicle using the settings from the builder and the given location and id.
     */
    @Override
    public Unit build(char id, HexMap map, Hex hex, Direction facing) {
        var movementFactory = new MovementFactory(map, movementPoints);
        var turret = new RealComponent("Turret", turretArmour, new NullComponent(), turretWeapons);
        var front = new RealComponent("Front", frontArmour, new NullComponent(), frontWeapons);
        var rear = new RealComponent("Rear", rearArmour, new NullComponent(), rearWeapons);
        var rightSide = new RealComponent("Right Side", rightSideArmour, new NullComponent(), rightSideWeapons);
        var leftSide = new RealComponent("Left Side", leftSideArmour, new NullComponent(), leftSideWeapons);
        return new Vehicle(id, type, hex, facing, movementFactory,
                turret, front, rear, rightSide, leftSide);
    }
}
