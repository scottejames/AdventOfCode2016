package com.scottejames.advent.daythirteen.maze;

public class Path {
	int distance = 1;
	int x;
	int y;
	Path parent;

	public Path(int x, int y, Path parent) {
		if (parent == null)
			this.distance = 0;
		else
			this.distance = parent.distance + 1;
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