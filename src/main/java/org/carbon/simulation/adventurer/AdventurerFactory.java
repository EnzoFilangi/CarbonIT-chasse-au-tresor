package org.carbon.simulation.adventurer;

import org.carbon.simulation.adventurer.action.Action;
import org.carbon.simulation.adventurer.action.TurnLeftAction;
import org.carbon.simulation.adventurer.action.TurnRightAction;
import org.carbon.simulation.adventurer.action.WalkAction;
import org.carbon.simulation.map.Coordinates;

import java.util.LinkedList;
import java.util.List;

public class AdventurerFactory {
    /**
     * Creates an adventurer from the components of a command.
     * @param components The command's components should have the form : ["A", {{Adventurer name}}, {{PosX}}, {{PosY}}, {{Orientation}}, {{List of actions}}]. All strings should have been trimmed beforehand.
     * @throws IllegalArgumentException If one of the command's component is invalid.
     */
    public static Adventurer create(String[] components) throws IllegalArgumentException {
        if (components.length != 6) {
            throw new IllegalArgumentException("Incomplete adventurer initialization instruction.");
        }

        String name = components[1];
        Coordinates coordinates = parseCoordinates(components[2], components[3]);
        Orientation orientation = Orientation.fromCharacter(components[4].charAt(0));
        List<Action> actions = createActionList(components[5]);

        return new Adventurer(name, orientation, coordinates, actions);
    }

    private static List<Action> createActionList(String actionString) throws IllegalArgumentException {
        LinkedList<Action> actions = new LinkedList<>();

        for (Character actionCharacter : actionString.toCharArray()) {
            switch (actionCharacter) {
                case 'A' -> actions.add(new WalkAction());
                case 'G' -> actions.add(new TurnLeftAction());
                case 'D' -> actions.add(new TurnRightAction());
                default -> throw new IllegalArgumentException("Unrecognized adventurer action in adventurer creation instruction.");
            }
        }

        return actions;
    }

    private static Coordinates parseCoordinates(String x, String y) {
        try {
            return new Coordinates(
                    Integer.parseInt(x),
                    Integer.parseInt(y)
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid coordinates for adventurer.");
        }
    }
}
