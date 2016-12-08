package com.scottejames.daythree.squaretriangles;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scottejames.advent.utils.Utils;

public class Triangles {

	public static void main(String[] args) throws IOException {
		String [] dataList = Utils.readFile("/daythree/squaretriangles/DayThreeData.txt");
		int count = 0;
		for (String data: dataList){
			
			System.out.println(" Data: " + data);
			
			String patternText = "\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)";
			Pattern pattern = Pattern.compile(patternText);
			Matcher matcher = pattern.matcher(data);
			matcher.matches();
			
			int[] ints = new int[3];
			
 			ints[0] = Integer.parseInt(matcher.group(1));
			ints[1] = Integer.parseInt(matcher.group(2));
			ints[2] = Integer.parseInt(matcher.group(3));
			Arrays.sort(ints);
			if (ints[0] + ints[1] > ints[2]){
				count++;
			}
		}
		System.out.println("Count " + count);
	}

}
