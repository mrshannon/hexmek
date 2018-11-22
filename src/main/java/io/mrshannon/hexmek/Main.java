package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.*;
import io.mrshannon.hexmek.views.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        HexMap map;
        try {
            map = (new MapLoader("default")).createMap();
            var view = new MapView(map);
            view.addUnit(UnitFactory.createUnit("Hunchback HBK-4G", map, new Hex(4, 4), new SouthEast()));
            view.addUnit(UnitFactory.createUnit("Manticore Heavy Tank", map, new Hex(7, 12), new North()));
            var unit = UnitFactory.createUnit("Scorpion Light Tank", map, new Hex(15, 17), new SouthEast());
            view.addUnit(unit);
            view.render();
            view.render();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        Player playerA = new Player("The best player.");
        var unitC = UnitFactory.createUnit("Hunchback HBK-4G", map, new Hex(4, 4), new SouthEast());
        playerA.addUnit(unitC);
        var unitA = UnitFactory.createUnit("Manticore Heavy Tank", map, new Hex(7, 5), new North());
        playerA.addUnit(unitA);
        unitA.applyDamage(WeaponFactory.createWeapon("Medium Laser"), 1000);
        Player playerB = new Player("ACE");
        var unitB = UnitFactory.createUnit("Scorpion Light Tank", map, new Hex(15, 17), new SouthWest());
        playerB.addUnit(unitB);
        unitB.applyDamage(WeaponFactory.createWeapon("Medium Laser"), 5);
        var list = new ListView(playerA, playerB);
        System.out.println("\n\n");
        list.render();
        System.out.println("\n\n");
        (new StatusView(unitA)).render();
        System.out.println("\n\n");
        (new StatusView(unitB)).render();
        System.out.println("\n\n");
        (new StatusView(unitC)).render();
        System.out.println("\n\n");
        (new FireSelectView(map, unitC, unitA, unitC.getWeapons())).render();
        System.out.println("\n\n");
        unitA = UnitFactory.createUnit("Hunchback HBK-4G", map, new Hex(4, 4), new SouthEast());
        (new DamageRecordView(unitA.applyDamage(WeaponFactory.createWeapon("PPC"), 300))).render();
        System.out.println("\n\n");
        (new MovementTypeSelectView(unitC)).render();
        System.out.println("\n\n");
        (new MovementTypeSelectView(unitB)).render();
    }
}
