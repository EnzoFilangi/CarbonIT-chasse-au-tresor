package org.carbon.simulation.map.tile;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.Coordinates;
import org.carbon.simulation.map.tile.behavior.graphical.GraphicalBehavior;
import org.carbon.simulation.map.tile.behavior.serialization.SerializationBehavior;
import org.carbon.simulation.map.tile.behavior.walking.WalkingBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TileTest {
    private Tile tile;
    @Mock
    private GraphicalBehavior graphicalBehavior;
    @Mock
    private WalkingBehavior walkingBehavior;

    @Mock
    private SerializationBehavior serializationBehavior;

    @Mock
    private Adventurer adventurer;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        tile = new Tile(graphicalBehavior, walkingBehavior, serializationBehavior);
    }

    @Test
    public void should_Delegate_CanWalkIn_To_WalkingBehavior(){
        tile.canWalkIn(adventurer);
        verify(walkingBehavior).canWalkIn(adventurer);
    }

    @Test
    public void should_Return_Result_Of_WalkingBehavior_CanWalkIn(){
        when(walkingBehavior.canWalkIn(adventurer)).thenReturn(true);

        boolean result = tile.canWalkIn(adventurer);

        assertTrue(result);
    }

    @Test
    public void should_Delegate_WalkIn_To_WalkingBehavior(){
        tile.walkIn(adventurer);
        verify(walkingBehavior).walkIn(adventurer);
    }

    @Test
    public void should_Return_Result_Of_WalkingBehavior_WalkIn(){
        when(walkingBehavior.walkIn(adventurer)).thenReturn(true);

        boolean result = tile.walkIn(adventurer);

        assertTrue(result);
    }

    @Test
    public void should_Delegate_WalkOut_To_WalkingBehavior(){
        tile.walkOut(adventurer);
        verify(walkingBehavior).walkOut(adventurer);
    }

    @Test
    public void should_Return_Result_Of_WalkingBehavior_WalkOut(){
        when(walkingBehavior.walkOut(adventurer)).thenReturn(true);

        boolean result = tile.walkOut(adventurer);

        assertTrue(result);
    }

    @Test
    public void should_Delegate_Display_To_GraphicalBehavior(){
        tile.display();
        verify(graphicalBehavior).display();
    }

    @Test
    public void should_Return_Result_Of_GraphicalBehavior_Display(){
        String expectedResult = "Lorem ipsum";
        when(graphicalBehavior.display()).thenReturn(expectedResult);

        String displayedValue = tile.display();

        assertEquals(expectedResult, displayedValue);
    }

    @Test
    public void should_Delegate_Serialization_To_SerializationBehavior(){
        Coordinates coordinates = new Coordinates(0, 0);
        tile.serialize(coordinates);
        verify(serializationBehavior).serialize(coordinates);
    }

    @Test
    public void should_Return_Result_Of_SerializationBehavior_Serialize(){
        String expectedResult = "Lorem ipsum";
        when(serializationBehavior.serialize(any())).thenReturn(expectedResult);

        String serializedValue = tile.serialize(new Coordinates(0, 0));

        assertEquals(expectedResult, serializedValue);
    }
}
