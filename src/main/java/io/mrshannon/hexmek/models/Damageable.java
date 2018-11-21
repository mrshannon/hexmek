package io.mrshannon.hexmek.models;

import java.util.List;

/**
 * An interface for damageable objects.
 */
public interface Damageable {

    /**
     * Determine if object is destroyed.
     *
     * @return true if object is destroyed, false if not destroyed
     */
    boolean isDestroyed();

    /**
     * Apply damage, getting a damage record in response.
     *
     * @see DamageRecord
     *
     * @param weapon the weapon that is doing the damage
     * @param damage amount of damage to apply
     * @return damage records
     */
    List<DamageRecord> applyDamage(Weapon weapon, int damage);

}
