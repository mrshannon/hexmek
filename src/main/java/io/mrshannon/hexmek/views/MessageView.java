package io.mrshannon.hexmek.views;

/**
 * This is a view that is used to display a message to the player.
 */
public class MessageView implements View {

    private String message;

    /**
     * Construct a message view.
     *
     * @param message message to display
     */
    public MessageView(String message) {
        this.message = message;
    }

    /**
     * Render the message view, displaying the message.
     */
    @Override
    public void render() {
        System.out.println("\n" + message);
    }

}
