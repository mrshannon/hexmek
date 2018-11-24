package io.mrshannon.hexmek.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A player class.
 */
public class Player {

    private String name;
    private ArrayList<Unit> units;

    public Player(String name) {
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Add a unit to the player.
     *
     * @param unit unit to add
     */
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    /**
     * Get the name of the player.
     *
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Get a list of all units that belong to the player.
     *
     * @return list of units
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * Get a list of all units that are not destroyed belonging to the player.
     *
     * @return list of not destroyed units
     */
    public List<Unit> getAliveUnits() {
        return units.stream().filter(u -> !u.isDestroyed()).collect(Collectors.toList());
    }

    /**
     * Get a list of all destroyed units belonging to the player.
     *
     * @return list of destroyed units
     */
    public List<Unit> getDestroyedUnits() {
        return units.stream().filter(Damageable::isDestroyed).collect(Collectors.toList());
    }

    /**
     * Get a list of all units that can move.
     *
     * @return list of units that can move
     */
    public List<Unit> getMovableUnits() {
        return units.stream().filter(Movable::canMove).collect(Collectors.toList());
    }

    /**
     * Get a list of all units that can fire at least one weapon.
     *
     * @return list of units that can fire
     */
    public List<Unit> getFireableUnits() { // TODO: Find places where this should have been used.
        return units.stream().filter(Unit::canFire).collect(Collectors.toList());
    }

    /**
     * Returns true if the player is still in the game.  A player is no longer in the game when all their units are
     * destroyed.
     *
     * @return true if the player has any units that have not been destroyed
     */
    public boolean inGame() {
        return !units.stream().allMatch(Damageable::isDestroyed);
    }

}
