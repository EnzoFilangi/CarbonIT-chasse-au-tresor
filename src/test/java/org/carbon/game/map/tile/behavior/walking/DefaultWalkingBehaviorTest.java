package org.carbon.game.map.tile.behavior.walking;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultWalkingBehaviorTest {
    private DefaultWalkingBehavior defaultWalkingBehavior;
    
    @BeforeEach
    public void setUp(){
        defaultWalkingBehavior = new DefaultWalkingBehavior();
    }
    
    @Test
    public void should_Move_Adventurer_When_Not_Occupied(){
        Adventurer adventurer = new Adventurer(new Coordinates(0,0));
        Coordinates expectedCoordinates = new Coordinates(1, 1);

        defaultWalkingBehavior.walkIn(adventurer, expectedCoordinates);

        assertEquals(expectedCoordinates.getX(), adventurer.getCoordinates().getX());
        assertEquals(expectedCoordinates.getY(), adventurer.getCoordinates().getY());
    }
    
    @Test
    public void should_Not_Move_Adventurer_When_Occupied(){
        Adventurer firstAdventurer = new Adventurer(new Coordinates(0,0));
        Adventurer secondAdventurer = new Adventurer(new Coordinates(1,1));
        Coordinates expectedCoordinates = new Coordinates(2, 2);

        defaultWalkingBehavior.walkIn(firstAdventurer, expectedCoordinates);
        defaultWalkingBehavior.walkIn(secondAdventurer, expectedCoordinates);

        assertEquals(1, secondAdventurer.getCoordinates().getX());
        assertEquals(1, secondAdventurer.getCoordinates().getY());
    }
    
    @Test
    public void should_Move_Adventurer_After_Occupying_Adventurer_Left(){
        Adventurer firstAdventurer = new Adventurer(new Coordinates(0,0));
        Adventurer secondAdventurer = new Adventurer(new Coordinates(1,1));
        Coordinates expectedCoordinates = new Coordinates(2, 2);

        defaultWalkingBehavior.walkIn(firstAdventurer, expectedCoordinates);
        defaultWalkingBehavior.walkOut(firstAdventurer, expectedCoordinates);
        defaultWalkingBehavior.walkIn(secondAdventurer, expectedCoordinates);

        assertEquals(expectedCoordinates.getX(), secondAdventurer.getCoordinates().getX());
        assertEquals(expectedCoordinates.getY(), secondAdventurer.getCoordinates().getY());
    }

    @Test
    public void should_Return_True_On_WalkIn_Success(){
        Adventurer adventurer = new Adventurer(new Coordinates(0,0));
        Coordinates tileCoordinates = new Coordinates(1, 1);

        assertTrue(defaultWalkingBehavior.walkIn(adventurer, tileCoordinates));
    }

    @Test
    public void should_Return_False_On_WalkIn_Failure(){
        Adventurer firstAdventurer = new Adventurer(new Coordinates(0,0));
        Adventurer secondAdventurer = new Adventurer(new Coordinates(0,0));
        Coordinates tileCoordinates = new Coordinates(1, 1);

        defaultWalkingBehavior.walkIn(firstAdventurer, tileCoordinates);
        assertFalse(defaultWalkingBehavior.walkIn(secondAdventurer, tileCoordinates));
    }

    @Test
    public void should_Return_True_On_WalkOut(){
        Adventurer adventurer = new Adventurer(new Coordinates(0,0));
        Coordinates tileCoordinates = new Coordinates(1, 1);

        assertTrue(defaultWalkingBehavior.walkOut(adventurer, tileCoordinates));
    }
}
