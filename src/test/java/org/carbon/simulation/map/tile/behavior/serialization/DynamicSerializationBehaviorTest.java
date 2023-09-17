package org.carbon.simulation.map.tile.behavior.serialization;

import org.carbon.simulation.map.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DynamicSerializationBehaviorTest {
    @Test
    public void should_Evaluate_Lambda_When_Serializing(){
        String expectedInnerString = "Lorem";
        String expectedPrefix = "T";

        DynamicSerializationBehavior dynamicSerializationBehavior = new DynamicSerializationBehavior(
                expectedPrefix,
                () -> expectedInnerString
        );

        String expectedResult = expectedPrefix + " - 0 - 0 - " + expectedInnerString + "\n";
        assertEquals(expectedResult, dynamicSerializationBehavior.serialize(new Coordinates(0, 0)));
    }

    @Test
    public void should_Propagate_Exception(){
        DynamicSerializationBehavior dynamicSerializationBehavior = new DynamicSerializationBehavior(
                "T",
                () -> {
                    throw new Exception("Test");
                }
        );

        assertThrows(RuntimeException.class, () -> dynamicSerializationBehavior.serialize(new Coordinates(0, 0)));
    }
}
