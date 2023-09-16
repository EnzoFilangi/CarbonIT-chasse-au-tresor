package org.carbon.game.map.tile;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;
import org.carbon.game.map.tile.behavior.graphical.GraphicalBehavior;
import org.carbon.game.map.tile.behavior.walking.WalkingBehavior;

public class Tile {
    private final GraphicalBehavior graphicalBehavior;
    private final WalkingBehavior walkingBehavior;

    public Tile(GraphicalBehavior graphicalBehavior, WalkingBehavior walkingBehavior) {
        this.graphicalBehavior = graphicalBehavior;
        this.walkingBehavior = walkingBehavior;
    }

    public boolean walkIn(Adventurer adventurer, Coordinates coordinates){
        return this.walkingBehavior.walkIn(adventurer, coordinates);
    }

    public boolean walkOut(Adventurer adventurer, Coordinates coordinates){
        return this.walkingBehavior.walkOut(adventurer, coordinates);
    }

    public String display(){
        return this.graphicalBehavior.display();
    }
}
