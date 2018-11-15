package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Components are the parts of units that have armour and weapons.
 */
public class Component implements Damageable {

    private String type;
    private int armour;
    private int maxArmour;
    private ArrayList<Weapon> weapons;

    /**
     * Construct a new component.
     *
     * @param type type of component
     * @param armour initial armour value
     * @param weapons collection of weapons in the component
     */
    public Component(String type, int armour, Collection<Weapon> weapons) {
        this.type = type;
        this.armour = armour;
        this.maxArmour = armour;
        this.weapons = new ArrayList<>(weapons);
    }

    /**
     * Get the component type.
     *
     * @return component type name
     */
    public String getType() {
        return type;
    }

    /**
     * Get current armour.
     *
     * @return current armour value
     */
    public int getArmour() {
        return armour;
    }

    /**
     * Get maximum (initial) armour value.
     *
     * @return max armour value
     */
    public int getMaxArmour() {
        return maxArmour;
    }

    /**
     * Get list of weapons in the component.
     *
     * @return list of weapons in component
     */
    public List<Weapon> getWeapons() {
        return this.weapons;
    }

    /**
     * Apply damage to the component.
     *
     * @param weapon the weapon that is doing the damage
     * @param damage amount of damage to apply
     * @return list (of length 1) of damage records
     */
    @Override
    public List<DamageRecord> applyDamage(Weapon weapon, int damage) {
        var records = new ArrayList<DamageRecord>();
        if (armour < damage) {
            damage = armour;
            armour = 0;
        } else {
            armour -= damage;
        }
        records.add(new Hit(weapon, this, damage));
        return records;
    }

}
