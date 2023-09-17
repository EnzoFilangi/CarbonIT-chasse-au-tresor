package org.carbon.simulation;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.Orientation;
import org.carbon.simulation.adventurer.action.Action;
import org.carbon.simulation.adventurer.action.TurnLeftAction;
import org.carbon.simulation.adventurer.action.TurnRightAction;
import org.carbon.simulation.adventurer.action.WalkAction;
import org.carbon.simulation.graphics.console.ConsoleBasedSimulationDisplay;
import org.carbon.simulation.map.Coordinates;
import org.carbon.simulation.map.RegionMap;
import org.carbon.simulation.map.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SimulationTest {
    @Mock
    ConsoleBasedSimulationDisplay consoleBasedSimulationDisplay;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class SimulationUnitTests {
        @Mock
        RegionMap map;

        @Mock
        Adventurer adventurer;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void should_Display_Simulation_State() {
            when(adventurer.getAndRemoveNextAction()).thenReturn(null);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            verify(consoleBasedSimulationDisplay).display(map, adventurers);
        }

        @Test
        public void should_Get_The_Actions_From_The_Adventurers() {
            when(adventurer.getAndRemoveNextAction()).thenReturn(null);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            verify(adventurer).getAndRemoveNextAction();
        }

        @Test
        public void should_Execute_Actions() {
            Action action = mock(Action.class);
            when(adventurer.getAndRemoveNextAction())
                    .thenReturn(action)
                    .thenReturn(null);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            verify(action).execute(adventurer, map);
        }
    }

    @Nested
    class SimulationIntegrationTests {
        @Test
        public void scenario_One_Player_Turn_Left() {
            List<Action> actions = new ArrayList<>();
            actions.add(new TurnLeftAction());
            Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), actions);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            RegionMap map = new RegionMap(1, 1);
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(Orientation.WEST, adventurer.getOrientation());
        }

        @Test
        public void scenario_One_Player_Turn_Right() {
            List<Action> actions = new ArrayList<>();
            actions.add(new TurnRightAction());
            Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), actions);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            RegionMap map = new RegionMap(1, 1);
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(Orientation.EAST, adventurer.getOrientation());
        }

        @Test
        public void scenario_One_Player_Walk() {
            List<Action> actions = new ArrayList<>();
            actions.add(new WalkAction());
            Adventurer adventurer = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), actions);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            RegionMap map = new RegionMap(1, 2);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(0, 1, TileFactory.plains());
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(0, adventurer.getCoordinates().getX());
            assertEquals(1, adventurer.getCoordinates().getY());
        }

        @Test
        public void scenario_One_Player_Mountain() {
            List<Action> actions = new ArrayList<>();
            actions.add(new WalkAction());
            Adventurer adventurer = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), actions);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            RegionMap map = new RegionMap(1, 2);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(0, 1, TileFactory.mountains());
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(0, adventurer.getCoordinates().getX());
            assertEquals(0, adventurer.getCoordinates().getY());
        }

        @Test
        public void scenario_One_Player_Treasure() {
            List<Action> actions = new ArrayList<>();
            actions.add(new WalkAction());
            Adventurer adventurer = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), actions);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            RegionMap map = new RegionMap(1, 2);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(0, 1, TileFactory.treasure(1));
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(0, adventurer.getCoordinates().getX());
            assertEquals(1, adventurer.getCoordinates().getY());
            assertEquals(1, adventurer.getTreasureQuantity());
        }

        @Test
        public void scenario_One_Player_Empty_Treasure() {
            List<Action> actions = new ArrayList<>();
            actions.add(new WalkAction());
            Adventurer adventurer = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), actions);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            RegionMap map = new RegionMap(1, 2);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(0, 1, TileFactory.treasure(0));
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(0, adventurer.getCoordinates().getX());
            assertEquals(1, adventurer.getCoordinates().getY());
            assertEquals(0, adventurer.getTreasureQuantity());
        }

        @Test
        public void scenario_One_Player_Multiple_Actions_No_Issues() {
            List<Action> actions = new ArrayList<>();
            actions.add(new WalkAction());
            actions.add(new WalkAction());
            Adventurer adventurer = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), actions);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            RegionMap map = new RegionMap(1, 3);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(0, 1, TileFactory.plains());
            map.setTile(0, 2, TileFactory.plains());
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(0, adventurer.getCoordinates().getX());
            assertEquals(2, adventurer.getCoordinates().getY());
        }

        @Test
        public void scenario_One_Player_Multiple_Actions_Impossible_Actions() {
            List<Action> actions = new ArrayList<>();
            actions.add(new WalkAction());
            actions.add(new TurnLeftAction());
            actions.add(new WalkAction());
            Adventurer adventurer = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), actions);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer);
            RegionMap map = new RegionMap(2, 3);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(1, 0, TileFactory.plains());
            map.setTile(0, 1, TileFactory.mountains());
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(1, adventurer.getCoordinates().getX());
            assertEquals(0, adventurer.getCoordinates().getY());
        }

        @Test
        public void scenario_Two_Players_Walk_No_Collision() {
            List<Action> actions1 = new ArrayList<>();
            actions1.add(new WalkAction());
            Adventurer adventurer1 = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), actions1);
            List<Action> actions2 = new ArrayList<>();
            actions2.add(new WalkAction());
            Adventurer adventurer2 = new Adventurer("", Orientation.SOUTH, new Coordinates(1, 0), actions2);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer1);
            adventurers.add(adventurer2);
            RegionMap map = new RegionMap(2, 2);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(1, 0, TileFactory.plains());
            map.setTile(0, 1, TileFactory.plains());
            map.setTile(1, 1, TileFactory.plains());
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(0, adventurer1.getCoordinates().getX());
            assertEquals(1, adventurer1.getCoordinates().getY());
            assertEquals(1, adventurer2.getCoordinates().getX());
            assertEquals(1, adventurer2.getCoordinates().getY());
        }

        @Test
        public void scenario_Two_Players_Walk_Collision() {
            List<Action> actions1 = new ArrayList<>();
            actions1.add(new WalkAction());
            Adventurer adventurer1 = new Adventurer("", Orientation.SOUTH, new Coordinates(0, 0), actions1);
            List<Action> actions2 = new ArrayList<>();
            actions2.add(new WalkAction());
            Adventurer adventurer2 = new Adventurer("", Orientation.WEST, new Coordinates(1, 1), actions2);
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers.add(adventurer1);
            adventurers.add(adventurer2);
            RegionMap map = new RegionMap(2, 2);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(1, 0, TileFactory.plains());
            map.setTile(0, 1, TileFactory.plains());
            map.setTile(1, 1, TileFactory.plains());
            Simulation simulation = new Simulation(consoleBasedSimulationDisplay, map, adventurers);

            simulation.runSimulation();

            assertEquals(0, adventurer1.getCoordinates().getX());
            assertEquals(1, adventurer1.getCoordinates().getY());
            assertEquals(1, adventurer2.getCoordinates().getX());
            assertEquals(1, adventurer2.getCoordinates().getY());
        }
    }
}
