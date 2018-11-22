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
        System.out.println("    h. Halt");
        System.out.printf("    w. Walk      (%d MP)  (+1 gunnery)\n", unit.getCruiseMovementPoints());
        System.out.printf("    r. Run       (%d MP)  (+2 gunnery)\n", unit.getFlankMovementPoints());
    }

    /**
     * Print vehicle movement choices.
     */
    private void printVehicleChoices() {
        System.out.println("    h. Halt");
        System.out.printf("    c. Cruise    (%d MP)  (+1 gunnery)\n", unit.getCruiseMovementPoints());
        System.out.printf("    f. Flank     (%d MP)  (+2 gunnery)\n", unit.getFlankMovementPoints());
    }

    /**
     * Print the movement type prompt.
     */
    private void printPrompt() {
        System.out.println("Select movement type: ");
    }

}
