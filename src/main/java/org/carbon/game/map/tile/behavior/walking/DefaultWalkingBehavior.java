package org.carbon.game.map.tile.behavior.walking;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;

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
