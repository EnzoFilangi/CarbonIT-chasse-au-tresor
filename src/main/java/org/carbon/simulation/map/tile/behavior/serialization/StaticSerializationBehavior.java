package org.carbon.simulation.map.tile.behavior.serialization;

import org.carbon.simulation.map.Coordinates;

public class StaticSerializationBehavior implements SerializationBehavior {
    private final String prefix;

    public StaticSerializationBehavior(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String serialize(Coordinates coordinates) {
        return prefix + " - " + coordinates.getX() + " - " + coordinates.getY() + "\n";
    }
}
