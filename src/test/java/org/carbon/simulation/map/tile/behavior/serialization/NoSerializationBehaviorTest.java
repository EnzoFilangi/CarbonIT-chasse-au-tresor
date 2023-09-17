package org.carbon.simulation.map.tile.behavior.serialization;

import org.carbon.simulation.map.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoSerializationBehaviorTest {
    @Test
    public void should_Return_Empty_String(){
        assertEquals("", new NoSerializationBehavior().serialize(new Coordinates(0, 0)));
    }
}
