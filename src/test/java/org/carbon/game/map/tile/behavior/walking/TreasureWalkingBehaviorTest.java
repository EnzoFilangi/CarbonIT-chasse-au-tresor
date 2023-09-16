package org.carbon.game.map.tile.behavior.walking;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreasureWalkingBehaviorTest {
    private TreasureWalkingBehavior treasureWalkingBehavior;

    @BeforeEach
    public void setUp(){
        treasureWalkingBehavior = new TreasureWalkingBehavior(2);
    }

    @Test
    public void should_Move_Adventurer_When_Not_Occupied(){
        Adventurer adventurer = new Adventurer(new Coordinates(0,0));
        Coordinates expectedCoordinates = new Coordinates(1, 1);

        treasureWalkingBehavior.walkIn(adventurer, expectedCoordinates);

        assertEquals(expectedCoordinates.getX(), adventurer.getCoordinates().getX());
        assertEquals(expectedCoordinates.getY(), adventurer.getCoordinates().getY());
    }

    @Test
    public void should_Not_Move_Adventurer_When_Occupied(){
        Adventurer firstAdventurer = new Adventurer(new Coordinates(0,0));
        Adventurer secondAdventurer = new Adventurer(new Coordinates(1,1));
        Coordinates expectedCoordinates = new Coordinates(2, 2);

        treasureWalkingBehavior.walkIn(firstAdventurer, expectedCoordinates);
        treasureWalkingBehavior.walkIn(secondAdventurer, expectedCoordinates);

        assertEquals(1, secondAdventurer.getCoordinates().getX());
        assertEquals(1, secondAdventurer.getCoordinates().getY());
    }

    @Test
    public void should_Move_Adventurer_After_Occupying_Adventurer_Left(){
        Adventurer firstAdventurer = new Adventurer(new Coordinates(0,0));
        Adventurer secondAdventurer = new Adventurer(new Coordinates(1,1));
        Coordinates expectedCoordinates = new Coordinates(2, 2);

        treasureWalkingBehavior.walkIn(firstAdventurer, expectedCoordinates);
        treasureWalkingBehavior.walkOut(firstAdventurer, expectedCoordinates);
        treasureWalkingBehavior.walkIn(secondAdventurer, expectedCoordinates);

        assertEquals(expectedCoordinates.getX(), secondAdventurer.getCoordinates().getX());
        assertEquals(expectedCoordinates.getY(), secondAdventurer.getCoordinates().getY());
    }

    @Test
    public void should_Increment_Adventurer_Treasures(){
        Adventurer adventurer = new Adventurer(new Coordinates(0,0));
        Coordinates tileCoordinates = new Coordinates(1, 1);

        treasureWalkingBehavior.walkIn(adventurer, tileCoordinates);

        assertEquals(1, adventurer.getTreasureQuantity());
    }

    @Test
    public void should_Not_Increment_Adventurer_Treasures_When_Move_Fails(){
        Adventurer firstAdventurer = new Adventurer(new Coordinates(0,0));
        Adventurer secondAdventurer = new Adventurer(new Coordinates(1,1));
        Coordinates expectedCoordinates = new Coordinates(2, 2);

        treasureWalkingBehavior.walkIn(firstAdventurer, expectedCoordinates);
        treasureWalkingBehavior.walkIn(secondAdventurer, expectedCoordinates);

        assertEquals(0, secondAdventurer.getTreasureQuantity());
    }

    @Test
    public void should_Not_Increment_Adventurer_Treasures_When_No_Treasures_Left(){
        treasureWalkingBehavior = new TreasureWalkingBehavior(0);
        Adventurer adventurer = new Adventurer(new Coordinates(0,0));
        Coordinates tileCoordinates = new Coordinates(1, 1);

        treasureWalkingBehavior.walkIn(adventurer, tileCoordinates);

        assertEquals(0, adventurer.getTreasureQuantity());
    }
}
