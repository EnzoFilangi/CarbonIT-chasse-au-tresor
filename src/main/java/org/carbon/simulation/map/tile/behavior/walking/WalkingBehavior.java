package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.adventurer.Adventurer;

public interface WalkingBehavior {
    boolean canWalkIn(Adventurer adventurer);
    boolean walkIn(Adventurer adventurer);

    boolean walkOut(Adventurer adventurer);
}
