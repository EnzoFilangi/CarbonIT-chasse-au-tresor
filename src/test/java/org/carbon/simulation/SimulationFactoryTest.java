package org.carbon.simulation;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.action.TurnLeftAction;
import org.carbon.simulation.adventurer.action.TurnRightAction;
import org.carbon.simulation.adventurer.action.WalkAction;
import org.carbon.simulation.map.tile.TileFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationFactoryTest {
    @Test
    public void should_Throw_If_No_Instructions() {
        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(new ArrayList<>()));
        assertEquals("No map initialization instruction found.", exception.getMessage());
    }

    @Test
    public void should_Ignore_Comments() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("# Only a comment in the file so it should throw");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("No map initialization instruction found.", exception.getMessage());
    }

    @Test
    public void should_Create_1x1_Empty_Map() throws SimulationCreationException {
        ArrayList<String> fileLines = new ArrayList<>();
        fileLines.add("C - 1 - 1");

        Simulation simulation = SimulationFactory.createSimulationFromCommands(fileLines);

        assertEquals(1, simulation.getMap().getSizeX());
        assertEquals(1, simulation.getMap().getSizeY());
        assertEquals(TileFactory.plains().display(), simulation.getMap().getTileAt(0, 0).display());
    }

    @Test
    public void should_Ignore_Empty_Trailing_Lines() throws SimulationCreationException {
        ArrayList<String> fileLines = new ArrayList<>();
        fileLines.add("C - 1 - 1");
        fileLines.add("");

        Simulation simulation = SimulationFactory.createSimulationFromCommands(fileLines);

        assertEquals(1, simulation.getMap().getSizeX());
        assertEquals(1, simulation.getMap().getSizeY());
        assertEquals(TileFactory.plains().display(), simulation.getMap().getTileAt(0, 0).display());
    }

    @Test
    public void should_Create_Complex_Map() throws SimulationCreationException {
        ArrayList<String> fileLines = new ArrayList<>();
        fileLines.add("C - 2 - 2");
        fileLines.add("M - 0 - 1");
        fileLines.add("T - 1 - 0 - 2");

        Simulation simulation = SimulationFactory.createSimulationFromCommands(fileLines);

        assertEquals(2, simulation.getMap().getSizeX());
        assertEquals(2, simulation.getMap().getSizeY());
        assertEquals(TileFactory.plains().display(), simulation.getMap().getTileAt(0, 0).display());
        assertEquals(TileFactory.plains().display(), simulation.getMap().getTileAt(1, 1).display());
        assertEquals(TileFactory.mountains().display(), simulation.getMap().getTileAt(0, 1).display());
        assertEquals(TileFactory.treasure(2).display(), simulation.getMap().getTileAt(1, 0).display());
    }

    @Test
    public void should_Create_Adventurer() throws SimulationCreationException {
        ArrayList<String> fileLines = new ArrayList<>();
        fileLines.add("C - 1 - 1");
        fileLines.add("A - Alice - 0 - 0 - S - DGA");

        Simulation simulation = SimulationFactory.createSimulationFromCommands(fileLines);

        assertEquals(1, simulation.getAdventurers().size());
        Adventurer adventurer = simulation.getAdventurers().get(0);
        assertEquals("Alice", adventurer.getName());
        assertEquals(0, adventurer.getCoordinates().getX());
        assertEquals(0, adventurer.getCoordinates().getY());
        assertEquals(0, adventurer.getTreasureQuantity());
        assertInstanceOf(TurnRightAction.class, adventurer.getAndRemoveNextAction());
        assertInstanceOf(TurnLeftAction.class, adventurer.getAndRemoveNextAction());
        assertInstanceOf(WalkAction.class, adventurer.getAndRemoveNextAction());
        assertNull(adventurer.getAndRemoveNextAction());
    }

    @Test
    public void should_Throw_If_Two_Map_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("C - 2 - 2");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Cannot have two map initialization instructions.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incomplete_Map_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Incomplete map initialization instruction.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incorrect_Map_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - X - Y");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Invalid map initialization instruction.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incomplete_Mountain_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("M - 0");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Incomplete mountain initialization instruction.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incorrect_Mountain_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("M - X - Y");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Invalid mountain initialization instruction.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_No_Map_Before_Mountain_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("M - 0 - 0");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("A map creation instruction must appear before tile instructions.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incomplete_Treasure_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("T - 0");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Incomplete treasure initialization instruction.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incorrect_Treasure_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("T - X - Y - Q");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Invalid treasure initialization instruction.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_No_Map_Before_Treasure_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("T - 0 - 0 - 1");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("A map creation instruction must appear before tile instructions.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incomplete_Adventurer_Initialization() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("A - Alice");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Incomplete adventurer initialization instruction.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incorrect_Adventurer_Initialization_Coordinates() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("A - Alice - X - Y - S - AAA");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Invalid coordinates for adventurer.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incorrect_Adventurer_Initialization_Orientation() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("A - Alice - 0 - 0 - K - AAA");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Not a valid orientation character.", exception.getMessage());
    }

    @Test
    public void should_Throw_If_Incorrect_Adventurer_Initialization_Actions() {
        ArrayList<String> fileLines = new ArrayList<>();

        fileLines.add("C - 1 - 1");
        fileLines.add("A - Alice - 0 - 0 - S - III");

        SimulationCreationException exception = assertThrowsExactly(SimulationCreationException.class, () -> SimulationFactory.createSimulationFromCommands(fileLines));
        assertEquals("Unrecognized adventurer action in adventurer creation instruction.", exception.getMessage());
    }
}
