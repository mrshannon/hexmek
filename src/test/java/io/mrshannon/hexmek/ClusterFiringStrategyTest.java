package io.mrshannon.hexmek;

import io.mrshannon.hexmek.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;


public class ClusterFiringStrategyTest {

    private Unit mockedUnit;
    private Weapon mockedWeapon;
    private Component mockedComponent;
    private ClusterFiringStrategy clusterStrategy;
    private ClusterFiringStrategy largeClusterStrategy;

    @Before
    public void setUp() throws Exception {
        mockedWeapon = mock(Weapon.class);
        mockedComponent = mock(Component.class);
        mockedUnit = mock(Unit.class);
        var records = new ArrayList<DamageRecord>();
        records.add(new Hit(mockedWeapon, mockedComponent, 2));
        when(mockedUnit.applyDamage(mockedWeapon, 2)).thenReturn(records);
        var largeRecords = new ArrayList<DamageRecord>();
        largeRecords.add(new Hit(mockedWeapon, mockedComponent, 1));
        when(mockedUnit.applyDamage(mockedWeapon, 1)).thenReturn(largeRecords);
        clusterStrategy = new ClusterFiringStrategy(2, 4);
        largeClusterStrategy = new ClusterFiringStrategy(1, 20);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDamage() {
        assertEquals(2*4, clusterStrategy.getDamage());
        assertEquals(1*20, largeClusterStrategy.getDamage());
    }

    @Test
    public void getHitChance() {
        assertEquals(1.0, clusterStrategy.getHitChance(2), 0.0001);
        assertEquals(0.9722222222222, clusterStrategy.getHitChance(3), 0.0001);
        assertEquals(0.9166666666665999, clusterStrategy.getHitChance(4), 0.0001);
        assertEquals(0.8333333333332999, clusterStrategy.getHitChance(5), 0.0001);
        assertEquals(0.7222222222222999, clusterStrategy.getHitChance(6), 0.0001);
        assertEquals(0.5833333333332999, clusterStrategy.getHitChance(7), 0.0001);
        assertEquals(0.4166666666662999, clusterStrategy.getHitChance(8), 0.0001);
        assertEquals(0.27777777777729995, clusterStrategy.getHitChance(9), 0.0001);
        assertEquals(0.16666666666629993, clusterStrategy.getHitChance(10), 0.0001);
        assertEquals(0.08333333333299993, clusterStrategy.getHitChance(11), 0.0001);
        assertEquals(0.027777777777399937, clusterStrategy.getHitChance(12), 0.0001);
        assertEquals(0.0, clusterStrategy.getHitChance(13), 0.0001);
    }


    @Test
    public void clusterHits() {
        ClusterFiringStrategy cluster;

        cluster = new ClusterFiringStrategy(1, 2);
        assertEquals(1, cluster.clusterHits(2));
        assertEquals(1, cluster.clusterHits(3));
        assertEquals(1, cluster.clusterHits(4));
        assertEquals(1, cluster.clusterHits(5));
        assertEquals(1, cluster.clusterHits(6));
        assertEquals(1, cluster.clusterHits(7));
        assertEquals(2, cluster.clusterHits(8));
        assertEquals(2, cluster.clusterHits(9));
        assertEquals(2, cluster.clusterHits(10));
        assertEquals(2, cluster.clusterHits(11));
        assertEquals(2, cluster.clusterHits(12));

        cluster = new ClusterFiringStrategy(1, 8);
        assertEquals(3, cluster.clusterHits(2));
        assertEquals(3, cluster.clusterHits(3));
        assertEquals(4, cluster.clusterHits(4));
        assertEquals(4, cluster.clusterHits(5));
        assertEquals(5, cluster.clusterHits(6));
        assertEquals(5, cluster.clusterHits(7));
        assertEquals(5, cluster.clusterHits(8));
        assertEquals(6, cluster.clusterHits(9));
        assertEquals(6, cluster.clusterHits(10));
        assertEquals(8, cluster.clusterHits(11));
        assertEquals(8, cluster.clusterHits(12));

        cluster = new ClusterFiringStrategy(1, 11);
        assertEquals(4, cluster.clusterHits(2));
        assertEquals(4, cluster.clusterHits(3));
        assertEquals(5, cluster.clusterHits(4));
        assertEquals(7, cluster.clusterHits(5));
        assertEquals(7, cluster.clusterHits(6));
        assertEquals(7, cluster.clusterHits(7));
        assertEquals(7, cluster.clusterHits(8));
        assertEquals(9, cluster.clusterHits(9));
        assertEquals(9, cluster.clusterHits(10));
        assertEquals(11, cluster.clusterHits(11));
        assertEquals(11, cluster.clusterHits(12));

        cluster = new ClusterFiringStrategy(1, 16);
        assertEquals(5, cluster.clusterHits(2));
        assertEquals(5, cluster.clusterHits(3));
        assertEquals(7, cluster.clusterHits(4));
        assertEquals(10, cluster.clusterHits(5));
        assertEquals(10, cluster.clusterHits(6));
        assertEquals(10, cluster.clusterHits(7));
        assertEquals(10, cluster.clusterHits(8));
        assertEquals(13, cluster.clusterHits(9));
        assertEquals(13, cluster.clusterHits(10));
        assertEquals(16, cluster.clusterHits(11));
        assertEquals(16, cluster.clusterHits(12));

        cluster = new ClusterFiringStrategy(1, 30);
        assertEquals(10, cluster.clusterHits(2));
        assertEquals(10, cluster.clusterHits(3));
        assertEquals(12, cluster.clusterHits(4));
        assertEquals(18, cluster.clusterHits(5));
        assertEquals(18, cluster.clusterHits(6));
        assertEquals(18, cluster.clusterHits(7));
        assertEquals(18, cluster.clusterHits(8));
        assertEquals(24, cluster.clusterHits(9));
        assertEquals(24, cluster.clusterHits(10));
        assertEquals(30, cluster.clusterHits(11));
        assertEquals(30, cluster.clusterHits(12));
    }

    @Test
    public void fire() {
        List<DamageRecord> records;

        for (int i = 1; i <= 1000; ++i) {
            records = clusterStrategy.fire(mockedWeapon, mockedUnit, 2);
            assertTrue(records.size() >= 1);
            assertTrue(records.size() <= 4);
            for (var record : records) {
                assertThat(record, instanceOf(Hit.class));
                assertEquals(mockedWeapon, record.getWeapon());
                assertEquals(mockedComponent, record.getComponent());
                assertEquals(2, record.getDamage());
            }
        }

        for (int i = 1; i <= 1000; ++i) {
            records = largeClusterStrategy.fire(mockedWeapon, mockedUnit, 2);
            assertTrue(records.size() >= 6);
            assertTrue(records.size() <= 20);
            for (var record : records) {
                assertThat(record, instanceOf(Hit.class));
                assertEquals(mockedWeapon, record.getWeapon());
                assertEquals(mockedComponent, record.getComponent());
                assertEquals(1, record.getDamage());
            }
        }
    }
}