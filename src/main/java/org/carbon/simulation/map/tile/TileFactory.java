package org.carbon.simulation.map.tile;

import org.carbon.simulation.map.tile.behavior.graphical.DynamicGraphicalBehavior;
import org.carbon.simulation.map.tile.behavior.graphical.StaticGraphicalBehavior;
import org.carbon.simulation.map.tile.behavior.serialization.DynamicSerializationBehavior;
import org.carbon.simulation.map.tile.behavior.serialization.NoSerializationBehavior;
import org.carbon.simulation.map.tile.behavior.serialization.StaticSerializationBehavior;
import org.carbon.simulation.map.tile.behavior.walking.DefaultWalkingBehavior;
import org.carbon.simulation.map.tile.behavior.walking.NoWalkingBehavior;
import org.carbon.simulation.map.tile.behavior.walking.TreasureWalkingBehavior;

public class TileFactory {
    public static Tile plains() {
        return new Tile(
                new StaticGraphicalBehavior("."),
                new DefaultWalkingBehavior(),
                new NoSerializationBehavior()
        );
    }

    public static Tile mountains() {
        return new Tile(
                new StaticGraphicalBehavior("M"),
                new NoWalkingBehavior(),
                new StaticSerializationBehavior("M")
        );
    }

    public static Tile treasure(int treasureQuantity) {
        TreasureWalkingBehavior treasureWalkingBehavior = new TreasureWalkingBehavior(treasureQuantity);
        return new Tile(
                // Evaluate the quantity of treasures left a runtime each time the tile is displayed
                new DynamicGraphicalBehavior("T", () -> String.valueOf(treasureWalkingBehavior.getTreasureQuantity())),
                treasureWalkingBehavior,
                new DynamicSerializationBehavior("T", () -> String.valueOf(treasureWalkingBehavior.getTreasureQuantity()))
        );
    }
}
