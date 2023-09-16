package org.carbon.game.map.tile.behavior.walking;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;

public interface WalkingBehavior {
    void walkIn(Adventurer adventurer, Coordinates coordinates);

    void walkOut(Adventurer adventurer, Coordinates coordinates);
}
