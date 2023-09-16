package org.carbon.simulation.adventurer.action;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.Orientation;
import org.carbon.simulation.map.Coordinates;
import org.carbon.simulation.map.RegionMap;
import org.carbon.simulation.map.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WalkActionTest {
    private WalkAction action;

    private Adventurer adventurer;

    @Mock
    private RegionMap map;

    @Mock
    private Tile currentTile;

    @Mock
    private Tile destinationTile;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        action = new WalkAction();
        adventurer = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), null);
    }

    @Test
    public void should_Do_Nothing_If_No_Destination_Tile(){
        when(map.getTileAt(any()))
                .thenReturn(currentTile)
                .thenReturn(null);

        action.execute(adventurer, map);

        verify(map, times(2)).getTileAt(any());
        verify(currentTile, times(0)).walkOut(any());
        verify(destinationTile, times(0)).canWalkIn(any());
        verify(destinationTile, times(0)).walkIn(any());

        assertEquals(0, adventurer.getCoordinates().getX());
        assertEquals(0, adventurer.getCoordinates().getY());
    }

    @Test
    public void should_Not_Move_Adventurer_If_Cant_Walk_In(){
        when(map.getTileAt(any()))
                .thenReturn(currentTile)
                .thenReturn(destinationTile);
        when(destinationTile.canWalkIn(adventurer)).thenReturn(false);

        action.execute(adventurer, map);

        verify(map, times(2)).getTileAt(any());
        verify(destinationTile, times(1)).canWalkIn(any());
        verify(currentTile, times(0)).walkOut(any());
        verify(destinationTile, times(0)).walkIn(any());

        assertEquals(0, adventurer.getCoordinates().getX());
        assertEquals(0, adventurer.getCoordinates().getY());
    }

    @Test
    public void should_Move_Adventurer_If_Can_Walk_In(){
        when(map.getTileAt(any()))
                .thenReturn(currentTile)
                .thenReturn(destinationTile);
        when(destinationTile.canWalkIn(adventurer)).thenReturn(true);

        action.execute(adventurer, map);

        assertEquals(0, adventurer.getCoordinates().getX());
        assertEquals(1, adventurer.getCoordinates().getY()); // Adventurer was oriented to the South
    }

    @Test
    public void should_Call_Tile_Lifecycle_Methods_If_Can_Walk_In(){
        when(map.getTileAt(any()))
                .thenReturn(currentTile)
                .thenReturn(destinationTile);
        when(destinationTile.canWalkIn(adventurer)).thenReturn(true);

        action.execute(adventurer, map);

        verify(currentTile, times(1)).walkOut(any());
        verify(destinationTile, times(1)).walkIn(any());
    }
}
