package org.carbon.game.map.tile.behavior.graphical;

public class LetterGraphicalBehavior implements GraphicalBehavior {
    private final String letter;

    public LetterGraphicalBehavior(String letter) {
        this.letter = letter;
    }

    @Override
    public String display() {
        return letter;
    }
}
