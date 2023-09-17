package org.carbon.simulation.adventurer;

import org.carbon.simulation.adventurer.action.Action;
import org.carbon.simulation.map.Coordinates;

import java.util.List;

public class Adventurer {
    private final String name;
    private Orientation orientation;
    private Coordinates coordinates;
    private int treasureQuantity = 0;
    private final List<Action> actionBacklog;

    public Adventurer(String name, Orientation orientation, Coordinates coordinates, List<Action> actionBacklog) {
        this.name = name;
        this.orientation = orientation;
        this.coordinates = coordinates;
        this.actionBacklog = actionBacklog;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = new Coordinates(coordinates);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void incrementTreasureQuantity() {
        this.treasureQuantity++;
    }

    public int getTreasureQuantity() {
        return treasureQuantity;
    }

    public void turnLeft(){
        orientation = Orientation.orientationAfterLeftTurn(orientation);
    }

    public void turnRight(){
        orientation = Orientation.orientationAfterRightTurn(orientation);
    }

    public Action getAndRemoveNextAction(){
        if (actionBacklog.size() == 0){
            return null;
        }

        return actionBacklog.remove(0);
    }

    public String serialize() {
        return "A - " + name + " - " + coordinates.getX() + " - " + coordinates.getY() + " - " + orientation.toString() + " - " + treasureQuantity + "\n";
    }
}
