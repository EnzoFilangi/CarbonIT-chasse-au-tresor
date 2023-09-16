package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.Adventurer;
import org.carbon.simulation.map.Coordinates;

public class NoWalkingBehavior implements WalkingBehavior {
    @Override
    public boolean walkIn(Adventurer adventurer, Coordinates coordinates) {
        return false;
    }

    @Override
    public boolean walkOut(Adventurer adventurer, Coordinates coordinates) {
        throw new RuntimeException("An adventurer should not be on this tile and so cannot leave it");
    }
}
