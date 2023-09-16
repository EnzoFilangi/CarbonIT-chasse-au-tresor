package org.carbon.game.map.tile;

import org.carbon.game.map.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TileFactoryTest {
    @Mock
    Coordinates coordinates;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void plains_Should_Create(){
        assertNotNull(TileFactory.plains(coordinates));
    }

    @Test
    public void mountains_Should_Create(){
        assertNotNull(TileFactory.mountains(coordinates));
    }

    @Test
    public void treasure_Should_Create(){
        assertNotNull(TileFactory.treasure(coordinates, 1));
    }
}
