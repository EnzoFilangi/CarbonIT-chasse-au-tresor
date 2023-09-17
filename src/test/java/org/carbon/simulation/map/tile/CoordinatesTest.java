package org.carbon.simulation.map.tile;

import org.carbon.simulation.map.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {
    @Test
    public void should_Equal_If_Same_Values() {
        assertEquals(new Coordinates(1, 2), new Coordinates(1, 2));
    }

    @Test
    public void should_Not_Equal_If_X_Different() {
        assertNotEquals(new Coordinates(1, 2), new Coordinates(2, 2));
    }

    @Test
    public void should_Not_Equal_If_Y_Different() {
        assertNotEquals(new Coordinates(1, 2), new Coordinates(1, 1));
    }

    @Test
    public void should_Have_Same_Hash_If_Same_Values() {
        assertEquals(new Coordinates(1, 2).hashCode(), new Coordinates(1, 2).hashCode());
    }

    @Test
    public void should_Not_Have_Same_Hash_If_X_Different() {
        assertNotEquals(new Coordinates(1, 2).hashCode(), new Coordinates(2, 2).hashCode());
    }

    @Test
    public void should_Not_Have_Same_Hash_If_Y_Different() {
        assertNotEquals(new Coordinates(1, 2).hashCode(), new Coordinates(1, 1).hashCode());
    }
}
