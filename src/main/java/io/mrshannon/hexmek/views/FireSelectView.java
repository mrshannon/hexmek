package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.HexMap;
import io.mrshannon.hexmek.models.Unit;
import io.mrshannon.hexmek.models.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Fire select view, allowing the user to select which weapons to fire.
 */
public class FireSelectView implements View {

    private HexMap map;
    private Unit attacker;
    private Unit target;
    private ArrayList<Weapon> weaponChoices;

    /**
     * Construct a new fire select view
     * @param map map the units are on
     * @param attacker attacking unit
     * @param target target unit
     * @param weaponChoices available weapon choices
     */
    public FireSelectView(HexMap map, Unit attacker, Unit target, List<Weapon> weaponChoices) {
        this.map = map;
        this.attacker = attacker;
        this.target = target;
        this.weaponChoices = new ArrayList<>(weaponChoices);
    }

    /**
     * Render the view.
     */
    @Override
    public void render() {
        printMessageLine();
        System.out.println();
        printHeader();
        printSeparator();
        printLines();
        System.out.println();
        printPrompt();
    }

    /**
     * Print the message line.
     */
    private void printMessageLine() {
        System.out.printf("%s (%c) is firing at %s (%c).\n",
                attacker.getType(), attacker.getId(), target.getType(), target.getId());
    }

    /**
     * Print table header.
     */
    private void printHeader() {
        System.out.println("#   Weapon Type     Damage   To-Hit");
    }

    /**
     * Print table separator.
     */
    private void printSeparator() {
        System.out.println("-   -------------   ------   ------");
    }

    /**
     * Print the lines of the weapon choice table.
     */
    private void printLines() {
        for (int i = 0; i < weaponChoices.size(); ++i) {
            var weapon = weaponChoices.get(i);
            System.out.printf("%d   %-13s   %6d   %5d%%\n",
                    i + 1, weapon.getType(), weapon.getDamage(),
                    (int) (100*weapon.getHitChance(attacker, target, map)));
        }
    }

    /**
     * Print the prompt for weapon choice.
     */
    private void printPrompt() {
        System.out.print("List weapon #'s to fire (leave blank to reset): ");
    }

}
