package org.carbon.game.map.tile.behavior.graphical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterGraphicalBehaviorTest {
    @Test
    public void display_Should_Return_Letter(){
        String expectedLetter = "M";
        LetterGraphicalBehavior letterGraphicalBehavior = new LetterGraphicalBehavior(expectedLetter);

        assertEquals(expectedLetter, letterGraphicalBehavior.display());
    }
}
