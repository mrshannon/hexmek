package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.Mech;
import io.mrshannon.hexmek.models.Unit;

/**
 * Movement type select view, allows the user to select the type of movement.
 */
public class MovementTypeSelectView implements View {

    private Unit unit;

    /**
     * Create the view.
     *
     * @param unit unit to display movement options for
     */
    public MovementTypeSelectView(Unit unit) {
        this.unit = unit;
    }

    /**
     * Render the view
     */
    @Override
    public void render() {
        System.out.println();
        System.out.println("    halt   = do not move at all");
        if (unit instanceof Mech) {
            printMechChoices();
        } else {
            printVehicleChoices();
        }
        System.out.println();
        printPrompt();
    }

    /**
     * Print mech movement choices.
     */
    private void printMechChoices() {
        System.out.printf("    walk   = %d MP  (+1 gunnery)\n", unit.getCruiseMovementPoints());
        System.out.printf("    run    = %d MP  (+2 gunnery)\n", unit.getFlankMovementPoints());
    }

    /**
     * Print vehicle movement choices.
     */
    private void printVehicleChoices() {
        System.out.printf("    cruise = %d MP  (+1 gunnery)\n", unit.getCruiseMovementPoints());
        System.out.printf("    flank  = %d MP  (+2 gunnery)\n", unit.getFlankMovementPoints());
    }

    /**
     * Print the movement type prompt.
     */
    private void printPrompt() {
        System.out.print("Select movement type: ");
    }

}
