package io.mrshannon.hexmek;

import java.util.HashMap;

/**
 * A factory for the Unit class.
 */
public class UnitFactory {

    private static char nextId = 'A';
    private static HashMap<String, UnitBuilder> unitBuilders = new HashMap<>();

    private UnitFactory() {
    }

    /**
     * Create a new weapon from the given type string.  Uses the flyweight pattern to make weapons cheap.
     *
     * <br>
     *
     * Types of weapons:
     * <ul>
     *     <li>Small Laser</li>
     *     <li>Medium Laser</li>
     *     <li>Large Laser</li>
     *     <li>PPC</li>
     *     <li>Machine Gun</li>
     *     <li>Autocannon/2</li>
     *     <li>Autocannon/5</li>
     *     <li>Autocannon/10</li>
     *     <li>Autocannon/20</li>
     *     <li>SRM 2</li>
     *     <li>SRM 4</li>
     *     <li>SRM 6</li>
     *     <li>LRM 5</li>
     *     <li>LRM 10</li>
     *     <li>LRM 15</li>
     *     <li>LRM 20</li>
     * </ul>
     *
     * @param weaponType type of weapon to create
     * @return a new weapon flyweight of the given type
     */

    /**
     * Create a new unit from the given type string with the given ID.
     *
     * <br>
     *
     * The ID of the new unit will be assigned, starting at 'A' and incrementing with every new unit.
     *
     * <br>
     *
     * Types of units:
     * <ul>
     *     <li>Scorpion Light Tank</li>
     *     <li>Bulldog Medium Tank</li>
     *     <li>Manticore Heavy Tank</li>
     *     <li>Jenner JR7-D</li>
     *     <li>Hunchback HBK-4G</li>
     *     <li>Catapult CPLT-C1</li>
     *     <li>Atlas AS7-D</li>
     * </ul>
     *
     * @param unitType type of unit to create
     * @param map the map to place the unit on
     * @param hex location of the unit
     * @param facing direction the unit will face
     * @return the new unit
     */
    public Unit createUnit(String unitType, HexMap map, Hex hex, Direction facing) {
        UnitBuilder builder;
        if (unitBuilders.containsKey(unitType)) {
            builder = unitBuilders.get(unitType);
        } else {
            builder = cacheBuilder(unitType);
        }
        if (nextId > 'Z') {
            throw new RuntimeException("Unit ID's exhausted, cannot create any more units.");
        }
        return builder.build(nextId++, map, hex, facing);
    }

    /**
     * Create and cache a unit builder, it must not already exist in the cache.
     *
     * @param unitType type of unit to store a builder for in the cache
     * @return the new unit builder that is also stored in the cache
     */
    private UnitBuilder cacheBuilder(String unitType) {
        UnitBuilder builder;
        switch (unitType) {
            case "Scorpion Light Tank":
                builder = createScorpionLightTank();
                break;
            case "Bulldog Medium Tank":
                builder = createBulldogMediumTank();
                break;
            case "Manticore Heavy Tank":
                builder = createManticoreHeavyTank();
                break;
            case "Jenner JR7-D":
                builder = createJennerJR7_D();
                break;
            case "Hunchback HBK-4G":
                builder = createHunchbackHBK_4G();
                break;
            case "Catapult CPLT-C1":
                builder = createCatapultCPLT_C1();
                break;
            case "Atlas AS7-D":
                builder = createAtlasAS7_D();
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown unit type %s.", unitType));
        }
        unitBuilders.put(unitType, builder);
        return builder;
    }

    /**
     * Create a builder for the Scorpion Light Tank.
     *
     * @return scorpion light tank builder
     */
    private UnitBuilder createScorpionLightTank() {
        var builder = new VehicleBuilder("Scorpion Light Tank", 4);
        builder.turret(16).front(16).rear(10).rightSide(11).leftSide(11);
        builder.turret(WeaponFactory.createWeapon("Autocannon/5"));
        builder.turret(WeaponFactory.createWeapon("Machine Gun"));
        return builder;
    }

    /**
     * Create a builder for the Bulldog Medium Tank.
     *
     * @return bulldog medium tank builder
     */
    private UnitBuilder createBulldogMediumTank() {
        var builder = new VehicleBuilder("Bulldog Medium Tank", 4);
        builder.turret(20).front(24).rear(20).rightSide(20).leftSide(20);
        builder.front(WeaponFactory.createWeapon("Machine Gun"));
        builder.turret(WeaponFactory.createWeapon("Large Laser"));
        builder.turret(WeaponFactory.createWeapon("SRM 4"));
        builder.turret(WeaponFactory.createWeapon("SRM 4"));
        return builder;
    }

