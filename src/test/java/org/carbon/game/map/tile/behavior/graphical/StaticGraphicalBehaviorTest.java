package org.carbon.game.map.tile.behavior.graphical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticGraphicalBehaviorTest {
    @Test
    public void display_Should_Return_Letter(){
        String expectedLetter = "M";
        StaticGraphicalBehavior staticGraphicalBehavior = new StaticGraphicalBehavior(expectedLetter);

        assertEquals(expectedLetter, staticGraphicalBehavior.display());
    }
}
