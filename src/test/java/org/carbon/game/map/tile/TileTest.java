package org.carbon.game.map.tile;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;
import org.carbon.game.map.tile.behavior.graphical.GraphicalBehavior;
import org.carbon.game.map.tile.behavior.walking.WalkingBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        this.tile = new Tile(graphicalBehavior, walkingBehavior, coordinates);
    }

    @Test
    public void should_Delegate_WalkIn_To_WalkingBehavior(){
        this.tile.walkIn(adventurer);
        verify(walkingBehavior).walkIn(adventurer, coordinates);
    }

    @Test
    public void should_Delegate_WalkOut_To_WalkingBehavior(){
        this.tile.walkOut(adventurer);
        verify(walkingBehavior).walkOut(adventurer, coordinates);
    }

    @Test
    public void should_Delegate_Display_To_GraphicalBehavior(){
        this.tile.display();
        verify(graphicalBehavior).display();
    }

    @Test
    public void should_Return_Result_Of_GraphicalBehavior_Display(){
        String expectedResult = "Lorem ipsum";
        when(graphicalBehavior.display()).thenReturn(expectedResult);

        String displayedValue = this.tile.display();

        assertEquals(displayedValue, expectedResult);
    }
}
