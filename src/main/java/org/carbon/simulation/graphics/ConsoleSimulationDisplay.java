package org.carbon.simulation.graphics;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.RegionMap;

import java.util.List;

public class ConsoleSimulationDisplay implements SimulationDisplay {

    private final StandardOutputWrapper standardOutputWrapper;

    public ConsoleSimulationDisplay(StandardOutputWrapper standardOutputWrapper) {
        this.standardOutputWrapper = standardOutputWrapper;
    }

    @Override
    public void display(RegionMap map, List<Adventurer> adventurers) {
        String[][] tileStrings = map.display();
        applyAdventurers(tileStrings, adventurers);
        String mapString = buildMapString(tileStrings);
        standardOutputWrapper.print(mapString);
    }

    private void applyAdventurers(String[][] mapWithoutAdventurers, List<Adventurer> adventurers) {
        for (Adventurer adventurer : adventurers) {
            mapWithoutAdventurers[adventurer.getCoordinates().getX()][adventurer.getCoordinates().getY()] = "A";
        }
    }

    private int findLongestStringLength(String[][] tileStrings) {
        int maxLength = 0;

        for (String[] row : tileStrings) {
            for (String tileString: row) {
                maxLength = Math.max(maxLength, tileString.length());
            }
        }

        return maxLength;
    }

    // https://stackoverflow.com/a/391978
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    private String buildMapString(String[][] tileStrings) {
        int maxStringLength = findLongestStringLength(tileStrings);

        StringBuilder mapString = new StringBuilder();
        // Print is done by line so loop on Y at the top level so for each Y we scan all X
        for (int y = 0; y < tileStrings[0].length; y++) { // Use the fact that the map is rectangular to find the max of Y
            for (int x = 0; x < tileStrings.length; x++) {
                mapString.append(padRight(tileStrings[x][y], maxStringLength));
                mapString.append(" "); // Add a separator between each tile
            }
            mapString.append("\n");
        }

        return mapString.toString();
    }
}
