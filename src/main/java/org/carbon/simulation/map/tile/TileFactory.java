package org.carbon.simulation.map.tile;

import org.carbon.simulation.map.tile.behavior.graphical.DynamicGraphicalBehavior;
import org.carbon.simulation.map.tile.behavior.graphical.StaticGraphicalBehavior;
import org.carbon.simulation.map.tile.behavior.walking.DefaultWalkingBehavior;
import org.carbon.simulation.map.tile.behavior.walking.NoWalkingBehavior;
import org.carbon.simulation.map.tile.behavior.walking.TreasureWalkingBehavior;

public class TileFactory {
    public static Tile plains() {
        return new Tile(
                new StaticGraphicalBehavior("."),
                new DefaultWalkingBehavior()
        );
    }

    public static Tile mountains() {
        return new Tile(
                new StaticGraphicalBehavior("M"),
                new NoWalkingBehavior()
        );
    }

    public static Tile treasure(int treasureQuantity) {
        TreasureWalkingBehavior treasureWalkingBehavior = new TreasureWalkingBehavior(treasureQuantity);
        return new Tile(
                // Evaluate the quantity of treasures left a runtime each time the tile is displayed
                new DynamicGraphicalBehavior("T", () -> String.valueOf(treasureWalkingBehavior.getTreasureQuantity())),
                treasureWalkingBehavior
        );
    }
}
