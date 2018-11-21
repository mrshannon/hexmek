package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MechBuilderTest {

    private HexMap map;
    private Weapon mockWeapon;
    private MechBuilder builder;

    @Before
    public void setUp() throws Exception {
        mockWeapon = mock(Weapon.class);
        map = (new MapLoader("clear")).createMap();
        builder = new MechBuilder("Test Mech", 4);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void head() {
        builder.head(10);
        builder.head(mockWeapon);
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(10, mech.getHeadArmour());
        assertEquals(1, mech.getWeapons().size());
        assertEquals(mockWeapon, mech.getWeapons().get(0));
    }

    @Test
    public void centerTorso() {
        builder.centerTorso(20);
        builder.centerTorso(mockWeapon);
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(20, mech.getCenterTorsoArmour());
        assertEquals(1, mech.getWeapons().size());
        assertEquals(mockWeapon, mech.getWeapons().get(0));
    }

    @Test
    public void rightTorso() {
        builder.rightTorso(30);
        builder.rightTorso(mockWeapon);
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(30, mech.getRightTorsoArmour());
        assertEquals(1, mech.getWeapons().size());
        assertEquals(mockWeapon, mech.getWeapons().get(0));
    }

    @Test
    public void leftTorso() {
        builder.leftTorso(40);
        builder.leftTorso(mockWeapon);
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(40, mech.getLeftTorsoArmour());
        assertEquals(1, mech.getWeapons().size());
        assertEquals(mockWeapon, mech.getWeapons().get(0));
    }

    @Test
    public void rightArm() {
        builder.rightArm(50);
        builder.rightArm(mockWeapon);
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(50, mech.getRightArmArmour());
        assertEquals(1, mech.getWeapons().size());
        assertEquals(mockWeapon, mech.getWeapons().get(0));
    }

    @Test
    public void leftArm() {
        builder.leftArm(60);
        builder.leftArm(mockWeapon);
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(60, mech.getLeftArmArmour());
        assertEquals(1, mech.getWeapons().size());
        assertEquals(mockWeapon, mech.getWeapons().get(0));
    }

    @Test
    public void rightLeg() {
        builder.rightLeg(70);
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(70, mech.getRightLegArmour());
    }

    @Test
    public void leftLeg() {
        builder.rightLeg(80);
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals(80, mech.getRightLegArmour());
    }

    @Test
    public void build() {
        Mech mech = (Mech) builder.build('C', map, new Hex(4, 7), new SouthEast());
        assertEquals('C', mech.getId());
        assertEquals(new Hex(4, 7), mech.getHex());
        assertEquals(new SouthEast(), mech.getFacing());
        assertEquals("Test Mech", mech.getType());
        mech.cruise();
        assertEquals(4, mech.getMovementPoints());
    }
}
