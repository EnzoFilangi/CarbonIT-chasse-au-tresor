package org.carbon.simulation.adventurer;

public enum Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;
    
    public static Orientation orientationAfterLeftTurn(Orientation orientation){
        // Get the previous orientation in the list
        return Orientation.values()[Math.floorMod(orientation.ordinal() - 1, 4)];
    }
    public static Orientation orientationAfterRightTurn(Orientation orientation){
        // Get the next orientation in the list
        return Orientation.values()[Math.floorMod(orientation.ordinal() + 1, 4)];
    }
}
