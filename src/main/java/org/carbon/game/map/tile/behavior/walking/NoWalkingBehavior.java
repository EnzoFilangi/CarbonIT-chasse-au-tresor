package org.carbon.game.map.tile.behavior.walking;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;

public class NoWalkingBehavior implements WalkingBehavior {
    @Override
    public void walkIn(Adventurer adventurer, Coordinates coordinates) {
    }

    @Override
    public void walkOut(Adventurer adventurer, Coordinates coordinates) {
        throw new RuntimeException("An adventurer should not be on this tile and so cannot leave it");
    }
}
