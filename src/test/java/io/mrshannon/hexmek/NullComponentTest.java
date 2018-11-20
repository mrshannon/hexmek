package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class NullComponentTest {

    Weapon mockWeapon;
    Component nullComponent;

    @Before
    public void setUp() throws Exception {
        nullComponent = new NullComponent();
        mockWeapon = mock(Weapon.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getType() {
        assertEquals("NULL Component", nullComponent.getType());
    }

    @Test
    public void isDestroyed() {
        assertTrue(nullComponent.isDestroyed());
    }

    @Test
    public void getArmour() {
        assertEquals(0, nullComponent.getArmour());
    }

    @Test
    public void getMaxArmour() {
        assertEquals(0, nullComponent.getMaxArmour());
    }

    @Test
    public void getWeapons() {
        var weapons = nullComponent.getWeapons();
        assertEquals(0, weapons.size());
    }

    @Test
    public void applyDamage() {
        var records = nullComponent.applyDamage(mockWeapon, 10);
        assertEquals(0, records.size());
    }
}
