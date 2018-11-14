package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * Normal firing strategy, single shot weapons.
 */
public class NormalFiringStrategy implements FiringStrategy {

    private int damage;
    private DiceRoller dice;

    /**
     * Construct a new normal (single shot) firing strategy.
     *
     * @param damage amount of damage the weapon does
     */
    public NormalFiringStrategy(int damage) {
        this.damage = damage;
        this.dice = new DiceRoller(new Die(6), new Die(6));
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public double getHitChance(int modifiers) {
        return dice.atLeastProbability(modifiers);
    }

    @Override
    public List<DamageRecord> fire(Weapon weapon, Unit target, int modifiers) {
        dice.roll();
        if (dice.getTotal() >= modifiers) {
            return target.applyDamage(weapon, damage);
        }
        var records = new ArrayList<DamageRecord>();
        records.add(new Miss(weapon));
        return records;
    }

}
