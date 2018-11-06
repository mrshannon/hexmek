package io.mrshannon.hexmek;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        var die = new io.mrshannon.hexmek.Die(12);
        var dice = new DiceRoller(new Die(6), new Die(6));
        for (int i = 0; i <= 20; ++i) {
            System.out.println(String.format("%d = %f", i, dice.probability(i)));
        }
        Direction d = new North();
        for (int i = 0; i <= 20; i++) {
            System.out.println(d);
            d = d.rotateRight();
        }
        for (int i = 0; i <= 20; i++) {
            System.out.println(d);
            d = d.rotateLeft();
        }

        var loader = new MapLoader("default");
        HexMap map;
        try {
            map = loader.createMap();
            System.out.println(map.getTile(new Hex(5,13)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
