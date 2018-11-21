package io.mrshannon.hexmek.models;

import io.mrshannon.hexmek.views.MapView;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            var map = (new MapLoader("default")).createMap();
            var view = new MapView(map);
            view.addUnit(UnitFactory.createUnit("Hunchback HBK-4G", map, new Hex(4, 4), new SouthEast()));
            view.addUnit(UnitFactory.createUnit("Manticore Heavy Tank", map, new Hex(7, 12), new North()));
            var unit = UnitFactory.createUnit("Scorpion Light Tank", map, new Hex(15, 17), new SouthEast());
            view.addUnit(unit);
            view.render();
            unit.applyDamage(WeaponFactory.createWeapon("Medium Laser"), 1000);
            view.render();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}