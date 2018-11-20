package io.mrshannon.hexmek;

import java.util.ArrayList;
import java.util.List;

/**
 * A NULL component. It is intended to be used as the top component for the next component field.
 */
public class NullComponent implements Component {

    @Override
    public String getType() {
        return "NULL Component";
    }

    @Override
    public boolean isDestroyed() {
        return true;
    }

    @Override
    public List<DamageRecord> applyDamage(Weapon weapon, int damage) {
        return new ArrayList<>();
    }

    @Override
    public int getArmour() {
        return 0;
    }

    @Override
    public int getMaxArmour() {
        return 0;
    }

    @Override
    public List<Weapon> getWeapons() {
        return new ArrayList<>();
    }
}
