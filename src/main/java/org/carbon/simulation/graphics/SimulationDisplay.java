package org.carbon.simulation.graphics;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.RegionMap;

import java.util.List;

public interface SimulationDisplay {
    void display(RegionMap map, List<Adventurer> adventurers);
}
