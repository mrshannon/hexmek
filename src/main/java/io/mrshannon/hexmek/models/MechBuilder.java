package io.mrshannon.hexmek.models;

import java.util.ArrayList;

/**
 * A mech builder.  This implements the builder pattern to assist in making mech units.
 */
public class MechBuilder implements UnitBuilder {

    private String type;
    private int movementPoints;

    private int headArmour;
    private int centerTorsoArmour;
    private int rightTorsoArmour;
    private int leftTorsoArmour;
    private int rightArmArmour;
    private int leftArmArmour;
    private int rightLegArmour;
    private int leftLegArmour;

    private ArrayList<Weapon> headWeapons;
    private ArrayList<Weapon> centerTorsoWeapons;
    private ArrayList<Weapon> rightTorsoWeapons;
    private ArrayList<Weapon> leftTorsoWeapons;
    private ArrayList<Weapon> rightArmWeapons;
    private ArrayList<Weapon> leftArmWeapons;

    /**
     * Construct a new mech builder.
     *
     * @param type the type of the mech to build
     * @param movementPoints cruising (walking) movement points for the mech
     */
    public MechBuilder(String type, int movementPoints) {
        this.type = type;
        this.movementPoints = movementPoints;

        this.headArmour = 1;
        this.centerTorsoArmour = 1;
        this.leftTorsoArmour = 1;
        this.rightTorsoArmour = 1;
        this.leftArmArmour = 1;
        this.rightArmArmour = 1;
        this.leftLegArmour = 1;
        this.rightLegArmour = 1;

        this.headWeapons = new ArrayList<>();
        this.centerTorsoWeapons = new ArrayList<>();
        this.rightTorsoWeapons = new ArrayList<>();
        this.leftTorsoWeapons = new ArrayList<>();
        this.rightArmWeapons = new ArrayList<>();
        this.leftArmWeapons = new ArrayList<>();
    }

    /**
     * Set the head armour value.
     *
     * @param armour head armour points
     * @return the builder
     */
    public MechBuilder head(int armour) {
        headArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the head.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public MechBuilder head(Weapon weapon) {
        headWeapons.add(weapon);
        return this;
    }

    /**
     * Set the center torso armour value.
     *
     * @param armour center torso armour points
     * @return the builder
     */
    public MechBuilder centerTorso(int armour) {
        centerTorsoArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the center torso.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public MechBuilder centerTorso(Weapon weapon) {
        centerTorsoWeapons.add(weapon);
        return this;
    }

    /**
     * Set the right torso armour value.
     *
     * @param armour right torso armour points
     * @return the builder
     */
    public MechBuilder rightTorso(int armour) {
        rightTorsoArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the right torso.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public MechBuilder rightTorso(Weapon weapon) {
        rightTorsoWeapons.add(weapon);
        return this;
    }

    /**
     * Set the left torso armour value.
     *
     * @param armour left torso armour points
     * @return the builder
     */
    public MechBuilder leftTorso(int armour) {
        leftTorsoArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the left torso.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public MechBuilder leftTorso(Weapon weapon) {
        leftTorsoWeapons.add(weapon);
        return this;
    }

    /**
     * Set the right arm armour value.
     *
     * @param armour right arm armour points
     * @return the builder
     */
    public MechBuilder rightArm(int armour) {
        rightArmArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the right arm.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public MechBuilder rightArm(Weapon weapon) {
        rightArmWeapons.add(weapon);
        return this;
    }

    /**
     * Set the left arm armour value.
     *
     * @param armour left arm armour points
     * @return the builder
     */
    public MechBuilder leftArm(int armour) {
        leftArmArmour = armour;
        return this;
    }

    /**
     * Add a weapon to the left arm.
     *
     * @param weapon weapon to add
     * @return the builder
     */
    public MechBuilder leftArm(Weapon weapon) {
        leftArmWeapons.add(weapon);
        return this;
    }

    /**
     * Set the right leg armour value.
     *
     * @param armour right leg armour points
     * @return the builder
     */
    public MechBuilder rightLeg(int armour) {
        rightLegArmour = armour;
        return this;
    }

    /**
     * Set the left leg armour value.
     *
     * @param armour left leg armour points
     * @return the builder
     */
    public MechBuilder leftLeg(int armour) {
        leftLegArmour = armour;
        return this;
    }

    /**
     * Build the mech.  This uses all of the settings set on the builder to this point.
     *
     * <br>
     *
     * Multiple mech's can be built from a single builder.
     *
     * @param id unique, single character id of unit
     * @param map map to place the unit on
     * @param hex location to put the unit
     * @param facing the facing direction of the new unit
     * @return a new mech using the settings from the builder and the given location and id.
     */
    @Override
    public Unit build(char id, HexMap map, Hex hex, Direction facing) {
        var movementFactory = new MovementFactory(map, movementPoints);
        var head = new RealComponent("Head", headArmour, new NullComponent(), headWeapons);
        var centerTorso = new RealComponent(
                "Center Torso", centerTorsoArmour, new NullComponent(), centerTorsoWeapons);
        var rightTorso = new RealComponent("Right Torso", rightTorsoArmour, centerTorso, rightTorsoWeapons);
        var leftTorso = new RealComponent("Left Torso", leftTorsoArmour, centerTorso, leftTorsoWeapons);
        var rightArm = new RealComponent("Right Arm", rightArmArmour, rightTorso, rightArmWeapons);
        var leftArm = new RealComponent("Left Arm", leftArmArmour, leftTorso, leftArmWeapons);
        var rightLeg = new RealComponent("Right Leg", rightLegArmour, rightTorso);
        var leftLeg = new RealComponent("Left Leg", leftLegArmour, leftTorso);
        return new Mech(type, id, hex, facing, movementFactory,
                head, centerTorso, rightTorso, leftTorso, rightArm, leftArm, rightLeg, leftLeg);
    }

}
