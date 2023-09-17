package org.carbon.simulation.graphics;

import org.carbon.simulation.graphics.console.ConsoleBasedSimulationDisplay;
import org.carbon.simulation.graphics.console.StandardOutputWrapper;

public class SimulationGraphicsConfig {
    public static SimulationDisplay instantiateSimulationDisplay(){
        return new ConsoleBasedSimulationDisplay(new StandardOutputWrapper());
    }
}
