package io.mrshannon.hexmek;

/**
 * Record a hit.
 */
public class Hit implements DamageRecord {

    private Weapon weapon;
    private int damage;
    private Component component;

    /**
     * Construct a damage record that is a hit.
     *
     * @param weapon weapon that inflicted the damage
     * @param component component that was damage
     * @param damage amount of damage done
     */
    public Hit(Weapon weapon, Component component, int damage) {
        this.weapon = weapon;
        this.damage = damage;
        this.component = component;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public Component getComponent() {
        return component;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return String.format("%s hit %s for %d damage.", weapon.getType(), component.getType(), damage);
    }

}
