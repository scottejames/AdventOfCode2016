package com.scottejames.advent.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static boolean isInteger(String str) {
		try {
			int d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static int findMatches(String line, String pattern) {
		Matcher matcher = Pattern.compile(pattern).matcher(line);
		int matches = 0;
		while (matcher.find()) {
			matches++;
		}
		return matches;
	}

	public static String[] readFile(String fileName) throws IOException{
		Path filePath = new File("target/classes/com/scottejames/" +fileName).toPath();
		System.out.println(filePath);
		Charset charset = Charset.defaultCharset();
		List<String> stringList = Files.readAllLines(filePath, charset);
		String[] stringArray = stringList.toArray(new String[] {});
		return stringArray;
	}
}
