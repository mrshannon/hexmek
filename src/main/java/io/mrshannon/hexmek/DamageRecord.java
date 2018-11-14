package io.mrshannon.hexmek;

/**
 * Records damage from a weapon to a component.
 */
public interface DamageRecord {

    /**
     * Get the weapon that damaged the component.
     *
     * @return weapon that inflicted damage
     */
    Weapon getWeapon();

    /**
     * Get the component that was damaged.
     *
     * @return damaged component
     */
    Component getComponent();

    /**
     * Get the amount of damage done to the component.
     *
     * @return amount of damage done
     */
    int getDamage();

}
