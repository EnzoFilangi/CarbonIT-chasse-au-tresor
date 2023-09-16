package org.carbon.simulation.map;

import org.carbon.simulation.map.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int sizeX;
    private final int sizeY;
    private final Tile[][] map;

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = new Tile[sizeX][sizeY];
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

    public String display(){
        List<String> tileStrings = collectTileStrings();
        int maxTileStringSize = findLongestStringLength(tileStrings);
        return buildMapString(tileStrings, maxTileStringSize);
    }

    private List<String> collectTileStrings() {
        List<String> tileStrings = new ArrayList<>(sizeX * sizeY);

        for (int y = 0; y < sizeY; y++) { // Iterate line by line
            for (int x = 0; x < sizeX; x++) {
                tileStrings.add(getTileAt(x, y).display());
            }
        }

        return tileStrings;
    }

    private int findLongestStringLength(List<String> tileStrings) {
        int maxLength = 0;

        for (String s: tileStrings){
            maxLength = Math.max(maxLength, s.length());
        }

        return maxLength;
    }

    private String buildMapString(List<String> tileDisplay, int maxTileStringSize) {
        StringBuilder mapString = new StringBuilder();
        for (int i = 0; i < tileDisplay.size(); i++) {
            mapString.append(padRight(tileDisplay.get(i), maxTileStringSize + 1));

            if (i % sizeX == sizeX - 1) {
                mapString.append("\n");
            }
        }
        return mapString.toString();
    }

    // https://stackoverflow.com/a/391978
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
