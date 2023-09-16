package org.carbon.simulation.map.tile.behavior.graphical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DynamicGraphicalBehaviorTest {
    @Test
    public void should_Evaluate_Lambda_When_Displaying(){
        String expectedInnerString = "Lorem";
        String expectedPrefix = "T";

        DynamicGraphicalBehavior dynamicGraphicalBehavior = new DynamicGraphicalBehavior(
                expectedPrefix,
                () -> expectedInnerString
        );

        String expectedResult = expectedPrefix + "(" + expectedInnerString + ")";
        assertEquals(expectedResult, dynamicGraphicalBehavior.display());
    }

    @Test
    public void should_Propagate_Exception(){
        DynamicGraphicalBehavior dynamicGraphicalBehavior = new DynamicGraphicalBehavior(
                "T",
                () -> {
                    throw new Exception("Test");
                }
        );

        assertThrows(RuntimeException.class, dynamicGraphicalBehavior::display);
    }
}
