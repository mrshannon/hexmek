package io.mrshannon.hexmek.controllers;

import io.mrshannon.hexmek.models.HexMap;
import io.mrshannon.hexmek.models.Player;
import io.mrshannon.hexmek.models.RandomSingleton;
import io.mrshannon.hexmek.models.Unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class of all controllers used to play the game.  It provides storage for the map and players as well as many
 * utility methods.  Running this controller will simply return itself.
 */
public class GameController implements Controller {

    private BufferedReader reader;
    private HexMap map;
    private List<Player> players;
    private int currentPlayer;

    /**
     * Construct a game controller from the map and players.
     *
     * @param map hex grid map to play the game on
     * @param players list of players
     */
    public GameController(HexMap map, List<Player> players) {
        this.map = map;
        this.players = players;
        this.currentPlayer = 0;
    }

    /**
     * Construct a game controller from another game controller.
     *
     * @param other game controller to copy
     */
    public GameController(GameController other) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.map = other.getMap();
        this.players = other.getPlayers();
        this.currentPlayer = this.players.indexOf(other.getCurrentPlayer());
    }

    /**
     * Run the controller, returning itself.
     *
     * @return itself
     * @throws IOException
     */
    @Override
    public Controller run() throws IOException {
        return this;
    }

    /**
     * Get the map the game is being played on.
     *
     * @return hex coordinate map
     */
    protected HexMap getMap() {
        return map;
    }

    /**
     * Get the list of players in the game.
     *
     * @return list of players
     */
    protected List<Player> getPlayers() {
        return players;
    }

    /**
     * Get the current player.
     *
     * @return current player
     */
    protected Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Change the current player to the next player.
     */
    protected void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    /**
     * Change to a random player for the current player.
     */
    protected void setRandomPlayer() {
        currentPlayer = RandomSingleton.getRandom().nextInt(players.size());
    }

    /**
     * Get a list of all units in the game, including those that have been destroyed.
     *
     * @return list of all units in game
     */
    protected List<Unit> getUnits() {
        var units = new ArrayList<Unit>();
        for (var player : players) {
            units.addAll(player.getUnits());
        }
        return units;
    }

    /**
     * Get a unit, given its character ID.
     *
     * @param Id id of unit to get
     * @return unit corresponding to the given id
     * @throws NoSuchUnitException if the unit is not in the game
     */
    protected Unit getUnit(char Id) throws NoSuchUnitException {
        for (var unit : getUnits()) {
            if (unit.getId() == Id) {
                return unit;
            }
        }
        throw new NoSuchUnitException(Id);
    }

    /**
     * Read a line from the user.
     *
     * @return the read line
     * @throws IOException if the line cannot be read
     */
    protected String readLine() throws IOException {
        return reader.readLine();
    }

}
