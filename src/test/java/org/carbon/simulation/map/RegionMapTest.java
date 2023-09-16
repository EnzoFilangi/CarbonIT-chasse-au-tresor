package org.carbon.simulation.map;

import org.carbon.simulation.map.tile.Tile;
import org.carbon.simulation.map.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegionMapTest {
    @Test
    public void should_Properly_Display_The_Map(){
        RegionMap regionMap = new RegionMap(2, 3);
        regionMap.setTile(0, 0, TileFactory.plains());
        regionMap.setTile(1, 0, TileFactory.plains());
        regionMap.setTile(0, 1, TileFactory.treasure(1));
        regionMap.setTile(1, 1, TileFactory.mountains());
        regionMap.setTile(0, 2, TileFactory.plains());
        regionMap.setTile(1, 2, TileFactory.plains());

        String mapDisplayed = regionMap.display();

        assertEquals(".    .    \nT(1) M    \n.    .    \n", mapDisplayed);
    }

    @Nested
    class GetTileAtTest {
        RegionMap regionMap;

        @BeforeEach
        public void setUp(){
            regionMap = new RegionMap(1, 1);
        }

        @Test
        public void should_Return_Right_Tile_With_XY(){
            Tile expectedTile = TileFactory.plains();
            regionMap.setTile(0, 0, expectedTile);

            assertEquals(expectedTile, regionMap.getTileAt(0, 0));
        }

        @Test
        public void should_Return_Right_Tile_With_Coordinates(){
            Tile expectedTile = TileFactory.plains();
            regionMap.setTile(0, 0, expectedTile);

            assertEquals(expectedTile, regionMap.getTileAt(new Coordinates(0, 0)));
        }

        @Test
        public void should_Return_Null_If_X_Negative(){
            assertNull(regionMap.getTileAt(-1, 0));
        }

        @Test
        public void should_Return_Null_If_Y_Negative(){
            assertNull(regionMap.getTileAt(0, -1));
        }
        @Test
        public void should_Return_Null_If_X_Too_Big(){
            assertNull(regionMap.getTileAt(10, 0));
        }
        @Test
        public void should_Return_Null_If_Y_Too_Big(){
            assertNull(regionMap.getTileAt(0, 10));
        }
    }

    @Nested
    class SetTileTest {
        RegionMap regionMap;

        @BeforeEach
        public void setUp(){
            regionMap = new RegionMap(1, 1);
        }

        @Test
        public void should_Properly_Set_Tile(){
            Tile expectedTile = TileFactory.plains();

            regionMap.setTile(0, 0, expectedTile);

            assertEquals(expectedTile, regionMap.getTileAt(0, 0));
        }

        @Test
        public void should_Do_Nothing_If_X_Negative(){
            Tile tile = TileFactory.plains();
            assertDoesNotThrow(() -> regionMap.setTile(-1, 0, tile));
        }

        @Test
        public void should_Do_Nothing_If_Y_Negative(){
            Tile tile = TileFactory.plains();
            assertDoesNotThrow(() -> regionMap.setTile(0, -1, tile));
        }
        @Test
        public void should_Do_Nothing_If_X_Too_Big(){
            Tile tile = TileFactory.plains();
            assertDoesNotThrow(() -> regionMap.setTile(10, 0, tile));
        }
        @Test
        public void should_Do_Nothing_If_Y_Too_Big(){
            Tile tile = TileFactory.plains();
            assertDoesNotThrow(() -> regionMap.setTile(0, 10, tile));
        }
    }
}
