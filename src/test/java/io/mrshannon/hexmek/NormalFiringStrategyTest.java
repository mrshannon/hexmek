package io.mrshannon.hexmek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;


public class NormalFiringStrategyTest {

    private Unit mockedUnit;
    private Weapon mockedWeapon;
    private Component mockedComponent;
    private NormalFiringStrategy normalStrategy;

    @Before
    public void setUp() throws Exception {
        mockedWeapon = mock(Weapon.class);
        mockedComponent = mock(Component.class);
        mockedUnit = mock(Unit.class);
        var records = new ArrayList<DamageRecord>();
        records.add(new Hit(mockedWeapon, mockedComponent, 23));
        when(mockedUnit.applyDamage(mockedWeapon, 23)).thenReturn(records);
        normalStrategy = new NormalFiringStrategy(23);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDamage() {
        assertEquals(23, normalStrategy.getDamage());
    }

    @Test
    public void getHitChance() {
        assertEquals(1.0, normalStrategy.getHitChance(2), 0.0001);
        assertEquals(0.9722222222222, normalStrategy.getHitChance(3), 0.0001);
        assertEquals(0.9166666666665999, normalStrategy.getHitChance(4), 0.0001);
        assertEquals(0.8333333333332999, normalStrategy.getHitChance(5), 0.0001);
        assertEquals(0.7222222222222999, normalStrategy.getHitChance(6), 0.0001);
        assertEquals(0.5833333333332999, normalStrategy.getHitChance(7), 0.0001);
        assertEquals(0.4166666666662999, normalStrategy.getHitChance(8), 0.0001);
        assertEquals(0.27777777777729995, normalStrategy.getHitChance(9), 0.0001);
        assertEquals(0.16666666666629993, normalStrategy.getHitChance(10), 0.0001);
        assertEquals(0.08333333333299993, normalStrategy.getHitChance(11), 0.0001);
        assertEquals(0.027777777777399937, normalStrategy.getHitChance(12), 0.0001);
        assertEquals(0.0, normalStrategy.getHitChance(13), 0.0001);
    }

    @Test
    public void fire() {
        var records = normalStrategy.fire(mockedWeapon, mockedUnit, 2);
        assertEquals(1, records.size());
        assertThat(records.get(0), instanceOf(Hit.class));
        assertEquals(mockedWeapon, records.get(0).getWeapon());
        assertEquals(mockedComponent, records.get(0).getComponent());
        assertEquals(23, records.get(0).getDamage());

        records = normalStrategy.fire(mockedWeapon, mockedUnit, 13);
        assertEquals(1, records.size());
        assertThat(records.get(0), instanceOf(Miss.class));
        assertEquals(mockedWeapon, records.get(0).getWeapon());
        assertNull(records.get(0).getComponent());
        assertEquals(0, records.get(0).getDamage());
    }
}
