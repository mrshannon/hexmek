package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MapView implements View {

    private HexMap map;
    private ArrayList<Unit> units;
    private HashMap<Hex, Unit> unitCache;

    public MapView(HexMap map) {
        this.map = map;
        this.units = new ArrayList<>();
        this.unitCache = new HashMap<>();
    }

    public MapView(HexMap map, Collection<Unit> units) {
        this(map);
        this.units = new ArrayList<>(units);
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    @Override
    public void render() {
        cacheUnits();
        printColumnHeadings();
        printTop();
        for (int row = 1; row < map.getHeight(); ++row) {
            printRow(row, false);
        }
        printRow(map.getHeight(), true);
    }

    private void cacheUnits() {
        unitCache.clear();
        for (var unit : units) {
            if (!unit.isDestroyed()) {
                unitCache.put(unit.getHex(), unit);
            }
        }
    }

    private void printColumnHeadings() {
        System.out.print("     ");
        for (int i = 1; i <= map.getWidth(); ++i) {
            System.out.print(String.format(" %2d", i));
        }
        System.out.print("\n");
    }

    private void printTop() {
        System.out.print("     ");
        for (int i = 1; i <= map.getWidth()/2; ++i) {
            System.out.print(" __   ");
        }
        if ((map.getWidth() & 1) == 1) {
            System.out.print(" __");
        }
        System.out.print("\n");
    }

    private char getTerrainChar(Hex hex) {
        var tile = map.getTile(hex);
        switch (tile.getId()) {
            case "clear:1":
                return ' ';
            case "woods:1":
                return '*';
            case "woods:2":
                return '#';
            default:
                return ' ';
        }
    }

    private char getUnitIdChar(Hex hex) {
        if (unitCache.containsKey(hex)) {
            return unitCache.get(hex).getId();
        }
        return '_';
    }

    private char getUnitDirectionChar(Hex hex) {
        if (unitCache.containsKey(hex)) {
            var unit = unitCache.get(hex);
            if (unit.getFacing() instanceof North) {
                return '0';
            } else if (unit.getFacing() instanceof NorthEast) {
                return '1';
            } else if (unit.getFacing() instanceof SouthEast) {
                return '2';
            } else if (unit.getFacing() instanceof South) {
                return '3';
            } else if (unit.getFacing() instanceof SouthWest) {
                return '4';
            } else { // NorthWest
                return '5';
            }
        }
        return '_';
    }

    private void printRow(int row, boolean lastLine) {
        System.out.print(String.format("  %2d ", row));
        for (int column = 1; column <= map.getWidth(); ++column) {
            Hex hex = new Hex(column, row);
            if ((column & 1) == 1) { // odd
                char c = getTerrainChar(hex);
                System.out.print(String.format("/%c%c\\", c, c));
            } else { // even
                if (lastLine) {
                    System.out.print("__");
                } else {
                    System.out.print(String.format("%c%c", getUnitIdChar(hex), getUnitDirectionChar(hex)));
                }
            }
        }
        System.out.print("\n     ");
        for (int column = 1; column <= map.getWidth(); ++column) {
            Hex hex = new Hex(column, row);
            if ((column & 1) == 1) { // odd
                System.out.print(String.format("\\%c%c/", getUnitIdChar(hex), getUnitDirectionChar(hex)));
            } else { // even
                if (lastLine) {
                    System.out.print("  ");
                } else {
                    char c = getTerrainChar(new Hex(column, row));
                    System.out.print(String.format("%c%c", c, c));
                }
            }
        }
        System.out.print("\n");
    }

}
