package com.scottejames.advent.daythirteen.maze;

public class Path {
    int x;
    int y;
    Path parent;

    public Path(int x, int y, Path parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public Path getParent() {
        return this.parent;
    }

    public String toString() {
        return "x = " + x + " y = " + y;
    }
}