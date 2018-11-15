package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a weapon that can be stored in a component of a unit.  Weapons can be fired from one unit to another.
 */
public class Weapon {

    private String type;
    private WeaponRange range;
    private LineOfSightStrategy lineOfSightStrategy;
    private FiringStrategy firingStrategy;

    /**
     * Construct a new weapon, given its type and various specifications and strategies.
     *
     * @param type type of weapon
     * @param range range specifications
     * @param lineOfSightStrategy line of sight strategy (direct or indirect fire)
     * @param firingStrategy firing strategy (single shot or cluster)
     */
    public Weapon(String type, WeaponRange range,
                  LineOfSightStrategy lineOfSightStrategy, FiringStrategy firingStrategy) {
        this.type = type;
        this.range = range;
        this.lineOfSightStrategy = lineOfSightStrategy;
        this.firingStrategy = firingStrategy;
    }

    /**
     * Get the type of the weapon.
     * @return type of weapon
     */
    public String getType() {
        return type;
    }

    /**
     * Get weapon range specification.
     *
     * @return weapon range specification
     */
    public WeaponRange getRange() {
        return range;
    }

    /**
     * Get maximum damage the weapon can inflict.
     *
     * @return maximum damage of weapon
     */
    public int getDamage() {
        return firingStrategy.getDamage();
    }

    /**
     * Compute the modifier for a firing solution.
     *
     * @param attacker attacking unit
     * @param target target unit
     * @param map the map the units are on
     * @return the total to hit modifier of the firing solution
     */
    public int modifier(Unit attacker, Unit target, HexMap map) {
        int modifiers = 0;
        var hexes = attacker.getHex().lineTo(target.getHex());
        var tiles = new ArrayList<Tile>();
        for (var hex : hexes) {
            tiles.add(map.getTile(hex));
        }
        modifiers += lineOfSightStrategy.modifier(tiles);
        modifiers += range.modifier(tiles.size()-1);
        modifiers += attacker.getGunneryModifier();
        modifiers += target.getToHitModifier();
        return modifiers;
    }

    /**
     * Get the chance to hit for a given firing strategy.
     *
     * @param attacker attacking unit
     * @param target target unit
     * @param map the map the units are on
     * @return percentage chance for the weapon to hit its target
     */
    public double getHitChance(Unit attacker, Unit target, HexMap map) {
        return firingStrategy.getHitChance(modifier(attacker, target, map));
    }

    /**
     * Fire the weapon.
     *
     * @param attacker attacking unit
     * @param target target unit
     * @param map the map the units ar on
     * @return list if damage records, documenting any damage caused by the weapon
     */
    public List<DamageRecord> fire(Unit attacker, Unit target, HexMap map) {
        return firingStrategy.fire(this, target, modifier(attacker, target, map));
    }

    /**
     * Get string representation of weapon.
     *
     * @return weapon type
     */
    public String toString() {
        return type;
    }

}
