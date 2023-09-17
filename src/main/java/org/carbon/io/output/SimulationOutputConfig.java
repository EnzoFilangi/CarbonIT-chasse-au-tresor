package org.carbon.io.output;

public class SimulationOutputConfig {
    public static SimulationOutput instantiateSimulationOutput(){
        return new FileBasedSimulationOutput();
    }
}
