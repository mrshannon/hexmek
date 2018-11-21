package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MechTest {

    private Weapon mockWeapon;
    private RealComponent head;
    private RealComponent centerTorso;
    private RealComponent rightTorso;
    private RealComponent leftTorso;
    private RealComponent rightArm;
    private RealComponent leftArm;
    private RealComponent rightLeg;
    private RealComponent leftLeg;
    private Mech mech;

    @Before
    public void setUp() throws Exception {
        mockWeapon = mock(Weapon.class);
        var weapons = new ArrayList<Weapon>();
        weapons.add(mockWeapon);
        var map = (new MapLoader("clear")).createMap();
        head = new RealComponent("Head", 60, new NullComponent(), weapons);
        centerTorso = new RealComponent("Center Torso", 80, new NullComponent(), weapons);
        rightTorso = new RealComponent("Right Torso", 75, centerTorso, weapons);
        leftTorso = new RealComponent("Left Torso", 40, centerTorso, weapons);
        rightArm = new RealComponent("Right Arm", 16, rightTorso, weapons);
        leftArm = new RealComponent("Left Arm", 13, leftTorso, weapons);
        rightLeg = new RealComponent("Right Leg", 33, rightTorso);
        leftLeg = new RealComponent("Left Leg", 43, leftTorso);
        mech = new Mech("Test Mech", 'C', new Hex(4, 7), new SouthEast(),
                new MovementFactory(map, 3), head, centerTorso, rightTorso,
                leftTorso, rightArm, leftArm, rightLeg, leftLeg);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getHeadArmour() {
        assertEquals(60, mech.getHeadArmour());
    }

    @Test
    public void getCenterTorsoArmour() {
        assertEquals(80, mech.getCenterTorsoArmour());
    }

    @Test
    public void getRightTorsoArmour() {
        assertEquals(75, mech.getRightTorsoArmour());
    }

    @Test
    public void getLeftTorsoArmour() {
        assertEquals(40, mech.getLeftTorsoArmour());
    }

    @Test
    public void getRightArmArmour() {
        assertEquals(16, mech.getRightArmArmour());
    }

    @Test
    public void getLeftArmArmour() {
        assertEquals(13, mech.getLeftArmArmour());
    }

    @Test
    public void getRightLegArmour() {
        assertEquals(33, mech.getRightLegArmour());
    }

    @Test
    public void getLeftLegArmour() {
        assertEquals(43, mech.getLeftLegArmour());
    }

    @Test
    public void isDestroyed() {
        assertFalse(mech.isDestroyed());
        mech.applyDamage(mockWeapon, 10000);
        assertTrue(mech.isDestroyed());
    }

    @Test
    public void getComponents() {
        var components = mech.getComponents();
        assertEquals(8, components.size());
        assertTrue(components.contains(head));
        assertTrue(components.contains(centerTorso));
        assertTrue(components.contains(rightTorso));
        assertTrue(components.contains(leftTorso));
        assertTrue(components.contains(rightArm));
        assertTrue(components.contains(leftArm));
        assertTrue(components.contains(rightLeg));
        assertTrue(components.contains(leftLeg));
    }

    @Test
    public void applyDamage() {
        var records = mech.applyDamage(mockWeapon, 300);
        assertTrue(records.size() >= 1);
        assertEquals(mockWeapon, records.get(0).getWeapon());
    }

    @Test
    public void getType() {
        assertEquals("Test Mech", mech.getType());
    }

    @Test
    public void getId() {
        assertEquals('C', mech.getId());
    }

    @Test
    public void getHex() {
        assertEquals(new Hex(4, 7), mech.getHex());
    }

    @Test
    public void getFacing() {
        assertEquals(new SouthEast(), mech.getFacing());
    }

    @Test
    public void getMovementPoints() throws MovementPointsExhaustedException {
        assertEquals(0, mech.getMovementPoints());
        mech.halt();
        assertEquals(0, mech.getMovementPoints());
        mech.cruise();
        assertEquals(3, mech.getMovementPoints());
        mech.flank();
        assertEquals(5, mech.getMovementPoints());
        mech.moveForward();
        assertEquals(4, mech.getMovementPoints());
    }

    @Test
    public void getCruiseMovementPoints() {
        assertEquals(3, mech.getCruiseMovementPoints());
    }

    @Test
    public void getFlankMovementPoints() {
        assertEquals(5, mech.getFlankMovementPoints());
    }

    @Test
    public void getToHitModifier() throws MovementPointsExhaustedException {
        assertEquals(0, mech.getToHitModifier());
        mech.flank();
        assertEquals(0, mech.getToHitModifier());
        mech.moveForward();
        mech.moveForward();
        mech.moveForward();
        mech.moveForward();
        mech.moveForward();
        assertEquals(2, mech.getToHitModifier());
    }

    @Test
    public void getGunneryModifier() {
        assertEquals(4+0, mech.getGunneryModifier());
        mech.halt();
        assertEquals(4+0, mech.getGunneryModifier());
        mech.cruise();
        assertEquals(4+1, mech.getGunneryModifier());
        mech.flank();
        assertEquals(4+2, mech.getGunneryModifier());
    }

    @Test
    public void canMove() throws MovementPointsExhaustedException {
        assertFalse(mech.canMove());
        mech.halt();
        assertFalse(mech.canMove());
        mech.cruise();
        assertTrue(mech.canMove());
        mech.flank();
        assertTrue(mech.canMove());
        mech.moveForward();
        mech.moveForward();
        mech.moveForward();
        mech.moveForward();
        mech.moveForward();
        assertFalse(mech.canMove());
    }

    @Test
    public void halt() {
        mech.halt();
        assertEquals(0, mech.getMovementPoints());
    }

    @Test
    public void cruise() {
        mech.cruise();
        assertEquals(3, mech.getMovementPoints());
    }

    @Test
    public void flank() {
        mech.flank();
        assertEquals(5, mech.getMovementPoints());
    }

    @Test
    public void moveForward() throws MovementPointsExhaustedException {
        mech.cruise();
        mech.moveForward();
        assertEquals(new Hex(5, 8), mech.getHex());
    }

    @Test
    public void moveBackward() throws MovementPointsExhaustedException {
        mech.cruise();
        mech.moveBackward();
        assertEquals(new Hex(3, 7), mech.getHex());
    }

    @Test
    public void rotateRight() throws MovementPointsExhaustedException {
        mech.cruise();
        mech.rotateRight();
        assertEquals(new South(), mech.getFacing());
    }

    @Test
    public void rotateLeft() throws MovementPointsExhaustedException {
        mech.cruise();
        mech.rotateLeft();
        assertEquals(new NorthEast(), mech.getFacing());
    }

    @Test
    public void getWeapons() {
        var weapons = mech.getWeapons();
        assertEquals(6, weapons.size());
        for (var weapon : weapons) {
            assertEquals(mockWeapon, weapon);
        }

        // test destruction of weapons
        mech.applyDamage(mockWeapon, 200);
        assertTrue(mech.getWeapons().size() < 6);
    }

    @Test
    public void getArmour() {
        assertEquals(360, mech.getArmour());
        mech.applyDamage(mockWeapon, 55);
        assertEquals(305, mech.getArmour());
    }

    @Test
    public void getMaxArmour() {
        assertEquals(360, mech.getMaxArmour());
        mech.applyDamage(mockWeapon, 55);
        assertEquals(360, mech.getMaxArmour());
    }

    @Test
    public void getArmourPercent() {
        assertEquals(1.0, mech.getArmourPercent(), 0.0001);
        mech.applyDamage(mockWeapon, 55);
        assertEquals(0.8472, mech.getArmourPercent(), 0.0001);
    }

    @Test
    public void save() throws MovementPointsExhaustedException {
        mech.flank();
        mech.moveForward();
        mech.rotateRight();
        mech.moveForward();
        assertEquals(new Hex(5, 9), mech.getHex());
        assertEquals(new South(), mech.getFacing());
        assertEquals(2, mech.getMovementPoints());
        var saved = mech.save();
        mech.rotateRight();
        mech.moveForward();
        assertEquals(new Hex(4, 9), mech.getHex());
        assertEquals(new SouthWest(), mech.getFacing());
        assertEquals(0, mech.getMovementPoints());
        saved.restore();
        assertEquals(new Hex(5, 9), mech.getHex());
        assertEquals(new South(), mech.getFacing());
        assertEquals(2, mech.getMovementPoints());
    }
}
