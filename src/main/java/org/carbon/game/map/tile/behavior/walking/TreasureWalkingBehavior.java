package org.carbon.game.map.tile.behavior.walking;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;

public class TreasureWalkingBehavior implements WalkingBehavior {
    private boolean occupied = false;
    private int treasureQuantity;

    public TreasureWalkingBehavior(int treasureQuantity) {
        this.treasureQuantity = treasureQuantity;
    }

    @Override
    public void walkIn(Adventurer adventurer, Coordinates coordinates) {
        if (this.occupied) {
            return;
        }

        this.occupied = true;
        adventurer.setCoordinates(coordinates);

        if (treasureQuantity > 0){
            adventurer.incrementTreasureQuantity();
            treasureQuantity--;
        }
    }

    @Override
    public void walkOut(Adventurer adventurer, Coordinates coordinates) {
        this.occupied = false;
    }
}
