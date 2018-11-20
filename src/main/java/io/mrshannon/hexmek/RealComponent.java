package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A real component.
 */
public class RealComponent implements Component {

    private String type;
    private int armour;
    private int maxArmour;
    private ArrayList<Weapon> weapons;
    private Component nextComponent;

    /**
     * Construct a new component.
     *
     * @param type type of component
     * @param armour initial armour value
     */
    public RealComponent(String type, int armour) {
        this.type = type;
        this.armour = armour;
        this.maxArmour = armour;
        this.nextComponent = new NullComponent();
        this.weapons = new ArrayList<>();
    }

    /**
     * Construct a new component.
     *
     * @param type type of component
     * @param armour initial armour value
     * @param nextComponent component to transfer damage to if this component is destroyed
     */
    public RealComponent(String type, int armour, Component nextComponent) {
        this.type = type;
        this.armour = armour;
        this.maxArmour = armour;
        this.nextComponent = nextComponent;
        this.weapons = new ArrayList<>();
    }

    /**
     * Construct a new component.
     *
     * @param type type of component
     * @param armour initial armour value
     * @param nextComponent component to transfer damage to if this component is destroyed
     * @param weapons collection of weapons in the component
     */
    public RealComponent(String type, int armour, Component nextComponent, Collection<Weapon> weapons) {
        this.type = type;
        this.armour = armour;
        this.maxArmour = armour;
        this.nextComponent = nextComponent;
        this.weapons = new ArrayList<>(weapons);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isDestroyed() {
        return getArmour() == 0;
    }

    @Override
    public int getArmour() {
        return armour;
    }

    @Override
    public int getMaxArmour() {
        return maxArmour;
    }

    @Override
    public List<Weapon> getWeapons() {
        return this.weapons;
    }

    /**
     * Apply damage to the component, and propagate to next component if this component is destroyed.
     *
     * @param weapon the weapon that is doing the damage
     * @param damage amount of damage to apply
     * @return list (of length 1) of damage records
     */
    @Override
    public List<DamageRecord> applyDamage(Weapon weapon, int damage) {
        var records = new ArrayList<DamageRecord>();
        if (armour < damage) {
            records.add(new Destroyed(weapon, this, armour));
            records.addAll(nextComponent.applyDamage(weapon, damage - armour));
            armour = 0;
        } else {
            records.add(new Hit(weapon, this, damage));
            armour -= damage;
        }
        return records;
    }

}
