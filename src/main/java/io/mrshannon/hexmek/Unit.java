package io.mrshannon.hexmek;

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
     * Determine if the unit is still alive.
     *
     * @return true if the unit is alive
     */
    boolean isAlive();

}
