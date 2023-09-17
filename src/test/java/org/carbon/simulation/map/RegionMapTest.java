package org.carbon.simulation.map;

import org.carbon.simulation.map.tile.Tile;
import org.carbon.simulation.map.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegionMapTest {

    @Nested
    class GetTileAtTest {
        private RegionMap regionMap;

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
        private RegionMap regionMap;

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

    @Nested
    class SerializeTest {
        @Test
        public void should_Serialize_Simple_Map() {
            RegionMap map = new RegionMap(1, 1);
            map.setTile(0, 0, TileFactory.plains());

            String serializationResult = map.serialize();

            assertEquals("C - 1 - 1\n", serializationResult);
        }

        @Test
        public void should_Serialize_Complex_Map() {
            RegionMap map = new RegionMap(2, 2);
            map.setTile(0, 0, TileFactory.plains());
            map.setTile(1, 1, TileFactory.plains());
            map.setTile(0, 1, TileFactory.mountains());
            map.setTile(1, 0, TileFactory.treasure(2));

            String serializationResult = map.serialize();

            assertEquals("C - 2 - 2\nM - 0 - 1\nT - 1 - 0 - 2\n", serializationResult);
        }
    }
}
