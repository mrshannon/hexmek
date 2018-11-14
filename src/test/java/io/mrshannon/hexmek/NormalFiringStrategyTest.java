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
    private NormalFiringStrategy strategy;

    @Before
    public void setUp() throws Exception {
        mockedWeapon = mock(Weapon.class);
        mockedComponent = mock(Component.class);
        mockedUnit = mock(Unit.class);
        var records = new ArrayList<DamageRecord>();
        records.add(new Hit(mockedWeapon, mockedComponent, 23));
        when(mockedUnit.applyDamage(mockedWeapon, 23)).thenReturn(records);
//        hit = new Hit(mockedWeapon, mockedComponent, 23);
        strategy = new NormalFiringStrategy(23);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDamage() {
        assertEquals(23, strategy.getDamage());
    }

    @Test
    public void getHitChance() {
        assertEquals(1.0, strategy.getHitChance(2), 0.0001);
        assertEquals(0.9722222222222, strategy.getHitChance(3), 0.0001);
        assertEquals(0.9166666666665999, strategy.getHitChance(4), 0.0001);
        assertEquals(0.8333333333332999, strategy.getHitChance(5), 0.0001);
        assertEquals(0.7222222222222999, strategy.getHitChance(6), 0.0001);
        assertEquals(0.5833333333332999, strategy.getHitChance(7), 0.0001);
        assertEquals(0.4166666666662999, strategy.getHitChance(8), 0.0001);
        assertEquals(0.27777777777729995, strategy.getHitChance(9), 0.0001);
        assertEquals(0.16666666666629993, strategy.getHitChance(10), 0.0001);
        assertEquals(0.08333333333299993, strategy.getHitChance(11), 0.0001);
        assertEquals(0.027777777777399937, strategy.getHitChance(12), 0.0001);
        assertEquals(0.0, strategy.getHitChance(13), 0.0001);
    }

    @Test
    public void fire() {
        var records = strategy.fire(mockedWeapon, mockedUnit, 2);
        assertEquals(1, records.size());
        assertThat(records.get(0), instanceOf(Hit.class));
        assertEquals(mockedWeapon, records.get(0).getWeapon());
        assertEquals(mockedComponent, records.get(0).getComponent());
        assertEquals(23, records.get(0).getDamage());

        records = strategy.fire(mockedWeapon, mockedUnit, 13);
        assertEquals(1, records.size());
        assertThat(records.get(0), instanceOf(Miss.class));
        assertEquals(mockedWeapon, records.get(0).getWeapon());
        assertNull(records.get(0).getComponent());
        assertEquals(0, records.get(0).getDamage());
    }
}
