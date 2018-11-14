package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

public class ClusterFiringStrategy implements FiringStrategy {

    private int damage;
    private int clusterSize;
    private DiceRoller dice;

    /**
     * Construct a new cluster (multiple shot) firing strategy.
     *
     * @param damage amount of damage per projectile
     * @param clusterSize amount of projectiles in the cluster
     */
    public ClusterFiringStrategy(int damage, int clusterSize) {
        this.damage = damage;
        this.clusterSize = clusterSize;
        this.dice = new DiceRoller(new Die(6), new Die(6));
    }

    @Override
    public int getDamage() {
        return damage * clusterSize;
    }

    @Override
    public double getHitChance(int modifiers) {
        return dice.atLeastProbability(modifiers);
    }

    @Override
    public List<DamageRecord> fire(Weapon weapon, Unit target, int modifiers) {
        var records = new ArrayList<DamageRecord>();
        for (int i = 1; i <= clusterSize; ++i) {
            dice.roll();
            if (dice.getTotal() >= modifiers) {
                records.addAll(target.applyDamage(weapon, damage));
            }
        }
        return records;
    }
}
