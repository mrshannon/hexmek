package io.mrshannon.hexmek.controllers;

import io.mrshannon.hexmek.views.GameOverView;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * End of turn controller, checks to see if a player has won the game, displaying the name of the winner and ending the
 * game if they have.
 */
public class EndOfTurnController extends GameController {

    /**
     * Construct an end of turn controller.
     *
     * @param other game controller to copy the game state from
     */
    public EndOfTurnController(GameController other) {
        super(other);
    }

    /**
     * Run the controller, checking for a winner and ending the game if it finds one.
     *
     * @return a {@link ExitController} if there is a winner, {@link MovementStageController} otherwise
     * @throws IOException
     */
    @Override
    public Controller run() throws IOException {
        var alive = getPlayers().stream().filter(p -> p.getAliveUnits().size() > 0).collect(Collectors.toList());
        if (alive.size() == 1) {
            (new GameOverView(alive.get(0))).render();
            return new ExitController();
        }
        return new MovementStageController(this);
    }

}
