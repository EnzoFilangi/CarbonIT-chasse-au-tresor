package org.carbon.simulation.adventurer.action;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.Coordinates;
import org.carbon.simulation.map.RegionMap;
import org.carbon.simulation.map.tile.Tile;

public class WalkAction implements Action {
    @Override
    public void execute(Adventurer adventurer, RegionMap map) {
        Coordinates coordinatesInFront = getCoordinatesInFrontOfAdventurer(adventurer);

        Tile currentTile = map.getTileAt(adventurer.getCoordinates());
        Tile destination = map.getTileAt(coordinatesInFront);
        if (destination == null){ // Ensure the destination tile exists (for example, if trying to go to (0, 3) in a 2x2 map)
            return;
        }

        if (destination.canWalkIn(adventurer)){
            currentTile.walkOut(adventurer);
            adventurer.setCoordinates(coordinatesInFront);
            destination.walkIn(adventurer);
        }
    }

    private Coordinates getCoordinatesInFrontOfAdventurer(Adventurer adventurer) {
        Coordinates currentCoordinates = adventurer.getCoordinates();
        Coordinates newCoordinates = new Coordinates(currentCoordinates);
        switch (adventurer.getOrientation()){
            case NORTH -> newCoordinates.setY(newCoordinates.getY() - 1);
            case EAST -> newCoordinates.setX(newCoordinates.getX() + 1);
            case SOUTH -> newCoordinates.setY(newCoordinates.getY() + 1);
            case WEST -> newCoordinates.setX(newCoordinates.getX() - 1);
        }
        return newCoordinates;
    }
}
