package io.mrshannon.hexmek.controllers;

import io.mrshannon.hexmek.models.DamageRecord;
import io.mrshannon.hexmek.models.Unit;
import io.mrshannon.hexmek.views.DamageRecordView;
import io.mrshannon.hexmek.views.FireSelectView;
import io.mrshannon.hexmek.views.MessageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fire controller, allows the player to fire weapons from one unit at another.
 */
public class FireController extends GameController {

    private CombatStageController returnTo;
    private Unit attacker;
    private Unit target;

    /**
     * Construct a fire controller.
     *
     * @param other {@link CombatStageController} to copy the game state from and return to after firing is over
     * @param attacker attacking unit
     * @param target target unit
     */
    public FireController(CombatStageController other, Unit attacker, Unit target) {
        super(other);
        this.returnTo = other;
        this.attacker = attacker;
        this.target = target;
    }

    /**
     * Run the controller, this displays weapon options and asks the player which weapons to fire and then fires them,
     * reporting the damage.
     *
     * @return the original {@link CombatStageController}
     * @throws IOException
     */
    @Override
    public Controller run() throws IOException {
        var weapons = attacker.getWeapons();
        (new FireSelectView(getMap(), attacker, target, weapons)).render();
        var line = readLine();
        if (line.isEmpty()) {
            return returnTo;
        }
        int[] weaponIds;
        try {
            weaponIds = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            (new MessageView(String.format("Invalid weapon list '%s'.", line))).render();
            return this;
        }
        var records = new ArrayList<DamageRecord>();
        for (int i : weaponIds) {
            var weapon = weapons.get(i-1);
            records.addAll(weapon.fire(attacker, target, getMap()));
        }
        (new DamageRecordView(records)).render();
        returnTo.takeTurn(attacker);
        return returnTo;
    }


}
