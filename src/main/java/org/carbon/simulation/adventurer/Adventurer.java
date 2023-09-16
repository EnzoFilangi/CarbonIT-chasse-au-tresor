package org.carbon.simulation.adventurer;

import org.carbon.simulation.map.Coordinates;

public class Adventurer {
    private final String name;
    private Orientation orientation;
    private Coordinates coordinates;
    private int treasureQuantity = 0;

    public Adventurer(String name, Orientation orientation, Coordinates coordinates) {
        this.name = name;
        this.orientation = orientation;
        this.coordinates = coordinates;
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
}
