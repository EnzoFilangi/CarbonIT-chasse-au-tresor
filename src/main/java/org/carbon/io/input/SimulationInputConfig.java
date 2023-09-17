package org.carbon.io.input;

public class SimulationInputConfig {
    public static SimulationInput instantiateSimulationInput() {
        return new FileBasedSimulationInput();
    }
}
