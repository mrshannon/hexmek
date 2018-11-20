package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * A ground vehicle unit.
 */
public class Vehicle extends AbstractUnit {

    private DiceRoller dice;
    private RealComponent turret;
    private RealComponent front;
    private RealComponent rear;
    private RealComponent rightSide;
    private RealComponent leftSide;

    /**
     * Construct a new vehicle.
     *
     * @param id              identification of unit, should be unique to each unit
     * @param type            type of unit
     * @param hex             starting hex coordinate
     * @param facing          initial facing direction
     * @param movementFactory factory to use to construct movement strategies
     * @param turret          turret component
     * @param front           front component
     * @param rear            rear component
     * @param rightSide       right side component
     * @param leftSide        left side component
     */
    public Vehicle(char id, String type, Hex hex, Direction facing, MovementFactory movementFactory,
                   RealComponent turret, RealComponent front, RealComponent rear,
                   RealComponent rightSide, RealComponent leftSide) {
        super(id, type, hex, facing, movementFactory);
        this.turret = turret;
        this.front = front;
        this.rear = rear;
        this.rightSide = rightSide;
        this.leftSide = leftSide;
        this.dice = new DiceRoller(new Die(6), new Die(6));
    }

    /**
     * Get the amount of turret armour.
     *
     * @return turret armour points
     */
    public int getTurretArmour() {
        return turret.getArmour();
    }

    /**
     * Get the amount of front armour.
     *
     * @return turret front points
     */
    public int getFrontArmour() {
        return front.getArmour();
    }

    /**
     * Get the amount of rear armour.
     *
     * @return turret rear points
     */
    public int getRearArmour() {
        return rear.getArmour();
    }

    /**
     * Get the amount of right side armour.
     *
     * @return turret right side points
     */
    public int getRightSideArmour() {
        return rightSide.getArmour();
    }

    /**
     * Get the amount of left side armour.
     *
     * @return turret left side points
     */
    public int getLeftSideArmour() {
        return leftSide.getArmour();
    }

    /**
     * Determine if the vehicle is destroyed.  If any component is destroyed the entire vehicle is destroyed.
     *
     * @return true if vehicle is destroyed
     */
    @Override
    public boolean isDestroyed() {
        return turret.isDestroyed() || front.isDestroyed() || rear.isDestroyed() ||
                leftSide.isDestroyed() || rightSide.isDestroyed();
    }

    @Override
    public List<Component> getComponents() {
        var components = new ArrayList<Component>();
        components.add(turret);
        components.add(front);
        components.add(rear);
        components.add(rightSide);
        components.add(leftSide);
        return components;
    }

    /**
     * Apply damage to a random component of the unit.
     *
     * @param weapon the weapon that is doing the damage
     * @param damage amount of damage to apply
     * @return list of damage records
     */
    @Override
    public List<DamageRecord> applyDamage(Weapon weapon, int damage) {
        dice.roll();
        switch (dice.getTotal()) {
            case 2:
                return rear.applyDamage(weapon, damage);
            case 3:
                damageMobility();
                return front.applyDamage(weapon, damage);
            case 4:
                damageMobility();
                return front.applyDamage(weapon, damage);
            case 5:
                damageMobility();
                return rightSide.applyDamage(weapon, damage);
            case 6:
                return front.applyDamage(weapon, damage);
            case 7:
                return front.applyDamage(weapon, damage);
            case 8:
                return front.applyDamage(weapon, damage);
            case 9:
                damageMobility();
                return leftSide.applyDamage(weapon, damage);
            case 10:
                return turret.applyDamage(weapon, damage);
            case 11:
                return turret.applyDamage(weapon, damage);
            case 12:
                return rear.applyDamage(weapon, damage);
        }
        throw new RuntimeException("The code is broken!");
    }

}
