package org.carbon;

import org.carbon.io.input.SimulationInput;
import org.carbon.io.input.SimulationInputConfig;
import org.carbon.io.output.SimulationOutput;
import org.carbon.io.output.SimulationOutputConfig;
import org.carbon.simulation.Simulation;
import org.carbon.simulation.SimulationCreationException;
import org.carbon.simulation.SimulationFactory;

import java.util.List;

public class Main {
    private static SimulationInput simulationInput;
    private static SimulationOutput simulationOutput;

    private static void configure(){
        simulationInput = SimulationInputConfig.instantiateSimulationInput();
        simulationOutput = SimulationOutputConfig.instantiateSimulationOutput();
    }

    public static void main(String[] args){
        configure();

        try {
            List<String> initializationCommands = simulationInput.getInitializationCommands();
            Simulation simulation = SimulationFactory.createSimulationFromCommands(initializationCommands);

            simulation.runSimulation();

            String simulationResultSerialized = simulation.serialize();
            simulationOutput.saveSimulationResult(simulationResultSerialized);
        } catch (SimulationCreationException e) {
            simulationOutput.alertSimulationError(e.getMessage());
        }
    }
}
