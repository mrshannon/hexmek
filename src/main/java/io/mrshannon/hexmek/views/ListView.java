package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.Player;
import io.mrshannon.hexmek.models.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.Math.max;

public class ListView implements View {

    private ArrayList<Player> players;

    public ListView(Player... players) {
        this.players = new ArrayList<Player>();
        this.players.addAll(Arrays.asList(players));
    }

    public ListView(Collection<Player> players) {
        this.players = new ArrayList<Player>();
        this.players.addAll(players);
    }

    @Override
    public void render() {
        printHeader();
        printSeparator();
        printLines();
    }

    private void printHeader() {
        String format = "%-" + getMaxPlayerLength() + "s   %-" + getMaxTypeLength() + "s   Health      Grid   Facing";
        System.out.println(String.format(format, "Player", "Type"));
    }

    private void printSeparator() {
        var builder = new StringBuilder();
        for (int i = 0; i < getMaxPlayerLength(); ++i) {
            builder.append('-');
        }
        builder.append("   ");
        for (int i = 0; i < getMaxTypeLength(); ++i) {
            builder.append('-');
        }
        builder.append("   ---------   ----   ----------");
        System.out.println(builder.toString());
    }

    private void printLines() {
        for (var player : players) {
            for (var unit : player.getUnits()) {
                printLine(player.getName(), unit);
            }
        }
    }

    private void printLine(String player, Unit unit) {
        String health;
        if (unit.isDestroyed()) {
            health = "DESTROYED";
        } else {
            health = String.format("%5d%%", (int) (unit.getArmourPercent()*100));
        }
        String format = "%-" + getMaxPlayerLength() + "s   %-" + getMaxTypeLength() + "s   %9s   %02d%02d   %s";
        System.out.println(String.format(format, player, unit.getType(), health,
                unit.getHex().getColumn(), unit.getHex().getRow(), unit.getFacing().toString()));
    }

    private int getMaxPlayerLength() {
        int length = 0;
        for (var player : players) {
            length = max(length, player.getName().length());
        }
        return length;
    }

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
