package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.*;

import java.io.IOException;

public class DemoGame {

    private HexMap map;
    private Player player1;
    private Player player2;

    /**
     * Run the demo game.  This is a game that has 2 players, runs the default map and has units pre placed on the map.
     *
     * @throws IOException
     */
    public DemoGame() throws IOException {
        this.map = (new MapLoader("default")).createMap();
        setupPlayer1();
        setupPlayer2();
    }

    /**
     * Setup the units for player 1.
     */
    private void setupPlayer1() {
        var player = new Player("Player 1");
        player.addUnit(UnitFactory.createUnit("Jenner JR7-D", map, new Hex(2, 1), new South()));
        player.addUnit(UnitFactory.createUnit("Atlas AS7-D", map, new Hex(14, 1), new South()));
        player.addUnit(UnitFactory.createUnit("Scorpion Light Tank", map, new Hex(5, 3), new South()));
        player.addUnit(UnitFactory.createUnit("Scorpion Light Tank", map, new Hex(11, 3), new South()));
        player1 = player;
    }

    /**
     * Setup the units for player 2.
     */
    private void setupPlayer2() {
        var player = new Player("Player 2");
        player.addUnit(UnitFactory.createUnit("Hunchback HBK-4G", map, new Hex(2, 16), new North()));
        player.addUnit(UnitFactory.createUnit("Catapult CPLT-C1", map, new Hex(14, 16), new North()));
        player.addUnit(UnitFactory.createUnit("Manticore Heavy Tank", map, new Hex(8, 15), new North()));
        player2 = player;
    }

    /**
     * Run the demo game.
     *
     * @throws IOException
     */
    public void run() throws IOException {
        var game = new Game(map, player1, player2);
        game.run();
    }

}
