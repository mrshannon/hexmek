package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.Player;

/**
 * This is a view that is used to display who won the game.
 */
public class GameOverView implements View {

    private Player winner;

    /**
     * Create a game over view.
     *
     * @param winner player who won the game
     */
    public GameOverView(Player winner) {
        this.winner = winner;
    }

    /**
     * Render the game over view.
     */
    @Override
    public void render() {
        System.out.println();
        System.out.printf("%s was won with %d units remaining.\n\n", winner.getName(), winner.getAliveUnits().size());
    }

}
