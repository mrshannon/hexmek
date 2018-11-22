package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.Unit;

/**
 * Movement select view.  Allows the user to move a unit.
 */
public class MovementSelectView implements View {

    private Unit unit;
    private boolean canMoveBackwards;

    /**
     * Create the view.
     *
     * @param unit unit to move
     * @param canMoveBackwards true if backwards movement is allowed
     */
    public MovementSelectView(Unit unit, boolean canMoveBackwards) {
        this.unit = unit;
        this.canMoveBackwards = canMoveBackwards;
    }

    /**
     * Render the view.
     */
    @Override
    public void render() {
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
        System.out.println("    w. Move Forward");
        if (canMoveBackwards) {
            System.out.println("    s. Move Backward");
        }
        System.out.println("    a. Rotate Left");
        System.out.println("    d. Rotate Right");
        System.out.println("    e. End Movement");
        System.out.println("    r. Reset");
    }

    /**
     * Print the prompt.
     */
    private void printPrompt() {
        System.out.print("Select movement: ");
    }

}
