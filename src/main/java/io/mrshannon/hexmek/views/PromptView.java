package io.mrshannon.hexmek.views;

/**
 * View to display the game prompt.
 */
public class PromptView implements View {

    private String player;
    private String stage;

    /**
     * Create a generic prompt.
     *
     * @param player current player
     */
    public PromptView(String player) {
        this.player = player;
        this.stage = null;
    }

    /**
     * Create a prompt that indicates the current game stage.
     *
     * @param player current player
     * @param stage current game stage
     */
    public PromptView(String player, String stage) {
        this.player = player;
        this.stage = stage;
    }

    /**
     * Render the view.
     */
    @Override
    public void render() {
        System.out.println();
        if (stage != null) {
            System.out.printf("[%s](%s): ", player, stage);
        } else {
            System.out.printf("[%s]: ", player);
        }
    }

}
