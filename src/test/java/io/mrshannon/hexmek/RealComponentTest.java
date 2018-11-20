package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.*;

public class RealComponentTest {

    private Weapon mockWeapon;

    private Component destroyedComponent;
    private Component componentA;
    private Component componentB;
    private Component componentC;

    @Before
    public void setUp() throws Exception {
        destroyedComponent = new RealComponent("Destroyed Component", 0);
        componentA = new RealComponent("Component A", 5);
        componentB = new RealComponent("Component B", 20, componentA);
        mockWeapon = mock(Weapon.class);
        var weapons = new ArrayList<Weapon>();
        weapons.add(mockWeapon);
        componentC = new RealComponent("Component C", 40, componentB, weapons);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getType() {
        assertEquals("Destroyed Component", destroyedComponent.getType());
        assertEquals("Component A", componentA.getType());
        assertEquals("Component B", componentB.getType());
        assertEquals("Component C", componentC.getType());
    }

    @Test
    public void isDestroyed() {
        assertTrue(destroyedComponent.isDestroyed());
        assertFalse(componentA.isDestroyed());
        assertFalse(componentB.isDestroyed());
        assertFalse(componentC.isDestroyed());
    }

    @Test
    public void getArmour() {
        assertEquals(0, destroyedComponent.getArmour());
        assertEquals(5, componentA.getArmour());
        assertEquals(20, componentB.getArmour());
        assertEquals(40, componentC.getArmour());
    }

    @Test
    public void getMaxArmour() {
        assertEquals(0, destroyedComponent.getMaxArmour());
        assertEquals(5, componentA.getMaxArmour());
        assertEquals(20, componentB.getMaxArmour());
        assertEquals(40, componentC.getMaxArmour());
    }

    @Test
    public void getWeapons() {
        assertEquals(0, destroyedComponent.getWeapons().size());
        assertEquals(0, componentA.getWeapons().size());
        assertEquals(0, componentB.getWeapons().size());
        assertEquals(1, componentC.getWeapons().size());
        assertEquals(mockWeapon, componentC.getWeapons().get(0));
    }

    @Test
    public void applyDamage() {
        List<DamageRecord> records;

        records = destroyedComponent.applyDamage(mockWeapon, 5);
        assertEquals(0, destroyedComponent.getArmour());
        assertEquals(0, records.size());

        records = componentC.applyDamage(mockWeapon, 62);
        assertFalse(componentA.isDestroyed());
        assertEquals(3, componentA.getArmour());
        assertEquals(5, componentA.getMaxArmour());
        assertTrue(componentB.isDestroyed());
        assertEquals(0, componentB.getArmour());
        assertEquals(20, componentB.getMaxArmour());
        assertTrue(componentC.isDestroyed());
        assertEquals(0, componentC.getArmour());
        assertEquals(40, componentC.getMaxArmour());
        assertEquals(3, records.size());
        assertThat(records.get(0), instanceOf(Destroyed.class));
        assertEquals(componentC, records.get(0).getComponent());
        assertEquals(mockWeapon, records.get(0).getWeapon());
        assertEquals(40, records.get(0).getDamage());
        assertThat(records.get(1), instanceOf(Destroyed.class));
        assertEquals(componentB, records.get(1).getComponent());
        assertEquals(mockWeapon, records.get(1).getWeapon());
        assertEquals(20, records.get(1).getDamage());
        assertThat(records.get(2), instanceOf(Hit.class));
        assertEquals(componentA, records.get(2).getComponent());
        assertEquals(mockWeapon, records.get(2).getWeapon());
        assertEquals(2, records.get(2).getDamage());
    }
}
