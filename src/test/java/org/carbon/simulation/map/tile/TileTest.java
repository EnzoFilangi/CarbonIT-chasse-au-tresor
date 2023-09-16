package org.carbon.simulation.map.tile;

import org.carbon.simulation.Adventurer;
import org.carbon.simulation.map.Coordinates;
import org.carbon.simulation.map.tile.behavior.graphical.GraphicalBehavior;
import org.carbon.simulation.map.tile.behavior.walking.WalkingBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TileTest {
    private Tile tile;
    @Mock
    private GraphicalBehavior graphicalBehavior;
    @Mock
    private WalkingBehavior walkingBehavior;

    @Mock
    private Adventurer adventurer;

    @Mock
    private Coordinates coordinates;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        tile = new Tile(graphicalBehavior, walkingBehavior);
    }

    @Test
    public void should_Delegate_WalkIn_To_WalkingBehavior(){
        tile.walkIn(adventurer, coordinates);
        verify(walkingBehavior).walkIn(adventurer, coordinates);
    }

    @Test
    public void should_Return_Result_Of_WalkingBehavior_WalkIn(){
        when(walkingBehavior.walkIn(adventurer, coordinates)).thenReturn(true);

        boolean result = tile.walkIn(adventurer, coordinates);

        assertTrue(result);
    }

    @Test
    public void should_Delegate_WalkOut_To_WalkingBehavior(){
        tile.walkOut(adventurer, coordinates);
        verify(walkingBehavior).walkOut(adventurer, coordinates);
    }

    @Test
    public void should_Return_Result_Of_WalkingBehavior_WalkOut(){
        when(walkingBehavior.walkOut(adventurer, coordinates)).thenReturn(true);

        boolean result = tile.walkOut(adventurer, coordinates);

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

        assertEquals(displayedValue, expectedResult);
    }
}
