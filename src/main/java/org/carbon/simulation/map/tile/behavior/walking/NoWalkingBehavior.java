package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.adventurer.Adventurer;

public class NoWalkingBehavior implements WalkingBehavior {
    @Override
    public boolean canWalkIn(Adventurer adventurer){
        return false;
    }

    @Override
    public boolean walkIn(Adventurer adventurer) {
        throw new RuntimeException("An adventurer cannot enter this tile.");
    }

    @Override
    public boolean walkOut(Adventurer adventurer) {
        throw new RuntimeException("An adventurer should not be on this tile and so cannot leave it.");
    }
}
