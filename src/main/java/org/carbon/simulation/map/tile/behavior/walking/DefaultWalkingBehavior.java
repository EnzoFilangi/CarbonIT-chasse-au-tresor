package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.adventurer.Adventurer;

public class DefaultWalkingBehavior implements WalkingBehavior {
    private boolean occupied = false;

    @Override
    public boolean canWalkIn(Adventurer adventurer) {
        return !occupied;
    }

    @Override
    public boolean walkIn(Adventurer adventurer) {
        if (!canWalkIn(adventurer)){
            throw new RuntimeException("An adventurer cannot enter this tile right now.");
        }

        occupied = true;
        return true;
    }

    @Override
    public boolean walkOut(Adventurer adventurer) {
        occupied = false;
        return true;
    }
}
