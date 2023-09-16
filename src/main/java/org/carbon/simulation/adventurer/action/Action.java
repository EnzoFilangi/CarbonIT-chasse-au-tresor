package org.carbon.simulation.adventurer.action;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.RegionMap;

public interface Action {
    void execute(Adventurer adventurer, RegionMap map);
}
