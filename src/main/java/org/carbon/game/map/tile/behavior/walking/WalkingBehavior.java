package org.carbon.game.map.tile.behavior.walking;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;

public interface WalkingBehavior {
    boolean walkIn(Adventurer adventurer, Coordinates coordinates);

    boolean walkOut(Adventurer adventurer, Coordinates coordinates);
}
