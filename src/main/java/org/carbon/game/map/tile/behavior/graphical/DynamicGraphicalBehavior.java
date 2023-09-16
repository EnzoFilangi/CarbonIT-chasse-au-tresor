package org.carbon.game.map.tile.behavior.graphical;

import java.util.concurrent.Callable;

public class DynamicGraphicalBehavior implements GraphicalBehavior {
    private final String prefix;
    private final Callable<String> dynamicValueEvaluator;

    public DynamicGraphicalBehavior(String prefix, Callable<String> dynamicValueEvaluator) {
        this.prefix = prefix;
        this.dynamicValueEvaluator = dynamicValueEvaluator;
    }

    @Override
    public String display() {
        try {
            return prefix + "(" + dynamicValueEvaluator.call() + ")";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
