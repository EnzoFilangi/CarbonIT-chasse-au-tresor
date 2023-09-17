package org.carbon.simulation.map.tile.behavior.serialization;

import org.carbon.simulation.map.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticSerializationBehaviorTest {
    @Test
    public void should_Return_Prefix_And_Coordinates(){
        assertEquals("M - 0 - 0\n", new StaticSerializationBehavior("M").serialize(new Coordinates(0, 0)));
    }
}
