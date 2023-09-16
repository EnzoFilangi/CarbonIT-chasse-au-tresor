package org.carbon.game.map;

import org.carbon.game.map.tile.Tile;
import org.carbon.game.map.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    @Test
    public void should_Properly_Display_The_Map(){
        Map map = new Map(2, 3);
        map.setTile(0, 0, TileFactory.plains());
        map.setTile(1, 0, TileFactory.plains());
        map.setTile(0, 1, TileFactory.treasure(1));
        map.setTile(1, 1, TileFactory.mountains());
        map.setTile(0, 2, TileFactory.plains());
        map.setTile(1, 2, TileFactory.plains());

        String mapDisplayed = map.display();

        assertEquals(".    .    \nT(1) M    \n.    .    \n", mapDisplayed);
    }

    @Nested
    class GetTileAtTest {
        Map map;

        @BeforeEach
        public void setUp(){
            map = new Map(1, 1);
        }

        @Test
        public void should_Return_Right_Tile_With_XY(){
            Tile expectedTile = TileFactory.plains();
            map.setTile(0, 0, expectedTile);

            assertEquals(expectedTile, map.getTileAt(0, 0));
        }

        @Test
        public void should_Return_Right_Tile_With_Coordinates(){
            Tile expectedTile = TileFactory.plains();
            map.setTile(0, 0, expectedTile);

            assertEquals(expectedTile, map.getTileAt(new Coordinates(0, 0)));
        }

        @Test
        public void should_Return_Null_If_X_Negative(){
            assertNull(map.getTileAt(-1, 0));
        }

        @Test
        public void should_Return_Null_If_Y_Negative(){
            assertNull(map.getTileAt(0, -1));
        }
        @Test
        public void should_Return_Null_If_X_Too_Big(){
            assertNull(map.getTileAt(10, 0));
        }
        @Test
        public void should_Return_Null_If_Y_Too_Big(){
            assertNull(map.getTileAt(0, 10));
        }
    }

    @Nested
    class SetTileTest {
        Map map;

        @BeforeEach
        public void setUp(){
            map = new Map(1, 1);
        }

        @Test
        public void should_Properly_Set_Tile(){
            Tile expectedTile = TileFactory.plains();

            map.setTile(0, 0, expectedTile);

            assertEquals(expectedTile, map.getTileAt(0, 0));
        }

        @Test
        public void should_Do_Nothing_If_X_Negative(){
            Tile tile = TileFactory.plains();
            assertDoesNotThrow(() -> map.setTile(-1, 0, tile));
        }

        @Test
        public void should_Do_Nothing_If_Y_Negative(){
            Tile tile = TileFactory.plains();
            assertDoesNotThrow(() -> map.setTile(0, -1, tile));
        }
        @Test
        public void should_Do_Nothing_If_X_Too_Big(){
            Tile tile = TileFactory.plains();
            assertDoesNotThrow(() -> map.setTile(10, 0, tile));
        }
        @Test
        public void should_Do_Nothing_If_Y_Too_Big(){
            Tile tile = TileFactory.plains();
            assertDoesNotThrow(() -> map.setTile(0, 10, tile));
        }
    }
}
