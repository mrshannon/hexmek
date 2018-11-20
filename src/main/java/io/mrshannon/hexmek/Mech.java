package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * A walking mech unit.
 */
public class Mech extends AbstractUnit {

    private DiceRoller dice;
    private RealComponent head;
    private RealComponent centerTorso;
    private RealComponent rightTorso;
    private RealComponent leftTorso;
    private RealComponent rightArm;
    private RealComponent leftArm;
    private RealComponent rightLeg;
    private RealComponent leftLeg;

    /**
     * Construct a new mech.
     *
     * @param id              identification of unit, should be unique to each unit
     * @param type            type of unit
     * @param hex             starting hex coordinate
     * @param facing          initial facing direction
     * @param movementFactory factory to use to construct movement strategies
     * @param head            head component
     * @param centerTorso     center torso component
     * @param rightTorso      right torso component
     * @param leftTorso       left torso component
     * @param rightArm        right arm component
     * @param leftArm         left arm component
     * @param rightLeg        right leg component
     * @param leftLeg         left leg component
     */
    public Mech(char id, String type, Hex hex, Direction facing, MovementFactory movementFactory,
                RealComponent head, RealComponent centerTorso, RealComponent rightTorso, RealComponent leftTorso,
                RealComponent rightArm, RealComponent leftArm, RealComponent rightLeg, RealComponent leftLeg) {
        super(id, type, hex, facing, movementFactory);
        this.head = head;
        this.centerTorso = centerTorso;
        this.rightTorso = rightTorso;
        this.leftTorso = leftTorso;
        this.rightArm = rightArm;
        this.leftArm = leftArm;
        this.rightLeg = rightLeg;
        this.leftLeg = leftLeg;
        this.dice = new DiceRoller(new Die(6), new Die(6));
    }

    /**
     * Get the amount of head armour.
     *
     * @return head armour points
     */
    public int getHeadArmour() {
        return head.getArmour();
    }

    /**
     * Get the amount of center torso armour.
     *
     * @return center torso armour points
     */
    public int getCenterTorsoArmour() {
        return centerTorso.getArmour();
    }

    /**
     * Get the amount of right torso armour.
     *
     * @return right torso armour points
     */
    public int getRightTorsoArmour() {
        return rightTorso.getArmour();
    }

    /**
     * Get the amount of left torso armour.
     *
     * @return left torso armour points
     */
    public int getLeftTorsoArmour() {
        return leftTorso.getArmour();
    }

    /**
     * Get the amount of right arm armour.
     *
     * @return right arm armour points
     */
    public int getRightArmArmour() {
        return rightArm.getArmour();
    }

    /**
     * Get the amount of left arm armour.
     *
     * @return left arm armour points
     */
    public int getLeftArmArmour() {
        return leftArm.getArmour();
    }

    /**
     * Get the amount of right leg armour.
     *
     * @return right leg armour points
     */
    public int getRightLegArmour() {
        return rightLeg.getArmour();
    }

    /**
     * Get the amount of left leg armour.
     *
     * @return left leg armour points
     */
    public int getLeftLegArmour() {
        return leftLeg.getArmour();
    }

    /**
     * Determine if the mech is destroyed.  If the head or the center torso is destroyed the entire mech is destroyed.
     *
     * @return true if the mech is destroyed, false otherwise
     */
    @Override
    public boolean isDestroyed() {
        return head.isDestroyed() || centerTorso.isDestroyed();
    }

    @Override
    public List<Component> getComponents() {
        var components = new ArrayList<Component>();
        components.add(head);
        components.add(centerTorso);
        components.add(rightTorso);
        components.add(leftTorso);
        components.add(rightArm);
        components.add(leftArm);
        components.add(rightLeg);
        components.add(leftLeg);
        return components;
    }

    /**
     * Apply damage to a random component of the unit.
     *
     * @param weapon the weapon that is doing the damage
     * @param damage amount of damage to apply
     * @return list of damage records
     */
    private List<DamageRecord> applyComponentDamage(Weapon weapon, int damage) {
        dice.roll();
        switch (dice.getTotal()) {
            case 2:
                return centerTorso.applyDamage(weapon, damage);
            case 3:
                return rightArm.applyDamage(weapon, damage);
            case 4:
                return rightArm.applyDamage(weapon, damage);
            case 5:
                return rightLeg.applyDamage(weapon, damage);
            case 6:
                return rightTorso.applyDamage(weapon, damage);
            case 7:
                return centerTorso.applyDamage(weapon, damage);
            case 8:
                return leftTorso.applyDamage(weapon, damage);
            case 9:
                return leftLeg.applyDamage(weapon, damage);
            case 10:
                return leftArm.applyDamage(weapon, damage);
            case 11:
                return leftArm.applyDamage(weapon, damage);
            case 12:
                return head.applyDamage(weapon, damage);
        }
        throw new RuntimeException("The code is broken!");
    }

    /**
     * Apply damage to mech, propagating to other components when one is destroyed.  If either leg is destroyed then
     * mobility is destroyed.
     *
     * @param weapon the weapon that is doing the damage
     * @param damage amount of damage to apply
     * @return list of damage records
     */
    @Override
    public List<DamageRecord> applyDamage(Weapon weapon, int damage) {
        var records = applyComponentDamage(weapon, damage);
        if (rightLeg.isDestroyed() || leftLeg.isDestroyed()) {
            destroyMobility();
        }
        return records;
    }

}
