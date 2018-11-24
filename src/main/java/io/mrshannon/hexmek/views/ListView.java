package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.Player;
import io.mrshannon.hexmek.models.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.Math.max;

/**
 * A list view that displays a list of all the units in the game.
 */
public class ListView implements View {

    private ArrayList<Player> players;

    /**
     * Create a list view.
     *
     * @param players the players whose units will be listed
     */
    public ListView(Player... players) {
        this.players = new ArrayList<Player>();
        this.players.addAll(Arrays.asList(players));
    }

    /**
     * Create a list view.
     *
     * @param players the players whose units will be listed
     */
    public ListView(Collection<Player> players) {
        this.players = new ArrayList<Player>();
        this.players.addAll(players);
    }

    /**
     * Render the list of units.
     */
    @Override
    public void render() {
        System.out.println();
        printHeader();
        printSeparator();
        printLines();
    }

    /**
     * Print the header line.
     */
    private void printHeader() {
        String format = "%-" + getMaxPlayerLength() + "s   ID   %-" + getMaxTypeLength() + "s   Health      Grid   Facing";
        System.out.println(String.format(format, "Player", "Type"));
    }

    /**
     * Print the separation line, to separate the header from the entries.
     */
    private void printSeparator() {
        var builder = new StringBuilder();
        for (int i = 0; i < getMaxPlayerLength(); ++i) {
            builder.append('-');
        }
        builder.append("   --   ");
        for (int i = 0; i < getMaxTypeLength(); ++i) {
            builder.append('-');
        }
        builder.append("   ---------   ----   ----------");
        System.out.println(builder.toString());
    }

    /**
     * Print the lines of unit status.
     */
    private void printLines() {
        for (var player : players) {
            for (var unit : player.getUnits()) {
                printLine(player.getName(), unit);
            }
        }
    }

    /**
     * Print a single line of unit status.
     *
     * @param player name of the player that owns the unit
     * @param unit the unit to print the status for
     */
    private void printLine(String player, Unit unit) {
        String health;
        if (unit.isDestroyed()) {
            health = "DESTROYED";
        } else {
            health = String.format("%5d%%", (int) (unit.getArmourPercent()*100));
        }
        String format = "%-" + getMaxPlayerLength() + "s    %c   %-" + getMaxTypeLength() + "s   %9s   %02d%02d   %s";
        System.out.println(String.format(format, player, unit.getId(), unit.getType(), health,
                unit.getHex().getColumn(), unit.getHex().getRow(), unit.getFacing().toString()));
    }

    /**
     * Get the maximum length of a player's name.
     *
     * @return max player name length
     */
    private int getMaxPlayerLength() {
        int length = 0;
        for (var player : players) {
            length = max(length, player.getName().length());
        }
        return length;
    }

    /**
     * Get the maximum length of the unit type.
     *
     * @return max unit type length
     */
    private int getMaxTypeLength() {
        int length = 0;
        for (var player : players) {
            for (var unit : player.getUnits()) {
                length = max(length, unit.getType().length());
            }
        }
        return length;
    }

}
