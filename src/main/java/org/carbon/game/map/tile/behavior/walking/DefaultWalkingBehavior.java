package org.carbon.game.map.tile.behavior.walking;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;

public class DefaultWalkingBehavior implements WalkingBehavior {
    private boolean occupied = false;

    @Override
    public void walkIn(Adventurer adventurer, Coordinates coordinates) {
        if (this.occupied) {
            return;
        }

        this.occupied = true;
        adventurer.setCoordinates(coordinates);
    }

    @Override
    public void walkOut(Adventurer adventurer, Coordinates coordinates) {
        this.occupied = false;
    }
}
