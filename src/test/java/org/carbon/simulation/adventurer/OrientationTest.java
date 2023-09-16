package org.carbon.simulation.adventurer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {
    @Nested
    class OrientationAfterLeftTurnTest {
        @Test
        public void should_Return_West_When_North(){
            assertEquals(Orientation.WEST, Orientation.orientationAfterLeftTurn(Orientation.NORTH));
        }

        @Test
        public void should_Return_South_When_West(){
            assertEquals(Orientation.SOUTH, Orientation.orientationAfterLeftTurn(Orientation.WEST));
        }

        @Test
        public void should_Return_East_When_South(){
            assertEquals(Orientation.EAST, Orientation.orientationAfterLeftTurn(Orientation.SOUTH));
        }

        @Test
        public void should_Return_North_When_East(){
            assertEquals(Orientation.NORTH, Orientation.orientationAfterLeftTurn(Orientation.EAST));
        }
    }

    @Nested
    class OrientationAfterRightTurnTest {
        @Test
        public void should_Return_Ease_When_North(){
            assertEquals(Orientation.EAST, Orientation.orientationAfterRightTurn(Orientation.NORTH));
        }

        @Test
        public void should_Return_North_When_West(){
            assertEquals(Orientation.NORTH, Orientation.orientationAfterRightTurn(Orientation.WEST));
        }

        @Test
        public void should_Return_West_When_South(){
            assertEquals(Orientation.WEST, Orientation.orientationAfterRightTurn(Orientation.SOUTH));
        }

        @Test
        public void should_Return_South_When_East(){
            assertEquals(Orientation.SOUTH, Orientation.orientationAfterRightTurn(Orientation.EAST));
        }
    }
}
