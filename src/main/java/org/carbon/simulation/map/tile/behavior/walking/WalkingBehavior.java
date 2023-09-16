package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.Adventurer;
import org.carbon.simulation.map.Coordinates;

public interface WalkingBehavior {
    boolean walkIn(Adventurer adventurer, Coordinates coordinates);

    boolean walkOut(Adventurer adventurer, Coordinates coordinates);
}
