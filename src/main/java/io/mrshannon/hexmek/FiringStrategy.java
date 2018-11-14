package io.mrshannon.hexmek;

/**
 * A firing strategy is used to allow some weapons to be single shot and others to fire in clusters.
 */
public interface FiringStrategy {

    /**
     * Get the maximum amount of damage that can be inflicted.
     *
     * @return maximum possible damage
     */
    int getDamage();

    /**
     * Get the chance that the shot will hit.  This is the chance of at least one shot hitting for cluster weapons.
     *
     * @param modifiers sum of all modifiers (gunnery, attack, and to hit)
     * @return percentage chance to hit with the given {@code modifiers}
     */
    double getHitChance(int modifiers);

    /**
     * Fire the weapon from one unit to another.
     *
     * @param weapon weapon to fire
     * @param target target unit
     * @param modifiers sum of all modifiers (gunnery, attack, and to hit)
     * @return iterable of damage records, will only have one element for non cluster (normal) firing strategy
     */
    Iterable<DamageRecord> fire(Weapon weapon, Unit target, int modifiers);

}
