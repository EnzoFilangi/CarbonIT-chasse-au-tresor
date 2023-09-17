package org.carbon.simulation;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.AdventurerFactory;
import org.carbon.simulation.graphics.SimulationGraphicsConfig;
import org.carbon.simulation.map.RegionMap;
import org.carbon.simulation.map.tile.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class SimulationFactory {
    /**
     * Returns an initialized Simulation from the given list of commands
     * @throws SimulationCreationException If one of the commands is invalid
     */
    public static Simulation createSimulationFromCommands(List<String> commands) throws SimulationCreationException {
        RegionMap regionMap = null;
        List<Adventurer> adventurers = new ArrayList<>();

        for (String command : commands) {
            String[] commandComponents = splitCommand(command);

            switch (commandComponents[0]) {
                case "C" -> regionMap = initializeRegionMap(commandComponents, regionMap); // Pass the regionMap to the method so it can check if one already exists
                case "A" -> adventurers.add(createNewAdventurer(commandComponents));
                case "M" -> addMountainToMap(commandComponents, regionMap);
                case "T" -> addTreasureToMap(commandComponents, regionMap);
            }
        }

        fillEmptyTilesWithPlains(regionMap);

        return new Simulation(
                SimulationGraphicsConfig.instantiateSimulationDisplay(),
                regionMap,
                adventurers
        );
    }

    private static String[] splitCommand(String command) {
        String[] commandComponents = command.split("-");

        // Trim all components to make parsing easier later
        for (int i = 0; i < commandComponents.length; i++) {
            commandComponents[i] = commandComponents[i].trim();
        }

        return commandComponents;
    }

    /**
     * Returns an initialized RegionMap if existingRegionMap is null.
     * @param commandComponents The command's components should have the form : ["C", {{Size X}}, {{Size Y}}]. All strings should have been trimmed beforehand.
     * @param existingRegionMap Used to ensure no map already exists.
     * @throws SimulationCreationException If a map already exists or the command is invalid.
     */
    private static RegionMap initializeRegionMap(String[] commandComponents, RegionMap existingRegionMap) throws SimulationCreationException {
        if (existingRegionMap != null) { // Ensure a map hasn't already been created
            throw new SimulationCreationException("Cannot have two map initialization instructions.");
        }
        if (commandComponents.length != 3) {
            throw new SimulationCreationException("Incomplete map initialization instruction.");
        }

        try {
            // Create the map if the components have correct values
            int sizeX = Integer.parseInt(commandComponents[1]);
            int sizeY = Integer.parseInt(commandComponents[2]);

            return new RegionMap(sizeX, sizeY);
        } catch (NumberFormatException e) {
            throw new SimulationCreationException("Invalid map initialization instruction.");
        }
    }

    /**
     * Inserts a new mountain type tile in the given RegionMap.
     * @param commandComponents The command's components should have the form : ["M", {{Pos X}}, {{Pos Y}}]. All strings should have been trimmed beforehand.
     * @param regionMap The RegionMap in which to insert the tile.
     * @throws SimulationCreationException If the command is invalid or RegionMap is null.
     */
    private static void addMountainToMap(String[] commandComponents, RegionMap regionMap) throws SimulationCreationException {
        ensureMapExists(regionMap);
        if (commandComponents.length != 3) {
            throw new SimulationCreationException("Incomplete mountain initialization instruction.");
        }

        try {
            int posX = Integer.parseInt(commandComponents[1]);
            int posY = Integer.parseInt(commandComponents[2]);

            regionMap.setTile(posX, posY, TileFactory.mountains());
        } catch (NumberFormatException e) {
            throw new SimulationCreationException("Invalid mountain initialization instruction.");
        }
    }

    /**
     * Inserts a new treasure type tile in the given RegionMap.
     * @param commandComponents The command's components should have the form : ["T", {{Pos X}}, {{Pos Y}}, {{Treasure quantity}}]. All strings should have been trimmed beforehand.
     * @param regionMap The RegionMap in which to insert the tile.
     * @throws SimulationCreationException If the command is invalid or RegionMap is null.
     */
    private static void addTreasureToMap(String[] commandComponents, RegionMap regionMap) throws SimulationCreationException {
        ensureMapExists(regionMap);
        if (commandComponents.length != 4) {
            throw new SimulationCreationException("Incomplete treasure initialization instruction.");
        }

        try {
            int posX = Integer.parseInt(commandComponents[1]);
            int posY = Integer.parseInt(commandComponents[2]);
            int treasureQuantity = Integer.parseInt(commandComponents[3].trim());

            regionMap.setTile(posX, posY, TileFactory.treasure(treasureQuantity));
        } catch (NumberFormatException e) {
            throw new SimulationCreationException("Invalid treasure initialization instruction.");
        }
    }

    private static void ensureMapExists(RegionMap regionMap) throws SimulationCreationException {
        if (regionMap == null) {
            throw new SimulationCreationException("A map creation instruction must appear before tile instructions.");
        }
    }

    /**
     * Returns an Adventurer from the given command.
     * @param commandComponents See AdventurerFactory.create for the command's format.
     */
    private static Adventurer createNewAdventurer(String[] commandComponents) throws SimulationCreationException {
        try {
            return AdventurerFactory.create(commandComponents);
        } catch (IllegalArgumentException e) {
            throw new SimulationCreationException(e.getMessage());
        }
    }

    /**
     * Fills all empty tiles from regionMap with new tiles of type plains.
     * @param regionMap The RegionMap to fill.
     * @throws SimulationCreationException If the RegionMap is null.
     */
    private static void fillEmptyTilesWithPlains(RegionMap regionMap) throws SimulationCreationException {
        if (regionMap == null) {
            throw new SimulationCreationException("No map initialization instruction found.");
        }

        for (int x = 0; x < regionMap.getSizeX(); x++) {
            for (int y = 0; y < regionMap.getSizeY(); y++) {
                if (regionMap.getTileAt(x, y) == null) {
                    regionMap.setTile(x, y, TileFactory.plains());
                }
            }
        }
    }
}
