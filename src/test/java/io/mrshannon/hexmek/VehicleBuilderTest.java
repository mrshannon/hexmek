package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VehicleBuilderTest {

    private HexMap map;
    private Weapon mockWeapon;
    private VehicleBuilder builder;

    @Before
    public void setUp() throws Exception {
        mockWeapon = mock(Weapon.class);
        map = (new MapLoader("clear").createMap());
        builder = new VehicleBuilder("Test Vehicle", 4);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void turret() {
        builder.turret(10);
        builder.turret(mockWeapon);
        Vehicle vehicle = (Vehicle) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(10, vehicle.getTurretArmour());
        assertEquals(1, vehicle.getWeapons().size());
        assertEquals(mockWeapon, vehicle.getWeapons().get(0));
    }

    @Test
    public void front() {
        builder.front(20);
        builder.front(mockWeapon);
        Vehicle vehicle = (Vehicle) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(20, vehicle.getFrontArmour());
        assertEquals(1, vehicle.getWeapons().size());
        assertEquals(mockWeapon, vehicle.getWeapons().get(0));
    }

    @Test
    public void rear() {
        builder.rear(30);
        builder.rear(mockWeapon);
        Vehicle vehicle = (Vehicle) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(30, vehicle.getRearArmour());
        assertEquals(1, vehicle.getWeapons().size());
        assertEquals(mockWeapon, vehicle.getWeapons().get(0));
    }

    @Test
    public void rightSide() {
        builder.rightSide(40);
        builder.rightSide(mockWeapon);
        Vehicle vehicle = (Vehicle) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(40, vehicle.getRightSideArmour());
        assertEquals(1, vehicle.getWeapons().size());
        assertEquals(mockWeapon, vehicle.getWeapons().get(0));
    }

    @Test
    public void leftSide() {
        builder.leftSide(50);
        builder.leftSide(mockWeapon);
        Vehicle vehicle = (Vehicle) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(50, vehicle.getLeftSideArmour());
        assertEquals(1, vehicle.getWeapons().size());
        assertEquals(mockWeapon, vehicle.getWeapons().get(0));
    }

    @Test
    public void build() {
        Vehicle vehicle = (Vehicle) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals('C', vehicle.getId());
        assertEquals(new Hex(4, 7), vehicle.getHex());
        assertEquals(new SouthEast(), vehicle.getFacing());
        assertEquals("Test Vehicle", vehicle.getType());
        vehicle.cruise();
        assertEquals(4, vehicle.getMovementPoints());
    }
}
