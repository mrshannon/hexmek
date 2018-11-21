package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class VehicleTest {

    private Weapon mockWeapon;
    private RealComponent turret;
    private RealComponent front;
    private RealComponent rear;
    private RealComponent rightSide;
    private RealComponent leftSide;
    private Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        mockWeapon = mock(Weapon.class);
        var weapons = new ArrayList<Weapon>();
        weapons.add(mockWeapon);
        var map = (new MapLoader("clear")).createMap();
        turret = new RealComponent("Turret", 10, new NullComponent(), weapons);
        front = new RealComponent("Front", 20, new NullComponent(), weapons);
        rear = new RealComponent("Rear", 30, new NullComponent(), weapons);
        rightSide = new RealComponent("Right Side", 40, new NullComponent(), weapons);
        leftSide = new RealComponent("Left Side", 50, new NullComponent(), weapons);
        vehicle = new Vehicle("Test Vehicle", 'C', new Hex(4, 7), new SouthEast(),
                new MovementFactory(map, 3), turret, front, rear, rightSide, leftSide);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTurretArmour() {
        assertEquals(10, vehicle.getTurretArmour());
    }

    @Test
    public void getFrontArmour() {
        assertEquals(20, vehicle.getFrontArmour());
    }

    @Test
    public void getRearArmour() {
        assertEquals(30, vehicle.getRearArmour());
    }

    @Test
    public void getRightSideArmour() {
        assertEquals(40, vehicle.getRightSideArmour());
    }

    @Test
    public void getLeftSideArmour() {
        assertEquals(50, vehicle.getLeftSideArmour());
    }

    @Test
    public void isDestroyed() {
        assertFalse(vehicle.isDestroyed());
        vehicle.applyDamage(mockWeapon, 50);
        assertTrue(vehicle.isDestroyed());
    }

    @Test
    public void getComponents() {
        var components = vehicle.getComponents();
        assertEquals(5, components.size());
        assertTrue(components.contains(turret));
        assertTrue(components.contains(front));
        assertTrue(components.contains(rear));
        assertTrue(components.contains(rightSide));
        assertTrue(components.contains(leftSide));
    }

    @Test
    public void applyDamage() {
        var records = vehicle.applyDamage(mockWeapon, 10);
        assertTrue(records.size() >= 1);
        assertEquals(mockWeapon, records.get(0).getWeapon());
        assertEquals(10, records.get(0).getDamage());

        // Will never propagate damage.
        records = vehicle.applyDamage(mockWeapon, 100);
        assertEquals(1, records.size());
    }

    @Test
    public void getType() {
        assertEquals("Test Vehicle", vehicle.getType());
    }

    @Test
    public void getId() {
        assertEquals('C', vehicle.getId());
    }

    @Test
    public void getHex() {
        assertEquals(new Hex(4, 7), vehicle.getHex());
    }

    @Test
    public void getFacing() {
        assertEquals(new SouthEast(), vehicle.getFacing());
    }

    @Test
    public void getMovementPoints() throws MovementPointsExhaustedException {
        assertEquals(0, vehicle.getMovementPoints());
        vehicle.halt();
        assertEquals(0, vehicle.getMovementPoints());
        vehicle.cruise();
        assertEquals(3, vehicle.getMovementPoints());
        vehicle.flank();
        assertEquals(5, vehicle.getMovementPoints());
        vehicle.moveForward();
        assertEquals(4, vehicle.getMovementPoints());
    }

    @Test
    public void getToHitModifier() throws MovementPointsExhaustedException {
        assertEquals(0, vehicle.getToHitModifier());
        vehicle.flank();
        assertEquals(0, vehicle.getToHitModifier());
        vehicle.moveForward();
        vehicle.moveForward();
        vehicle.moveForward();
        vehicle.moveForward();
        vehicle.moveForward();
        assertEquals(2, vehicle.getToHitModifier());
    }

    @Test
    public void getGunneryModifier() {
        assertEquals(4+0, vehicle.getGunneryModifier());
        vehicle.halt();
        assertEquals(4+0, vehicle.getGunneryModifier());
        vehicle.cruise();
        assertEquals(4+1, vehicle.getGunneryModifier());
        vehicle.flank();
        assertEquals(4+2, vehicle.getGunneryModifier());
    }

    @Test
    public void canMove() throws MovementPointsExhaustedException {
        assertFalse(vehicle.canMove());
        vehicle.halt();
        assertFalse(vehicle.canMove());
        vehicle.cruise();
        assertTrue(vehicle.canMove());
        vehicle.flank();
        assertTrue(vehicle.canMove());
        vehicle.moveForward();
        vehicle.moveForward();
        vehicle.moveForward();
        vehicle.moveForward();
        vehicle.moveForward();
        assertFalse(vehicle.canMove());
    }

    @Test
    public void halt() {
        vehicle.halt();
        assertEquals(0, vehicle.getMovementPoints());
    }

    @Test
    public void cruise() {
        vehicle.cruise();
        assertEquals(3, vehicle.getMovementPoints());
    }

    @Test
    public void flank() {
        vehicle.flank();
        assertEquals(5, vehicle.getMovementPoints());
    }

    @Test
    public void moveForward() throws MovementPointsExhaustedException {
        vehicle.cruise();
        vehicle.moveForward();
        assertEquals(new Hex(5, 8), vehicle.getHex());
    }

    @Test
    public void moveBackward() throws MovementPointsExhaustedException {
        vehicle.cruise();
        vehicle.moveBackward();
        assertEquals(new Hex(3, 7), vehicle.getHex());
    }

    @Test
    public void rotateRight() throws MovementPointsExhaustedException {
        vehicle.cruise();
        vehicle.rotateRight();
        assertEquals(new South(), vehicle.getFacing());
    }

    @Test
    public void rotateLeft() throws MovementPointsExhaustedException {
        vehicle.cruise();
        vehicle.rotateLeft();
        assertEquals(new NorthEast(), vehicle.getFacing());
    }

    @Test
    public void getWeapons() {
        var weapons = vehicle.getWeapons();
        assertEquals(5, weapons.size());
        for (var weapon : weapons) {
            assertEquals(mockWeapon, weapon);
        }
    }

    @Test
    public void getArmour() {
        assertEquals(150, vehicle.getArmour());
        vehicle.applyDamage(mockWeapon, 5);
        assertEquals(145, vehicle.getArmour());
    }

    @Test
    public void getMaxArmour() {
        assertEquals(150, vehicle.getMaxArmour());
        vehicle.applyDamage(mockWeapon, 5);
        assertEquals(150, vehicle.getMaxArmour());
    }

    @Test
    public void getArmourPercent() {
        assertEquals(1.0, vehicle.getArmourPercent(), 0.0001);
        vehicle.applyDamage(mockWeapon, 5);
        assertEquals(0.9667, vehicle.getArmourPercent(), 0.0001);
    }

    @Test
    public void save() throws MovementPointsExhaustedException {
        vehicle.flank();
        vehicle.moveForward();
        vehicle.rotateRight();
        vehicle.moveForward();
        assertEquals(new Hex(5, 9), vehicle.getHex());
        assertEquals(new South(), vehicle.getFacing());
        assertEquals(2, vehicle.getMovementPoints());
        var saved = vehicle.save();
        vehicle.rotateRight();
        vehicle.moveForward();
        assertEquals(new Hex(4, 9), vehicle.getHex());
        assertEquals(new SouthWest(), vehicle.getFacing());
        assertEquals(0, vehicle.getMovementPoints());
        saved.restore();
        assertEquals(new Hex(5, 9), vehicle.getHex());
        assertEquals(new South(), vehicle.getFacing());
        assertEquals(2, vehicle.getMovementPoints());
    }

}