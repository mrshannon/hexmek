package io.mrshannon.hexmek;

import io.mrshannon.hexmek.controllers.Controller;
import io.mrshannon.hexmek.controllers.GameController;
import io.mrshannon.hexmek.controllers.MovementStageController;
import io.mrshannon.hexmek.models.HexMap;
import io.mrshannon.hexmek.models.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The game runner class.  It is used to start the game given a map and a list of players.
 */
public class Game {

    private HexMap map;
    private List<Player> players;

    /**
     * Create a new game.
     *
     * @param map map to play the game on
     * @param players the players that will player the game
     */
    public Game(HexMap map, Player... players) {
        this.map = map;
        this.players = new ArrayList<>(Arrays.asList(players));
    }

    /**
     * Run the game.
     *
     * @throws IOException
     */
    public void run() throws IOException {
        Controller controller = new MovementStageController(new GameController(map, players));
        while (true) {
            controller = controller.run();
        }
    }
}
