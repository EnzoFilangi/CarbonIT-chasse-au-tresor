package org.carbon.game.map.tile.behavior.graphical;

public class StaticGraphicalBehavior implements GraphicalBehavior {
    private final String letter;

    public StaticGraphicalBehavior(String letter) {
        this.letter = letter;
    }

    @Override
    public String display() {
        return letter;
    }
}
