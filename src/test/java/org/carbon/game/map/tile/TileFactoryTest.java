package org.carbon.game.map.tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TileFactoryTest {

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void plains_Should_Create(){
        assertNotNull(TileFactory.plains());
    }

    @Test
    public void mountains_Should_Create(){
        assertNotNull(TileFactory.mountains());
    }

    @Test
    public void treasure_Should_Create(){
        assertNotNull(TileFactory.treasure(1));
    }
}
