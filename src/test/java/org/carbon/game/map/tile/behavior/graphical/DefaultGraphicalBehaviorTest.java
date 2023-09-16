package org.carbon.game.map.tile.behavior.graphical;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultGraphicalBehaviorTest {
    private DefaultGraphicalBehavior defaultGraphicalBehavior;

    @BeforeEach
    public void setUp(){
        defaultGraphicalBehavior = new DefaultGraphicalBehavior();
    }

    @Test
    public void display_Should_Return_Dot(){
        assertEquals(".", defaultGraphicalBehavior.display());
    }
}
