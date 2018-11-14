package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * Cluster firing strategy.  This is for weapons that fire multiple projectiles each time they are fired.
 */
public class ClusterFiringStrategy implements FiringStrategy {

    /**
     * Cluster hits table, maps 2D6 value and weapon size to number of hits.
     */
    private final int clusterTable[][] = {
            { 1,  1,  1,  1,  1,  1,  2,  2,  2,  2,  2}, // 2
            { 1,  1,  1,  2,  2,  2,  2,  2,  3,  3,  3}, // 3
            { 1,  2,  2,  2,  2,  3,  3,  3,  3,  4,  4}, // 4
            { 1,  2,  2,  3,  3,  3,  3,  4 , 4,  5,  5}, // 5
            { 2,  2,  3,  3,  4,  4,  4,  5,  5,  6,  6}, // 6
            { 2,  2,  3,  4,  4,  4,  4,  6,  6,  7,  7}, // 7
            { 3,  3,  4,  4,  5,  5,  5,  6,  6,  8,  8}, // 8
            { 3,  3,  4,  5,  5,  5,  5,  7,  7,  9,  9}, // 9
            { 3,  3,  4,  6,  6,  6,  6,  8,  8, 10, 10}, // 10
            { 4,  4,  5,  7,  7,  7,  7,  9,  9, 11, 11}, // 11
            { 4,  4,  5,  8,  8,  8,  8, 10, 10, 12, 12}, // 12
            { 4,  4,  5,  8,  8,  8,  8, 11, 11, 13, 13}, // 13
            { 5,  5,  6,  8,  8,  8,  8, 11, 11, 14, 14}, // 14
            { 5,  5,  6,  9,  9,  9,  9, 12, 12, 15, 15}, // 15
            { 5,  5,  7, 10, 10, 10, 10, 13, 13, 16, 16}, // 16
            { 5,  5,  7, 10, 10, 10, 10, 13, 13, 16, 16}, // 17
            { 6,  6,  8, 11, 11, 11, 11, 14, 14, 18, 18}, // 18
            { 6,  6,  8, 11, 11, 11, 11, 15, 15, 19, 19}, // 19
            { 6,  6,  8, 11, 11, 11, 11, 15, 15, 19, 19}, // 20
            { 7,  7,  9, 13, 13, 13, 13, 17, 17, 21, 21}, // 21
            { 7,  7,  9, 13, 13, 13, 13, 17, 17, 21, 21}, // 22
            { 7,  7, 10, 15, 15, 15, 15, 19, 19, 23, 23}, // 23
            { 8,  8, 10, 16, 16, 16, 16, 20, 20, 24, 24}, // 24
            { 8,  8, 10, 16, 16, 16, 16, 21, 21, 25, 25}, // 25
            { 9,  9, 11, 17, 17, 17, 17, 21, 21, 26, 26}, // 26
            { 9,  9, 11, 17, 17, 17, 17, 22, 22, 27, 27}, // 27
            { 9,  9, 11, 17, 17, 17, 17, 23, 23, 28, 28}, // 28
            {10, 10, 12, 18, 18, 18, 18, 23, 23, 29, 29}, // 29
            {10, 10, 12, 18, 18, 18, 18, 24, 24, 30, 30}, // 30
    };

    private int damage;
    private int clusterSize;
    private DiceRoller dice;

    /**
     * Construct a new cluster (multiple shot) firing strategy.
     *
     * @param damage amount of damage per projectile
     * @param clusterSize amount of projectiles in the cluster, between 2 and 30 (inclusive)
     */
    public ClusterFiringStrategy(int damage, int clusterSize) {
        if (clusterSize < 2) {
            throw new IllegalArgumentException("Cluster size must be at least 2.");
        }
        if (clusterSize > 30) {
            throw new IllegalArgumentException("Cluster size cannot be larger than 30.");
        }
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

    /**
     * Computer the number of projectiles of the cluster that will hit for a given dice roll.
     *
     * @param diceTotal the total of 2D6
     * @return number of projectiles that will hit
     */
    public int clusterHits(int diceTotal) {
        if (diceTotal < 2) {
            throw new IllegalArgumentException("Dice total must be at least 2.");
        }
        if (diceTotal > 12) {
            throw new IllegalArgumentException("Dice total cannot exceed 12.");
        }
        return clusterTable[clusterSize-2][diceTotal-2];
    }

    /**
     * Fire the cluster weapon, with 100% chance.
     *
     * @param weapon weapon to fire
     * @param target target unit
     * @return list of damage records, one for each projectile hit in the cluster
     */
    private List<DamageRecord> fireClusters(Weapon weapon, Unit target) {
        var records = new ArrayList<DamageRecord>();
        dice.roll();
        int numHits = clusterHits(dice.getTotal());
        for (int i = 1; i <= numHits; ++i) {
            records.addAll(target.applyDamage(weapon, damage));
        }
        return records;
    }

    @Override
    public List<DamageRecord> fire(Weapon weapon, Unit target, int modifiers) {
        dice.roll();
        if (dice.getTotal() >= modifiers) {
            return fireClusters(weapon, target);
        }
        var records = new ArrayList<DamageRecord>();
        records.add(new Miss(weapon));
        return records;
    }
}
