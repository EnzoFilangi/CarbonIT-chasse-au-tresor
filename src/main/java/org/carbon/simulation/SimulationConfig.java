package org.carbon.simulation;

import org.carbon.simulation.graphics.ConsoleSimulationDisplay;
import org.carbon.simulation.graphics.SimulationDisplay;
import org.carbon.simulation.graphics.StandardOutputWrapper;

public class SimulationConfig {
    public static SimulationDisplay instantiateSimulationDisplay(){
        return new ConsoleSimulationDisplay(new StandardOutputWrapper());
    }
}
