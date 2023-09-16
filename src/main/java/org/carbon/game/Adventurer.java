package org.carbon.game;

import org.carbon.game.map.Coordinates;

public class Adventurer {
    private Coordinates coordinates;

    public Adventurer(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = new Coordinates(coordinates);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
