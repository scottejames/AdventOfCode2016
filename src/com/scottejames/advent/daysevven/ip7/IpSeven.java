package com.scottejames.advent.daysevven.ip7;

import java.io.IOException;

import com.scottejames.advent.utils.Utils;

public class IpSeven {

	public static void main(String[] args) throws IOException {
		String[] list = Utils.readFile("/advent/daysevven/ip7/DaySevenData.txt");
		int count = 0;
		for (String s : list) {
			if (!(s.length() <= 3)) {
				int inBrackets = 0;
				boolean foundAbba = false;
				boolean badHS = false;
				for (int i = 0; i < s.length() - 3; i++) {
					char a = s.charAt(i);
					char b = s.charAt(i + 1);
					char c = s.charAt(i + 2);
					char d = s.charAt(i + 3);
					if (a == '[')
						inBrackets++;
					if (a == ']')
						inBrackets--;
					
					if ((a == d && b == c) && a != b) {
						if (inBrackets == 0) {
							foundAbba = true;

						} else {
							badHS = true;
						}
					}
				}
				if (foundAbba && !badHS) count++;
			}
		}

		System.out.println("Count " + count);

	}

}
