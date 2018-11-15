package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createWeapon() {
        assertEquals(3, WeaponFactory.createWeapon("Small Laser").getDamage());
        assertEquals(5, WeaponFactory.createWeapon("Medium Laser").getDamage());
        assertEquals(8, WeaponFactory.createWeapon("Large Laser").getDamage());
        assertEquals(10, WeaponFactory.createWeapon("PPC").getDamage());
        assertEquals(2, WeaponFactory.createWeapon("Machine Gun").getDamage());
        assertEquals(2, WeaponFactory.createWeapon("Autocannon/2").getDamage());
        assertEquals(5, WeaponFactory.createWeapon("Autocannon/5").getDamage());
        assertEquals(10, WeaponFactory.createWeapon("Autocannon/10").getDamage());
        assertEquals(20, WeaponFactory.createWeapon("Autocannon/20").getDamage());
        assertEquals(4, WeaponFactory.createWeapon("SRM 2").getDamage());
        assertEquals(8, WeaponFactory.createWeapon("SRM 4").getDamage());
        assertEquals(12, WeaponFactory.createWeapon("SRM 6").getDamage());
        assertEquals(5, WeaponFactory.createWeapon("LRM 5").getDamage());
        assertEquals(10, WeaponFactory.createWeapon("LRM 10").getDamage());
        assertEquals(15, WeaponFactory.createWeapon("LRM 15").getDamage());
        assertEquals(20, WeaponFactory.createWeapon("LRM 20").getDamage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWeaponException() {
        WeaponFactory.createWeapon("Large Pulse Laser");
    }
}