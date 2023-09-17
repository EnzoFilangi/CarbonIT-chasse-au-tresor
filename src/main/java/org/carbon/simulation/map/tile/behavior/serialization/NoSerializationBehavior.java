package org.carbon.simulation.map.tile.behavior.serialization;

import org.carbon.simulation.map.Coordinates;

public class NoSerializationBehavior implements SerializationBehavior {
    @Override
    public String serialize(Coordinates coordinates) {
        return "";
    }
}
