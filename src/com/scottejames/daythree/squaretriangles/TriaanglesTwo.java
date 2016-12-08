package com.scottejames.daythree.squaretriangles;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scottejames.advent.utils.Utils;

public class TriaanglesTwo {

	public static void main(String[] args) throws IOException {
		String[] dataList = Utils.readFile("/daythree/squaretriangles/DayThreeData.txt");
		int count = 0;
		for (int i = 0; i < dataList.length; i += 3) {
			int[][] ints = new int[3][3];
			for (int j = 0; j < 3; j++) {
//				System.out.println(dataList[i+j]);

				String patternText = "\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)";
				Pattern pattern = Pattern.compile(patternText);
				Matcher matcher = pattern.matcher(dataList[i+j]);
				matcher.matches();
				ints[0][j] = Integer.parseInt(matcher.group(1));
				ints[1][j] = Integer.parseInt(matcher.group(2));
				ints[2][j] = Integer.parseInt(matcher.group(3));
			}
			for (int x = 0; x < 3; x++) {
				Arrays.sort(ints[x]);
				if (ints[x][0] + ints[x][1] > ints[x][2]){
					count++;
				}
//				for (int y = 0; y < 3; y++) {
//					System.out.print(ints[x][y] + ", ");
//				}
//				
//				System.out.println("");
			}
		}
		System.out.println("Count = " + count);
	}
}