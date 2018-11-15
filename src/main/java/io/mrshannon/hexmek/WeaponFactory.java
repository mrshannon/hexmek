package io.mrshannon.hexmek;

import java.util.HashMap;


/**
 * A factory for the Weapon class that uses the Flyweight pattern to allow many of the same type of weapons to be made
 * cheaply.
 */
public class WeaponFactory {

    private static HashMap<String, Weapon> weaponTypes = new HashMap<>();

    private WeaponFactory() {
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
     * @return
     */
    public static Weapon createWeapon(String weaponType) {

        if (weaponTypes.containsKey(weaponType)) {
            return weaponTypes.get(weaponType);
        }

        return cacheWeapon(weaponType);
    }

    /**
     * Create and cache weapon flyweight, it must not already exist in the cache.
     *
     * @param weaponType type of weapon to store in the cache
     * @return the new weapon, that is stored in the cache
     */
    private static Weapon cacheWeapon(String weaponType) {
        Weapon weapon;
        switch (weaponType) {
            case "Small Laser":
                weapon = createSmallLaser();
                break;
            case "Medium Laser":
                weapon = createMediumLaser();
                break;
            case "Large Laser":
                weapon = createLargeLaser();
                break;
            case "PPC":
                weapon = createPPC();
                break;
            case "Machine Gun":
                weapon = createMachineGun();
                break;
            case "Autocannon/2":
                weapon = createAutocannon2();
                break;
            case "Autocannon/5":
                weapon = createAutocannon5();
                break;
            case "Autocannon/10":
                weapon = createAutocannon10();
                break;
            case "Autocannon/20":
                weapon = createAutocannon20();
                break;
            case "SRM 2":
                weapon = createSRM(2);
                break;
            case "SRM 4":
                weapon = createSRM(4);
                break;
            case "SRM 6":
                weapon = createSRM(6);
                break;
            case "LRM 5":
                weapon = createLRM(5);
                break;
            case "LRM 10":
                weapon = createLRM(10);
                break;
            case "LRM 15":
                weapon = createLRM(15);
                break;
            case "LRM 20":
                weapon = createLRM(20);
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown weapon type %s.", weaponType));
        }
        weaponTypes.put(weaponType, weapon);
        return weapon;
    }

    /**
     * Create a Small Laser.
     *
     * @return small laser weapon
     */
    private static Weapon createSmallLaser() {
        return new Weapon("Small Laser",
                new WeaponRange(1, 2, 3),
                new DirectFireStrategy(),
                new NormalFiringStrategy(3));
    }

    /**
     * Create a Medium Laser.
     *
     * @return medium laser weapon
     */
    private static Weapon createMediumLaser() {
        return new Weapon("Medium Laser",
                new WeaponRange(3, 6, 9),
                new DirectFireStrategy(),
                new NormalFiringStrategy(5));
    }

    /**
     * Create a Large laser.
     *
     * @return large laser weapon
     */
    private static Weapon createLargeLaser() {
        return new Weapon("Large Laser",
                new WeaponRange(5, 10, 15),
                new DirectFireStrategy(),
                new NormalFiringStrategy(8));
    }

    /**
     * Create Particle Projector Cannon (PPC).
     *
     * @return ppc weapon
     */
    private static Weapon createPPC() {
        return new Weapon("PPC",
                new WeaponRange(3, 6, 12, 18),
                new DirectFireStrategy(),
                new NormalFiringStrategy(10));
    }

    /**
     * Create Machine Gun.
     *
     * @return machine gun weapon
     */
    private static Weapon createMachineGun() {
        return new Weapon("Machine Gun",
                new WeaponRange(1, 2, 3),
                new DirectFireStrategy(),
                new NormalFiringStrategy(2));
    }

    /**
     * Create Autocannon/2.
     *
     * @return autocannon/2 weapon
     */
    private static Weapon createAutocannon2() {
        return new Weapon("Autocannon/2",
                new WeaponRange(4, 8, 16, 24),
                new DirectFireStrategy(),
                new NormalFiringStrategy(2));
    }

    /**
     * Create Autocannon/5.
     *
     * @return autocannon/5 weapon
     */
    private static Weapon createAutocannon5() {
        return new Weapon("Autocannon/5",
                new WeaponRange(3, 6, 12, 18),
                new DirectFireStrategy(),
                new NormalFiringStrategy(5));
    }

    /**
     * Create Autocannon/10.
     *
     * @return autocannon/10 weapon
     */
    private static Weapon createAutocannon10() {
        return new Weapon("Autocannon/10",
                new WeaponRange(5, 10, 15),
                new DirectFireStrategy(),
                new NormalFiringStrategy(10));
    }

    /**
     * Create Autocannon/20.
     *
     * @return autocannon/20 weapon
     */
    private static Weapon createAutocannon20() {
        return new Weapon("Autocannon/20",
                new WeaponRange(3, 6, 9),
                new DirectFireStrategy(),
                new NormalFiringStrategy(20));
    }

    /**
     * Create Short Range Missile (SRM) launcher.
     *
     * @param missiles number of missiles in srm launcher
     * @return srm launcher
     */
    private static Weapon createSRM(int missiles) {
        return new Weapon("SRM " + missiles,
                new WeaponRange(3, 6, 9),
                new DirectFireStrategy(),
                new ClusterFiringStrategy(2, missiles));
    }

    /**
     * Create Long Range Missile (SRM) launcher.
     *
     * @param missiles number of missiles in srm launcher
     * @return srm launcher
     */
    private static Weapon createLRM(int missiles) {
        return new Weapon("LRM " + missiles,
                new WeaponRange(6, 7, 14, 21),
                new IndirectFireStrategy(),
                new ClusterFiringStrategy(1, missiles));
    }

}
