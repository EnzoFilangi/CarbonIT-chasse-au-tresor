package org.carbon.simulation.adventurer;

import org.carbon.simulation.adventurer.action.TurnLeftAction;
import org.carbon.simulation.adventurer.action.TurnRightAction;
import org.carbon.simulation.adventurer.action.WalkAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdventurerFactoryTest {
    @Test
    public void should_Instantiate_Valid_Adventurer() {
        Adventurer adventurer = AdventurerFactory.create("A-Alice-0-0-S-DGA".split("-"));

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
    public void should_Throw_If_Invalid_X() {
        assertThrows(IllegalArgumentException.class, () -> AdventurerFactory.create("A-Alice-X-0-S-DGA".split("-")));
    }

    @Test
    public void should_Throw_If_Invalid_Y() {
        assertThrows(IllegalArgumentException.class, () -> AdventurerFactory.create("A-Alice-0-Y-S-DGA".split("-")));
    }

    @Test
    public void should_Throw_If_Invalid_Orientation() {
        assertThrows(IllegalArgumentException.class, () -> AdventurerFactory.create("A-Alice-0-0-K-DGA".split("-")));
    }

    @Test
    public void should_Throw_If_Unrecognized_Action() {
        assertThrows(IllegalArgumentException.class, () -> AdventurerFactory.create("A-Alice-0-0-S-III".split("-")));
    }
}
