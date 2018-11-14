package io.mrshannon.hexmek;

import java.util.List;

/**
 * An interface for damageable objects.
 */
public interface Damageable {

    /**
     * Apply damage, getting a damage record in response.
     *
     * @see io.mrshannon.hexmek.DamageRecord
     *
     * @param weapon the weapon that is doing the damage
     * @param damage amount of damage to apply
     * @return damage records
     */
    List<DamageRecord> applyDamage(Weapon weapon, int damage);

}
