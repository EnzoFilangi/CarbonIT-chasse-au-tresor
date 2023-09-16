package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.Orientation;
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
    public void should_Never_Allow_Entry() {
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        assertFalse(noWalkingBehavior.canWalkIn(adventurer));
    }

    @Test
    public void should_Throw_If_Forcing_Entry(){
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        assertThrows(RuntimeException.class, () -> noWalkingBehavior.walkIn(adventurer));
    }

    @Test
    public void should_Throw_If_Walking_Out(){
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        assertThrows(RuntimeException.class, () -> noWalkingBehavior.walkOut(adventurer));
    }
}
