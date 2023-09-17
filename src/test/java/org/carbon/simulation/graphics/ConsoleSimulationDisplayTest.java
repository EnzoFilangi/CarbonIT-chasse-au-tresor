package org.carbon.simulation.graphics;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.Orientation;
import org.carbon.simulation.map.Coordinates;
import org.carbon.simulation.map.RegionMap;
import org.carbon.simulation.map.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public class ConsoleSimulationDisplayTest {
    private ConsoleSimulationDisplay consoleSimulationDisplay;

    @Mock
    private StandardOutputWrapper standardOutputWrapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        consoleSimulationDisplay = new ConsoleSimulationDisplay(standardOutputWrapper);
    }

    @Test
    public void should_Display_A_Map_Without_Adventurers(){
        RegionMap regionMap = new RegionMap(2, 3);
        regionMap.setTile(0, 0, TileFactory.plains());
        regionMap.setTile(1, 0, TileFactory.plains());
        regionMap.setTile(0, 1, TileFactory.treasure(1));
        regionMap.setTile(1, 1, TileFactory.mountains());
        regionMap.setTile(0, 2, TileFactory.plains());
        regionMap.setTile(1, 2, TileFactory.plains());

        consoleSimulationDisplay.display(regionMap, new ArrayList<>());

        verify(standardOutputWrapper).print(".    .    \nT(1) M    \n.    .    \n");
    }

    @Test
    public void should_Display_A_Map_With_Adventurers(){
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("1", Orientation.NORTH, new Coordinates(0, 0), null));
        adventurers.add(new Adventurer("2", Orientation.NORTH, new Coordinates(0, 1), null));

        RegionMap regionMap = new RegionMap(2, 3);
        regionMap.setTile(0, 0, TileFactory.plains());
        regionMap.setTile(1, 0, TileFactory.plains());
        regionMap.setTile(0, 1, TileFactory.treasure(1));
        regionMap.setTile(1, 1, TileFactory.mountains());
        regionMap.setTile(0, 2, TileFactory.plains());
        regionMap.setTile(1, 2, TileFactory.plains());

        consoleSimulationDisplay.display(regionMap, adventurers);

        verify(standardOutputWrapper).print("A(1) .    \nA(2) M    \n.    .    \n");
    }
}
