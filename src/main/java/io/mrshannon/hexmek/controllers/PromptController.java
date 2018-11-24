package io.mrshannon.hexmek.controllers;

import io.mrshannon.hexmek.models.Unit;
import io.mrshannon.hexmek.views.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * An abstract controller that provides a default prompt with default options.  It consolidates the shared code of the
 * stage controllers.
 */
public abstract class PromptController extends GameController {

    private HashSet<Unit> turnsTaken;

    /**
     * Construct a prompt controller from a game controller.
     *
     * @param other game controller to copy the game state from
     */
    public PromptController(GameController other) {
        super(other);
        turnsTaken = new HashSet<>();
    }

    /**
     * Run the controller, displaying the prompt and dealing with a player entered command.
     *
     * @return a controller, based on the command the user enters
     * @throws IOException
     */
    @Override
    public Controller run() throws IOException {
        var args = promptForInput();
        if (args.isEmpty()) {
            return this;
        }
        return handleInput(args);
    }

    /**
     * Determine if a unit has already exhausted its turn.
     *
     * @param unit unit to check
     * @return true if the given unit has already taken it's turn, false otherwise
     */
    public boolean turnTaken(Unit unit) {
        return turnsTaken.contains(unit);
    }

    /**
     * Register that a unit has taken it's turn.
     *
     * @param unit unit to register the turn for
     */
    public void takeTurn(Unit unit) {
        turnsTaken.add(unit);
        nextPlayer();
    }

    /**
     * Get the current stage name, it will be printed in the prompt.
     *
     * @return name of current stage
     */
    protected abstract String getStage();

    /**
     * Prompt the player for input and return the input as a list of the space separated command/arguments.
     *
     * @return list of commands/arguments the player typed ito the prompt
     * @throws IOException
     */
    private List<String> promptForInput() throws IOException {
        (new PromptView(getCurrentPlayer().getName(), getStage())).render();
        ArrayList<String> args = new ArrayList<>(Arrays.asList(readLine().split("\\s+")));
        if (args.size() == 1 && args.get(0).isEmpty()) {
            args.clear();
        }
        return args;
    }

    /**
     * Handle input from the player.  Valid commands are:
     *
     * <ul>
     *     <li>map - displays the map</li>
     *     <li>list - list all units in the game</li>
     *     <li>status {id} - get the status of the given unit</li>
     *     <li>pass - pass on your turn</li>
     *     <li>exit/quit - quit the game</li>
     * </ul>
     *
     * @param args player input as a list of commands/arguments
     * @return next controller to run
     */
    protected Controller handleInput(List<String> args) {
        switch (args.get(0)) {
            case "map":
                return handleMap();
            case "list":
                return handleList();
            case "status":
                return handleStatus(args);
            case "pass":
                return handlePass();
            case "exit":
            case "quit":
                return handleExit();
            default:
                (new MessageView(String.format("Unknown command '%s'", args.get(0)))).render();
                return this;
        }
    }

    /**
     * Handle the 'map' command, displaying the hexagonal grid map.
     *
     * @return the same prompt controller
     */
    private Controller handleMap() {
        (new MapView(getMap(), getUnits())).render();
        return this;
    }

    /**
     * Handle the 'list' command, displaying a list of all units in the game.
     *
     * @return the same prompt controller
     */
    private Controller handleList() {
        (new ListView(getPlayers())).render();
        return this;
    }

    /**
     * Handle the 'status' command, displaying the status of the given unit.
     *
     * @param args the arguments from the user, in the form ['status', 'id']
     * @return the same prompt controller
     */
    private Controller handleStatus(List<String> args) {
        if (args.size() < 2 || args.get(1).length() != 1) {
            (new MessageView("Must provide unit ID.")).render();
            return this;
        }
        try {
            (new StatusView(getUnit(args.get(1).toUpperCase().charAt(0)))).render();
        } catch (NoSuchUnitException e) {
            (new MessageView(e.getMessage())).render();
        }
        return this;
    }

    /**
     * Handle the 'pass' command which ends the player's turn for the current stage.
     *
     * @return the same prompt controller
     */
    private Controller handlePass() {
        turnsTaken.addAll(getCurrentPlayer().getUnits());
        return this;
    }

    /**
     * Handle the 'exit'/'quit' command which ends the game.
     *
     * @return the {@code ExitController} which quits the game when run
     */
    private Controller handleExit() {
        return new ExitController();
    }

}
