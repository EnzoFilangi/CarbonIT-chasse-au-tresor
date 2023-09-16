package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.Adventurer;
import org.carbon.simulation.map.Coordinates;

public class DefaultWalkingBehavior implements WalkingBehavior {
    private boolean occupied = false;

    @Override
    public boolean walkIn(Adventurer adventurer, Coordinates coordinates) {
        if (this.occupied) {
            return false;
        }

        this.occupied = true;
        adventurer.setCoordinates(coordinates);
        return true;
    }

    @Override
    public boolean walkOut(Adventurer adventurer, Coordinates coordinates) {
        this.occupied = false;
        return true;
    }
}
