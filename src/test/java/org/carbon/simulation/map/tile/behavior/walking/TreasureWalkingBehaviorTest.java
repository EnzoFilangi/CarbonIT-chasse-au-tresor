package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.Orientation;
import org.carbon.simulation.map.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreasureWalkingBehaviorTest {
    private TreasureWalkingBehavior treasureWalkingBehavior;

    @BeforeEach
    public void setUp(){
        treasureWalkingBehavior = new TreasureWalkingBehavior(2);
    }


    @Test
    public void should_Allow_Entry_If_Not_Occupied() {
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        assertTrue(treasureWalkingBehavior.canWalkIn(adventurer));
    }

    @Test
    public void should_Not_Allow_Entry_If_Occupied(){
        Adventurer firstAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));
        Adventurer secondAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        treasureWalkingBehavior.walkIn(firstAdventurer);

        assertFalse(treasureWalkingBehavior.canWalkIn(secondAdventurer));
    }

    @Test
    public void should_Allow_Entry_Again_After_Leaving() {
        Adventurer firstAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));
        Adventurer secondAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        treasureWalkingBehavior.walkIn(firstAdventurer);
        treasureWalkingBehavior.walkOut(firstAdventurer);

        assertTrue(treasureWalkingBehavior.canWalkIn(secondAdventurer));
    }

    @Test
    public void should_Throw_If_Forcing_Entry(){
        Adventurer firstAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));
        Adventurer secondAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        treasureWalkingBehavior.walkIn(firstAdventurer);

        assertThrows(RuntimeException.class, () -> treasureWalkingBehavior.walkIn(secondAdventurer));
    }

    @Test
    public void should_Return_True_On_WalkIn_Success(){
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        assertTrue(treasureWalkingBehavior.walkIn(adventurer));
    }

    @Test
    public void should_Return_True_On_WalkOut(){
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        assertTrue(treasureWalkingBehavior.walkOut(adventurer));
    }

    @Test
    public void should_Increment_Adventurer_Treasures(){
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        treasureWalkingBehavior.walkIn(adventurer);

        assertEquals(1, adventurer.getTreasureQuantity());
    }

    @Test
    public void should_Not_Increment_Adventurer_Treasures_When_Forcing_Entry(){
        Adventurer firstAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));
        Adventurer secondAdventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        treasureWalkingBehavior.walkIn(firstAdventurer);
        try {
            treasureWalkingBehavior.walkIn(secondAdventurer);
        } catch (RuntimeException ignored){}

        assertEquals(0, secondAdventurer.getTreasureQuantity());
    }

    @Test
    public void should_Not_Increment_Adventurer_Treasures_When_No_Treasures_Left(){
        treasureWalkingBehavior = new TreasureWalkingBehavior(0);
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0));

        treasureWalkingBehavior.walkIn(adventurer);

        assertEquals(0, adventurer.getTreasureQuantity());
    }

    @Test
    public void should_Return_Quantity_Of_Treasures(){
        int expectedTreasureQuantity = 1;
        treasureWalkingBehavior = new TreasureWalkingBehavior(expectedTreasureQuantity);

        assertEquals(expectedTreasureQuantity, treasureWalkingBehavior.getTreasureQuantity());
    }
}
