package org.carbon.simulation.map;

import org.carbon.simulation.map.tile.Tile;

public class RegionMap {
    private final int sizeX;
    private final int sizeY;
    private final Tile[][] map;

    public RegionMap(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = new Tile[sizeX][sizeY];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Tile getTileAt(Coordinates coordinates){
        return getTileAt(coordinates.getX(), coordinates.getY());
    }

    public Tile getTileAt(int x, int y){
        if (x < 0 || x >= sizeX){
            return null;
        }
        if (y < 0 || y >= sizeY){
            return null;
        }
        return map[x][y];
    }

    public void setTile(int x, int y, Tile tile){
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY){
            return;
        }
        map[x][y] = tile;
    }

    public String[][] display(){
        String[][] tileStrings = new String[sizeX][sizeY];

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                tileStrings[x][y] = map[x][y].display();
            }
        }

        return tileStrings;
    }
}
