package io.mrshannon.hexmek.views;

import io.mrshannon.hexmek.models.Mech;
import io.mrshannon.hexmek.models.Unit;
import io.mrshannon.hexmek.models.Vehicle;
import io.mrshannon.hexmek.models.Weapon;

/**
 * A status view that shows the status of a single unit and its weapons.
 */
public class StatusView implements View {

    Unit unit;

    /**
     * Create a unit status view.
     *
     * @param unit the unit to display the status of
     */
    public StatusView(Unit unit) {
        this.unit = unit;
    }

    /**
     * Render the unit status view.
     */
    @Override
    public void render() {
        System.out.println();
        printStatus();
        System.out.println();
        printWeapons();
    }

    /**
     * Print unit status.
     */
    private void printStatus() {
        if (unit instanceof Mech) {
            printMechStatus();
        } else {
            printVehicleStatus();
        }
    }

    /**
     * Print mech status.
     */
    private void printMechStatus() {
        var mech = (Mech) unit;
        System.out.println("ID: " + mech.getId());
        System.out.println(String.format("Type: %-40s __", mech.getType()));
        String health;
        if (mech.isDestroyed()) {
            health = "DESTROYED";
        } else {
            health = String.format("%3d%%", (int) (100*mech.getArmourPercent()));
        }
        System.out.printf("Health: %-32s __ __|%2d|__ __\n", health, mech.getHeadArmour());
        System.out.printf("Grid: %02d%02d                              /  /%2d|__|%2d\\  \\\n",
                mech.getHex().getColumn(), mech.getHex().getRow(),
                mech.getLeftTorsoArmour(), mech.getRightTorsoArmour());
        System.out.printf("Facing: %-30s /%2d/\\__/%2d\\__/\\%2d\\\n", mech.getFacing(),
                mech.getLeftArmArmour(), mech.getCenterTorsoArmour(), mech.getRightArmArmour());
        System.out.print("Movement Points:                       \\_/  __\\__/__  \\_/\n");
        System.out.printf("    Walking: %2d                            /  \\  /  \\\n", mech.getCruiseMovementPoints());
        System.out.printf("    Running: %2d                            |%2d|  |%2d|\n",
                mech.getFlankMovementPoints(), mech.getLeftLegArmour(), mech.getRightLegArmour());
        System.out.printf("To-Hit Modifier: %2d                        |__|  |__|\n", unit.getToHitModifier());
        System.out.printf("Gunnery Modifier %2d\n", unit.getGunneryModifier());
    }

    /**
     * Print vehicle status.
     */
    private void printVehicleStatus() {
        var vehicle = (Vehicle) unit;
        System.out.println("ID: " + vehicle.getId());
        System.out.println(String.format("Type: %-38s ______", vehicle.getType()));
        String health;
        if (vehicle.isDestroyed()) {
            health = "DESTROYED";
        } else {
            health = String.format("%3d%%", (int) (100*vehicle.getArmourPercent()));
        }
        System.out.printf("Health: %-35s /\\ %2d /\\\n", health, vehicle.getFrontArmour());
        System.out.printf("Grid: %02d%02d                                 /  \\__/  \\\n",
                vehicle.getHex().getColumn(), vehicle.getHex().getRow());
        System.out.printf("Facing: %-34s |%2d/%2d\\%2d|\n", vehicle.getFacing(),
                vehicle.getLeftSideArmour(), vehicle.getTurretArmour(), vehicle.getRightSideArmour());
        System.out.print("Movement Points:                           |  \\__/  |\n");
        System.out.printf("    Walking: %2d                            |__/%2d\\__|\n",
                vehicle.getCruiseMovementPoints(), vehicle.getRearArmour());
        System.out.printf("    Running: %2d                            \\________/\n",
                vehicle.getFlankMovementPoints());
        System.out.printf("To-Hit Modifier %2d\n", unit.getToHitModifier());
        System.out.printf("Gunnery Modifier %2d\n", unit.getGunneryModifier());
    }

    /**
     * Print list of weapons.
     */
    private void printWeapons() {
        printHeader();
        printSeparator();
        printLines();
    }

    /**
     * Print header for list of weapons.
     */
    private void printHeader() {
        System.out.println("Weapon Type     Dmg   Min   Sht   Med   Lng   Location");
    }

    /**
     * Print header separator.
     */
    private void printSeparator() {
        System.out.println("-------------   ---   ---   ---   ---   ---   ------------");
    }

    /**
     * Print each line of the weapon status table.
     */
    private void printLines() {
        for (var component : unit.getComponents()) {
            String location = component.getType();
            if (component.isDestroyed()) {
                location = "DESTROYED";
            }
            for (var weapon : component.getWeapons()) {
                printLine(location, weapon);
            }
        }
    }

    /**
     * Print a single weapon status.
     *
     * @param location location of the weapon
     * @param weapon weapon to print
     */
    private void printLine(String location, Weapon weapon) {
        var range = weapon.getRange();
        String min;
        if (range.getMinimumRange() == 0) {
            min = "-";
        } else {
            min = String.format("%d", range.getMinimumRange());
        }
        System.out.printf("%-13s   %3d   %3s   %3d   %3d   %3d   %s\n", weapon.getType(), weapon.getDamage(), min,
                range.getShortRange(), range.getMediumRange(), range.getLongRange(), location);
    }

}
