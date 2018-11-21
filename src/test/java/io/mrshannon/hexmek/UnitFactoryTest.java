package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitFactoryTest {

    HexMap map;

    @Before
    public void setUp() throws Exception {
        map = (new MapLoader("clear")).createMap();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createUnitMech() {
        Mech mech = (Mech) UnitFactory.createUnit(
                "Atlas AS7-D", map, new Hex(4, 7), new SouthEast());
        assertEquals(12, mech.getHeadArmour());
        assertEquals(92, mech.getCenterTorsoArmour());
        assertEquals(63, mech.getRightTorsoArmour());
        assertEquals(63, mech.getLeftTorsoArmour());
        assertEquals(51, mech.getRightArmArmour());
        assertEquals(51, mech.getLeftArmArmour());
        assertEquals(62, mech.getRightLegArmour());
        assertEquals(62, mech.getLeftLegArmour());
        assertTrue(mech.getWeapons().contains(WeaponFactory.createWeapon("Medium Laser")));
        assertTrue(mech.getWeapons().contains(WeaponFactory.createWeapon("Autocannon/20")));
        assertTrue(mech.getWeapons().contains(WeaponFactory.createWeapon("LRM 20")));
        assertTrue(mech.getWeapons().contains(WeaponFactory.createWeapon("SRM 6")));
    }

    @Test
    public void createUnitVehicle() {
        Vehicle vehicle = (Vehicle) UnitFactory.createUnit(
                "Manticore Heavy Tank", map, new Hex(4, 7), new SouthEast());
        assertEquals(42, vehicle.getTurretArmour());
        assertEquals(42, vehicle.getFrontArmour());
        assertEquals(26, vehicle.getRearArmour());
        assertEquals(33, vehicle.getRightSideArmour());
        assertEquals(33, vehicle.getLeftSideArmour());
        assertTrue(vehicle.getWeapons().contains(WeaponFactory.createWeapon("Medium Laser")));
        assertTrue(vehicle.getWeapons().contains(WeaponFactory.createWeapon("LRM 10")));
        assertTrue(vehicle.getWeapons().contains(WeaponFactory.createWeapon("PPC")));
        assertTrue(vehicle.getWeapons().contains(WeaponFactory.createWeapon("SRM 6")));
    }

}
