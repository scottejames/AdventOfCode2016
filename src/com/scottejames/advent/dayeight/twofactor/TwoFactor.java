package com.scottejames.advent.dayeight.twofactor;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scottejames.advent.utils.Utils;

public class TwoFactor {

	public static void main(String[] args) throws IOException {
		final int WIDTH = 50;
		final int HEIGHT = 6;

		Pattern rect = Pattern.compile("rect (\\d+)x(\\d+)");
		Pattern rotRow = Pattern.compile("rotate row y=(\\d+) by (\\d+)");
		Pattern rotCol = Pattern.compile("rotate column x=(\\d+) by (\\d+)");

		char[][] grid = new char[HEIGHT][WIDTH];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = '-';
			}
		}
		String[] list = Utils.readFile("/advent/dayeight/twofactor/TwoFactorData.txt");

		for (String i : list) {
			Matcher rectM = rect.matcher(i);
			Matcher rotRowM = rotRow.matcher(i);
			Matcher rotColM = rotCol.matcher(i);
			if (rectM.find()) {
				int col = Integer.parseInt(rectM.group(1));
				int row = Integer.parseInt(rectM.group(2));
				setRect(grid, col, row);
			} else if (rotColM.find()) {

				int col = Integer.parseInt(rotColM.group(1));
				int count = Integer.parseInt(rotColM.group(2));
				rotCol(grid, col, count);
			} else if (rotRowM.find()) {

				int row = Integer.parseInt(rotRowM.group(1));
				int count = Integer.parseInt(rotRowM.group(2));
				rotRow(grid, row, count);
			}
		}
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '#'	)
					count++;
			}
		}
		System.out.println("Count " + count);
		showScreen(grid);

	}

	private static void rotRow(char[][] grid, int row, int count) {
		for (int j = 0; j < count; j++) {
			char next = grid[row][0];
			for (int i = 0; i < grid[row].length - 1; i++) {
				char tmp = grid[row][i + 1];
				grid[row][i + 1] = next;
				next = tmp;
			}
			grid[row][0] = next;
		}
	}

	private static void rotCol(char[][] grid, int col, int count) {
		for (int j = 0; j < count; j++) {
			char next = grid[0][col];
			for (int i = 0; i < grid.length - 1; i++) {
				char tmp = grid[i + 1][col];
				grid[i + 1][col] = next;
				next = tmp;
			}
			grid[0][col] = next;
		}
	}

	private static void setRect(char[][] grid, int col, int row) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				grid[i][j] = '#';
			}
		}
	}

	private static void showScreen(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println(" ");
		}
	}

}
