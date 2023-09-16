package org.carbon.simulation.map.tile;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.tile.behavior.graphical.GraphicalBehavior;
import org.carbon.simulation.map.tile.behavior.walking.WalkingBehavior;

public class Tile {
    private final GraphicalBehavior graphicalBehavior;
    private final WalkingBehavior walkingBehavior;

    public Tile(GraphicalBehavior graphicalBehavior, WalkingBehavior walkingBehavior) {
        this.graphicalBehavior = graphicalBehavior;
        this.walkingBehavior = walkingBehavior;
    }

    public boolean canWalkIn(Adventurer adventurer){
        return this.walkingBehavior.canWalkIn(adventurer);
    }

    public boolean walkIn(Adventurer adventurer){
        return this.walkingBehavior.walkIn(adventurer);
    }

    public boolean walkOut(Adventurer adventurer){
        return this.walkingBehavior.walkOut(adventurer);
    }

    public String display(){
        return this.graphicalBehavior.display();
    }
}
