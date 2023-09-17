package org.carbon.simulation.map.tile.behavior.serialization;

import org.carbon.simulation.map.Coordinates;

public interface SerializationBehavior {
    String serialize(Coordinates coordinates);
}
