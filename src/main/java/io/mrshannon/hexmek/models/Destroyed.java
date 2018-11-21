package io.mrshannon.hexmek.models;

/**
 * Record a destroyed component.
 */
public class Destroyed extends Hit {

    /**
     * Construct a damage record that is a hit that destroys the component.
     *
     * @param weapon    weapon that inflicted the damage
     * @param component component that was damage
     * @param damage    amount of damage done
     */
    public Destroyed(Weapon weapon, Component component, int damage) {
        super(weapon, component, damage);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\n%s destroyed!", getComponent().getType());
    }

}
