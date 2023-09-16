package org.carbon.simulation.map.tile.behavior.walking;

import org.carbon.simulation.adventurer.Adventurer;

public class TreasureWalkingBehavior extends DefaultWalkingBehavior {
    private int treasureQuantity;

    public TreasureWalkingBehavior(int treasureQuantity) {
        super();
        this.treasureQuantity = treasureQuantity;
    }

    @Override
    public boolean walkIn(Adventurer adventurer) {
        if (super.walkIn(adventurer) && treasureQuantity > 0){
            adventurer.incrementTreasureQuantity();
            treasureQuantity--;
        }
        return true;
    }

    public int getTreasureQuantity() {
        return treasureQuantity;
    }
}
