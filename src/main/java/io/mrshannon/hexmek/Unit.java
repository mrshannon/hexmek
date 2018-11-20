package io.mrshannon.hexmek;

import java.util.List;

/**
 * Unit interface.
 *
 * @see io.mrshannon.hexmek.Movable
 * @see io.mrshannon.hexmek.Damageable
 * @see io.mrshannon.hexmek.Originator
 */
public interface Unit extends Movable, Damageable, Originator {

    /**
     * Get the ID of the unit.
     *
     * @return unique (per game) unit ID
     */
    char getId();

    /**
     * Get the type of the unit.
     *
     * @return get unit type
     */
    String getType();

    /**
     * Get the current to-hit modifier.
     *
     * @return current to hit modifier
     */
    int getToHitModifier();

    /**
     * Get the current gunnery/attack modifier.
     *
     * @return current attack modifier
     */
    int getGunneryModifier();

    /**
     * Get a list of components in the unit.
     *
     * @return list of unit components
     */
    List<Component> getComponents();

    /**
     * Get list of the unit's weapons.
     *
     * @return list of weapons
     */
    List<Weapon> getWeapons();

    /**
     * Get the current armour points of the unit.
     *
     * @return current armour points
     */
    int getArmour();

    /**
     * Get the maximum armour points of the unit.
     *
     * @return maximum armour points
     */
    int getMaxArmour();

    /**
     * Get current armour percentage of the unit (unit health).
     *
     * @return armour percentage
     */
    default double getArmourPercent() {
        return ((double) getArmour())/((double) getMaxArmour());
    }

}
