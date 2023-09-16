package org.carbon.game.map.tile;

import org.carbon.game.Adventurer;
import org.carbon.game.map.Coordinates;
import org.carbon.game.map.tile.behavior.graphical.GraphicalBehavior;
import org.carbon.game.map.tile.behavior.walking.WalkingBehavior;

public class Tile {
    private final GraphicalBehavior graphicalBehavior;
    private final WalkingBehavior walkingBehavior;
    private final Coordinates coordinates;

    public Tile(GraphicalBehavior graphicalBehavior, WalkingBehavior walkingBehavior, Coordinates coordinates) {
        this.graphicalBehavior = graphicalBehavior;
        this.walkingBehavior = walkingBehavior;
        this.coordinates = coordinates;
    }

    public boolean walkIn(Adventurer adventurer){
        return this.walkingBehavior.walkIn(adventurer, coordinates);
    }

    public boolean walkOut(Adventurer adventurer){
        return this.walkingBehavior.walkOut(adventurer, coordinates);
    }

    public String display(){
        return this.graphicalBehavior.display();
    }
}
