package org.carbon.game;

import org.carbon.game.map.Coordinates;

public class Adventurer {
    private Coordinates coordinates;
    private int treasureQuantity = 0;

    public Adventurer(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = new Coordinates(coordinates);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void incrementTreasureQuantity() {
        this.treasureQuantity++;
    }

    public int getTreasureQuantity() {
        return treasureQuantity;
    }
}
