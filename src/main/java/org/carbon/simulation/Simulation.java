package org.carbon.simulation;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.adventurer.action.Action;
import org.carbon.simulation.adventurer.action.PrimedActionCommand;
import org.carbon.simulation.graphics.SimulationDisplay;
import org.carbon.simulation.map.RegionMap;

import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final SimulationDisplay simulationDisplay;
    private final RegionMap map;
    private final List<Adventurer> adventurers;

    public Simulation(SimulationDisplay simulationDisplay, RegionMap map, List<Adventurer> adventurers) {
        this.simulationDisplay = simulationDisplay;
        this.map = map;
        this.adventurers = adventurers;
    }

    public void runSimulation(){
        boolean actionsLeft = true;
        while (actionsLeft) {
            displaySimulationState(); // Display the state before updating it to shown the initial map
            actionsLeft = simulateRound();
        }
        // No need to display after the simulation has ended since the state didn't change in the last loop (otherwise actionsLeft wouldn't be false)
    }

    private boolean simulateRound() {
        List<PrimedActionCommand> roundActions = buildRoundActionBacklog();
        if (roundActions.size() == 0){
            return false;
        }

        runActionBacklog(roundActions);
        return true;
    }

    private List<PrimedActionCommand> buildRoundActionBacklog() {
        LinkedList<PrimedActionCommand> globalActionBacklog = new LinkedList<>();

        // Build the list of actions for a round by interleaving the actions of the adventurers
        for (Adventurer adventurer : adventurers){
            Action action = adventurer.getAndRemoveNextAction();
            if (action != null){
                globalActionBacklog.add(new PrimedActionCommand(action, adventurer, map));
            }
        }

        return globalActionBacklog;
    }

    private void runActionBacklog(List<PrimedActionCommand> globalActionBacklog) {
        for (PrimedActionCommand action: globalActionBacklog) {
            action.execute();
        }
    }

    private void displaySimulationState(){
        simulationDisplay.display(map, adventurers);
    }

    public RegionMap getMap() {
        return map;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(map.serialize());
        for (Adventurer adventurer: adventurers){
            stringBuilder.append(adventurer.serialize());
        }

        return stringBuilder.toString();
    }
}