    /**
     * Create a builder for the Manticore Heavy Tank.
     *
     * @return manticore heavy tank builder
     */
    private UnitBuilder createManticoreHeavyTank() {
        var builder = new VehicleBuilder("Manticore Heavy Tank", 4);
        builder.turret(42).front(42).rear(26).rightSide(33).leftSide(33);
        builder.front(WeaponFactory.createWeapon("Medium Laser"));
        builder.turret(WeaponFactory.createWeapon("LRM 10"));
        builder.turret(WeaponFactory.createWeapon("PPC"));
        builder.turret(WeaponFactory.createWeapon("SRM 6"));
        return builder;
    }

    /**
     * Create a builder for the Jenner JR7-D.
     *
     * @return jenner JR7-D builder
     */
    private UnitBuilder createJennerJR7_D() {
        var builder = new MechBuilder("Jenner JR7-D", 7);
        builder.head(10).centerTorso(24).rightTorso(20).leftTorso(20).rightArm(10).leftArm(10).rightLeg(14).leftLeg(14);
        builder.centerTorso(WeaponFactory.createWeapon("SRM 4"));
        builder.leftArm(WeaponFactory.createWeapon("Medium Laser"));
        builder.leftArm(WeaponFactory.createWeapon("Medium Laser"));
        builder.rightArm(WeaponFactory.createWeapon("Medium Laser"));
        builder.rightArm(WeaponFactory.createWeapon("Medium Laser"));
        return builder;
    }

    /**
     * Create a builder for the Hunchback HBK-4G.
     *
     * @return hunchback HBK-4G builder
     */
    private UnitBuilder createHunchbackHBK_4G() {
        var builder = new MechBuilder("Hunchback HBK-4G", 4);
        builder.head(12).centerTorso(47).rightTorso(36).leftTorso(36).rightArm(24).leftArm(24).rightLeg(32).leftLeg(32);
        builder.head(WeaponFactory.createWeapon("Small Laser"));
        builder.rightTorso(WeaponFactory.createWeapon("Autocannon/20"));
        builder.rightArm(WeaponFactory.createWeapon("Medium Laser"));
        builder.leftArm(WeaponFactory.createWeapon("Medium Laser"));
        return builder;
    }

    /**
     * Create a builder for the Catapult CPLT-C1.
     *
     * @return catapult CPLT-C1 builder
     */
    private UnitBuilder createCatapultCPLT_C1() {
        var builder = new MechBuilder("Catapult CPLT-C1", 4);
        builder.head(12).centerTorso(56).rightTorso(42).leftTorso(42).rightArm(23).leftArm(23).rightLeg(32).leftLeg(32);
        builder.centerTorso(WeaponFactory.createWeapon("Medium Laser"));
        builder.centerTorso(WeaponFactory.createWeapon("Medium Laser"));
        builder.rightTorso(WeaponFactory.createWeapon("Medium Laser"));
        builder.leftTorso(WeaponFactory.createWeapon("Medium Laser"));
        builder.rightArm(WeaponFactory.createWeapon("LRM 15"));
        builder.leftArm(WeaponFactory.createWeapon("LRM 15"));
        return builder;
    }

    /**
     * Create a builder for the Atlas AS7-D.
     *
     * @return atlas AS7-D builder
     */
    private UnitBuilder createAtlasAS7_D() {
        var builder = new MechBuilder("Atlas AS7-D", 3);
        builder.head(12).centerTorso(92).rightTorso(63).leftTorso(63).rightArm(51).leftArm(51).rightLeg(62).leftLeg(62);
        builder.centerTorso(WeaponFactory.createWeapon("Medium Laser"));
        builder.centerTorso(WeaponFactory.createWeapon("Medium Laser"));
        builder.rightTorso(WeaponFactory.createWeapon("Autocannon/20"));
        builder.leftTorso(WeaponFactory.createWeapon("LRM 20"));
        builder.leftTorso(WeaponFactory.createWeapon("SRM 6"));
        builder.rightArm(WeaponFactory.createWeapon("Medium Laser"));
        builder.leftArm(WeaponFactory.createWeapon("Medium Laser"));
        return builder;
    }

}
