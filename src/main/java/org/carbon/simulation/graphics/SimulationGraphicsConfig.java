package org.carbon.simulation.graphics;

public class SimulationGraphicsConfig {
    public static SimulationDisplay instantiateSimulationDisplay(){
        return new ConsoleSimulationDisplay(new StandardOutputWrapper());
    }
}
