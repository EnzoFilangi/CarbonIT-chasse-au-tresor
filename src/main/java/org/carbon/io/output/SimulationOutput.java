package org.carbon.io.output;

public interface SimulationOutput {
    void saveSimulationResult(String simulationResultSerialized);
    void alertSimulationError(String errorMessage);
}
