package io.mrshannon.hexmek;

/**
 * Record a miss.
 */
public class Miss implements DamageRecord {

    private Weapon weapon;

    /**
     * Construct a damage record that is a miss.
     *
     * @param weapon weapon that inflicted the damage
     */
    public Miss(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public Component getComponent() {
        return null; // TODO: change this to a real component
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s missed.", weapon.getName());
    }

}
