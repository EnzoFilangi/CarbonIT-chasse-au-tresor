package org.carbon.simulation.adventurer.action;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.RegionMap;

public class TurnRightAction implements Action {
    @Override
    public void execute(Adventurer adventurer, RegionMap map) {
        adventurer.turnRight();
    }
}
