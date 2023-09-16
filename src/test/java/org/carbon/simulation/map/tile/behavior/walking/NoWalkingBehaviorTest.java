package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.Adventurer;
import org.carbon.simulation.map.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoWalkingBehaviorTest {
    private NoWalkingBehavior noWalkingBehavior;

    @BeforeEach
    public void setUp(){
        noWalkingBehavior = new NoWalkingBehavior();
    }

    @Test
    public void should_Not_Move_Adventurer_On_WalkIn(){
        Adventurer adventurer = new Adventurer(new Coordinates(0, 0));
        Coordinates tileCoordinates = new Coordinates(1, 1);

        noWalkingBehavior.walkIn(adventurer, tileCoordinates);

        assertEquals(0, adventurer.getCoordinates().getX());
        assertEquals(0, adventurer.getCoordinates().getY());
    }

    @Test
    public void should_Throw_On_WalkOut(){
        Adventurer adventurer = new Adventurer(new Coordinates(0, 0));
        Coordinates tileCoordinates = new Coordinates(1, 1);

        assertThrows(RuntimeException.class, () -> noWalkingBehavior.walkOut(adventurer, tileCoordinates));
    }

    @Test
    public void should_Return_False_On_WalkIn(){
        Adventurer adventurer = new Adventurer(new Coordinates(0,0));
        Coordinates tileCoordinates = new Coordinates(1, 1);

        assertFalse(noWalkingBehavior.walkIn(adventurer, tileCoordinates));
    }
}
