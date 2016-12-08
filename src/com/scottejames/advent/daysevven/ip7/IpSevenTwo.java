package com.scottejames.advent.daysevven.ip7;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scottejames.advent.utils.Utils;

public class IpSevenTwo {
	public static void main(String[] args) throws IOException {
		String[] list = Utils.readFile("/advent/daysevven/ip7/DaySevenData.txt");
		int count = 0;
		for (String s : list) {
//			System.out.println(s);

			String abaString = s.replaceAll("\\[(.*?)\\]", " ");
//			System.out.println(abaString);

			Pattern babPattern = Pattern.compile("([a-z])([a-z])(\\1)");
			String patternText = "\\[(.*?)\\]";
			Pattern pattern = Pattern.compile(patternText);
			Matcher matcher = pattern.matcher(s);
			boolean matched = false;
			while (matcher.find() && !matched) {
				String ht = matcher.group(1);
//				System.out.println("ht " + ht);
				Matcher babMatcher = babPattern.matcher(ht);
				boolean found = babMatcher.find();
				while (found && !matched) {
					String bab = babMatcher.group(1) + babMatcher.group(2) + babMatcher.group(3);
					if (bab.charAt(0) != bab.charAt(1)) {
						String aba = "" + bab.charAt(1) + bab.charAt(0) + bab.charAt(1);
//						System.out.println("Aba " + aba);
//						System.out.println("Bab  " + bab);
						if (abaString.contains(aba)) {
//							System.out.println("Legal ");
							count++;
							matched = true;
//							System.out.println(s);
						}
					}
					found = babMatcher.find(babMatcher.start() + 1);

				}
			}
		}
		System.out.println("Count " + count);
	}
}
