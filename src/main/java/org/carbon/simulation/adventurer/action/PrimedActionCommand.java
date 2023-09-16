package org.carbon.simulation.adventurer.action;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.RegionMap;

public class PrimedActionCommand {
    private final Action action;
    private final Adventurer adventurer;
    private final RegionMap regionMap;

    public PrimedActionCommand(Action action, Adventurer adventurer, RegionMap regionMap) {
        this.action = action;
        this.adventurer = adventurer;
        this.regionMap = regionMap;
    }

    public void execute(){
        action.execute(adventurer, regionMap);
    }
}
