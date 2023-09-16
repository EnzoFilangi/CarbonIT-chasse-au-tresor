package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.Orientation;
import org.carbon.simulation.map.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultWalkingBehaviorTest {
    private DefaultWalkingBehavior defaultWalkingBehavior;

    @BeforeEach
    public void setUp() {
        defaultWalkingBehavior = new DefaultWalkingBehavior();
    }


    @Test
    public void should_Allow_Entry_If_Not_Occupied() {
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);

        assertTrue(defaultWalkingBehavior.canWalkIn(adventurer));
    }

    @Test
    public void should_Not_Allow_Entry_If_Occupied(){
        Adventurer firstAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);
        Adventurer secondAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);

        defaultWalkingBehavior.walkIn(firstAdventurer);

        assertFalse(defaultWalkingBehavior.canWalkIn(secondAdventurer));
    }

    @Test
    public void should_Allow_Entry_Again_After_Leaving() {
        Adventurer firstAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);
        Adventurer secondAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);

        defaultWalkingBehavior.walkIn(firstAdventurer);
        defaultWalkingBehavior.walkOut(firstAdventurer);

        assertTrue(defaultWalkingBehavior.canWalkIn(secondAdventurer));
    }

    @Test
    public void should_Throw_If_Forcing_Entry(){
        Adventurer firstAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);
        Adventurer secondAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);

        defaultWalkingBehavior.walkIn(firstAdventurer);

        assertThrows(RuntimeException.class, () -> defaultWalkingBehavior.walkIn(secondAdventurer));
    }

    @Test
    public void should_Return_True_On_WalkIn_Success(){
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);

        assertTrue(defaultWalkingBehavior.walkIn(adventurer));
    }

    @Test
    public void should_Return_True_On_WalkOut(){
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);

        assertTrue(defaultWalkingBehavior.walkOut(adventurer));
    }
}
