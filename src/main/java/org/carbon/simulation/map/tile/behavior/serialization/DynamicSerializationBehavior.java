package org.carbon.simulation.map.tile.behavior.serialization;

import org.carbon.simulation.map.Coordinates;

import java.util.concurrent.Callable;

public class DynamicSerializationBehavior implements SerializationBehavior {
    private final String prefix;
    private final Callable<String> dynamicValueEvaluator;

    public DynamicSerializationBehavior(String prefix, Callable<String> dynamicValueEvaluator) {
        this.prefix = prefix;
        this.dynamicValueEvaluator = dynamicValueEvaluator;
    }

    @Override
    public String serialize(Coordinates coordinates) {
        try {
            return prefix + " - " + coordinates.getX() + " - " + coordinates.getY() + " - " + dynamicValueEvaluator.call() + "\n";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
