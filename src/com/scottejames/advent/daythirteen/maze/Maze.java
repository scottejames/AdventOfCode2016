package com.scottejames.advent.daythirteen.maze;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Maze {
	public static int FAV = 1350;
	public static int ROW = 100;
	public static int LENGTH = 100;
	public static Queue<Path> queue = new LinkedList<Path>();
	public static int DEST_X = 31;
	public static int DEST_Y = 39;
	public static char[][] maze = new char[ROW][LENGTH];

	public static int bitCount(int value) {
		int count = 0;
		while (value > 0) { // until all bits are zero
			if ((value & 1) == 1) // check lower bit
				count++;
			value >>= 1; // shift bits, removing lower bit
		}
		return count;
	}

	public static boolean isWall(int x, int y) {
		int result = x * x + 3 * x + 2 * x * y + y + y * y;
		result += FAV;
		int count = bitCount(result);

		if ((count % 2) == 0)
			return false;
		else
			return true;
	}

	public static boolean isFree(int x, int y) {
		// Should clean this up.
		if ((x >= 0 && x < maze.length) && (y >= 0 && y < maze[x].length) && (maze[x][y] == '.')) {
			if (maze[x][y] != 'X')
				return true;
			else
				return false;
		}

		return false;
	}

	public static List<Path> getDistanceBFS(int x, int y) {
		List<Path> results = new LinkedList<Path>();

		queue.add(new Path(x, y, null));
		int fiftyCount = 0;
		while (!queue.isEmpty()) {
			System.out.println("queue depth " + queue.size());
			Path p = queue.remove();

			if (p.distance == 50)
				results.add(p);
			else {
				if (isFree(p.x + 1, p.y)) {
					maze[p.x][p.y] = 'X';
					Path next = new Path(p.x + 1, p.y, p);
					queue.add(next);
				}
				if (isFree(p.x - 1, p.y)) {
					maze[p.x][p.y] = 'X';
					Path next = new Path(p.x - 1, p.y, p);
					queue.add(next);
				}

				if (isFree(p.x, p.y + 1)) {
					maze[p.x][p.y] = 'X';
					Path next = new Path(p.x, p.y + 1, p);
					queue.add(next);
				}

				if (isFree(p.x, p.y - 1)) {
					maze[p.x][p.y] = 'X';
					Path next = new Path(p.x, p.y - 1, p);
					queue.add(next);
				}
			}
		}
		return results;

	}

	public static Path getPathBFS(int x, int y) {
		queue.add(new Path(x, y, null));
		int fiftyCount = 0;
		while (!queue.isEmpty()) {
			System.out.println("queue depth " + queue.size());
			Path p = queue.remove();
			// if ((p.x == DEST_X) && (p.y == DEST_Y)) {
			// System.out.println("Reached end");
			// return p;
			// }
			if (p.distance == 50)
				fiftyCount++;
			else {
				if (isFree(p.x + 1, p.y)) {
					maze[p.x][p.y] = 'X';
					Path next = new Path(p.x + 1, p.y, p);
					queue.add(next);
				}
				if (isFree(p.x - 1, p.y)) {
					maze[p.x][p.y] = 'X';
					Path next = new Path(p.x - 1, p.y, p);
					queue.add(next);
				}

				if (isFree(p.x, p.y + 1)) {
					maze[p.x][p.y] = 'X';
					Path next = new Path(p.x, p.y + 1, p);
					queue.add(next);
				}

				if (isFree(p.x, p.y - 1)) {
					maze[p.x][p.y] = 'X';
					Path next = new Path(p.x, p.y - 1, p);
					queue.add(next);
				}
			}
		}
		System.out.println("Fifty count " + fiftyCount);
		return null;

	}

	public static void main(String[] args) {
		for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze[y].length; x++) {
				if (isWall(x, y) == true)
					maze[x][y] = '#';
				else
					maze[x][y] = '.';
			}
		}
		// Calculate shortest path
		// Path p = getPathBFS(0, 0);

		// Reset the maze -- probably shoudl refactor this
		for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze[y].length; x++) {
				if (isWall(x, y) == true)
					maze[x][y] = '#';
				else
					maze[x][y] = '.';
			}
		}

		List<Path> paths = getDistanceBFS(0, 0);
		int count = 0;

		for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze[y].length; x++) {
				if (maze[x][y] == 'X') count++;
				System.out.print(maze[x][y]);
			}
			System.out.println("");
		}
		for (Path p : paths) {
			while (p.getParent() != null) {
				maze[p.x][p.y] = 'X';
				p = p.getParent();
			}
		}
		// Print path
//		for (int y = 0; y < maze.length; y++) {
//			for (int x = 0; x < maze[y].length; x++) {
//				if (maze[x][y] == 'X') count++;
//				System.out.print(maze[x][y]);
//			}
//			System.out.println("");
//		}
		System.out.println("count " + count);
	}

}
