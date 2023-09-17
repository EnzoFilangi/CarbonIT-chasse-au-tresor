package org.carbon.simulation.map;

import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Coordinates coordinates){
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Coordinates coordinates)){
            return false;
        }
        return x == coordinates.getX() && y == coordinates.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
