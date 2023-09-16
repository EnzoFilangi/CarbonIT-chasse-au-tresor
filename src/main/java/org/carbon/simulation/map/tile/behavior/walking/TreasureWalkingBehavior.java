package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.Adventurer;
import org.carbon.simulation.map.Coordinates;

public class TreasureWalkingBehavior extends DefaultWalkingBehavior {
    private int treasureQuantity;

    public TreasureWalkingBehavior(int treasureQuantity) {
        super();
        this.treasureQuantity = treasureQuantity;
    }

    @Override
    public boolean walkIn(Adventurer adventurer, Coordinates coordinates) {
        boolean walkSuccess = super.walkIn(adventurer, coordinates);
        if (walkSuccess && treasureQuantity > 0){
            adventurer.incrementTreasureQuantity();
            treasureQuantity--;
        }

        return walkSuccess;
    }

    @Override
    public boolean walkOut(Adventurer adventurer, Coordinates coordinates) {
        return super.walkOut(adventurer, coordinates);
    }

    public int getTreasureQuantity() {
        return treasureQuantity;
    }
}
