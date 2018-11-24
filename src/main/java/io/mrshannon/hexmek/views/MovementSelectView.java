package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.Unit;

/**
 * Movement select view.  Allows the user to move a unit.
 */
public class MovementSelectView implements View {

    private Unit unit;

    /**
     * Create the view.
     *
     * @param unit unit to move
     */
    public MovementSelectView(Unit unit) {
        this.unit = unit;
    }

    /**
     * Render the view.
     */
    @Override
    public void render() {
        System.out.println();
        printStatusLine();
        System.out.println();
        printOptions();
        System.out.println();
        printPrompt();
    }

    /**
     * Print the status line, detailing ID, grid position, facing direction, remaining movement points, and the to-hit
     * modifier.
     */
    private void printStatusLine() {
        System.out.printf("Unit: %c    Grid: %02d%02d    Facing: %s    MP: %d    To-Hit: %d\n",
                unit.getId(), unit.getHex().getColumn(), unit.getHex().getRow(), unit.getFacing(),
                unit.getMovementPoints(), unit.getToHitModifier());
    }

    /**
     * Print the available movement options.
     */
    private void printOptions() {
        System.out.println("    forward = Move forward");
        System.out.println("    back    = Move backward");
        System.out.println("    left    = Rotate left");
        System.out.println("    right   = Rotate right");
        System.out.println("    end     = End movement");
        System.out.println("    reset   = Reset to before movement");
    }

    /**
     * Print the prompt.
     */
    private void printPrompt() {
        System.out.print("Select movement: ");
    }

}
