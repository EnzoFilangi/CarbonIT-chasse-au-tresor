package org.carbon.simulation.adventurer;

import org.carbon.simulation.adventurer.action.Action;
import org.carbon.simulation.adventurer.action.TurnLeftAction;
import org.carbon.simulation.adventurer.action.TurnRightAction;
import org.carbon.simulation.map.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AdventurerTest {
    @Test
    public void should_Turn_Correct_Left() {
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);
        adventurer.turnLeft();

        assertEquals(Orientation.orientationAfterLeftTurn(Orientation.NORTH), adventurer.getOrientation());
    }

    @Test
    public void should_Turn_Correct_Right() {
        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), null);
        adventurer.turnRight();

        assertEquals(Orientation.orientationAfterRightTurn(Orientation.NORTH), adventurer.getOrientation());
    }

    @Test
    public void should_Return_Correct_Action() {
        Action expectedAction = new TurnRightAction();
        List<Action> actions = new ArrayList<>();
        actions.add(expectedAction);
        actions.add(new TurnLeftAction());

        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), actions);

        assertEquals(expectedAction, adventurer.getAndRemoveNextAction());
    }

    @Test
    public void should_Use_Up_All_Actions() {
        Action expectedAction1 = new TurnRightAction();
        Action expectedAction2 = new TurnRightAction();
        List<Action> actions = new ArrayList<>();
        actions.add(expectedAction1);
        actions.add(expectedAction2);

        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), actions);

        assertEquals(expectedAction1, adventurer.getAndRemoveNextAction());
        assertEquals(expectedAction2, adventurer.getAndRemoveNextAction());
        assertNull(adventurer.getAndRemoveNextAction());
    }

    @Test
    public void should_Return_Null_When_No_Actions() {
        List<Action> actions = new ArrayList<>();

        Adventurer adventurer = new Adventurer("", Orientation.NORTH, new Coordinates(0, 0), actions);

        assertNull(adventurer.getAndRemoveNextAction());
    }

    @Test
    public void should_Serialize_Properties() {
        Adventurer adventurer = new Adventurer("Alice", Orientation.NORTH, new Coordinates(0, 0), null);

        assertEquals("A - Alice - 0 - 0 - N - 0\n", adventurer.serialize());
    }

    @Test
    public void should_Display_Properly() {
        Adventurer adventurer = new Adventurer("Alice", Orientation.NORTH, new Coordinates(0, 0), null);

        assertEquals("A(Alice)", adventurer.display());
    }
}
