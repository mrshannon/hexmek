package io.mrshannon.hexmek;

import java.util.List;

/**
 * Components are the parts of units that have armour and weapons.
 */
public interface Component extends Damageable {

    /**
     * Get the component type.
     *
     * @return component type name
     */
    String getType();

    /**
     * Determine if component is destroyed
     *
     * @return true if component is destroyed, false otherwise
     */
    @Override
    boolean isDestroyed();

    /**
     * Get current armour.
     *
     * @return current armour value
     */
    int getArmour();

    /**
     * Get maximum (initial) armour value.
     *
     * @return max armour value
     */
    int getMaxArmour();

    /**
     * Get list of weapons in the component.
     *
     * @return list of weapons in component
     */
    List<Weapon> getWeapons();

}
