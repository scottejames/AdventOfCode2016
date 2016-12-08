package com.scottejames.advent.daysix.signalsnnoise;

import java.io.IOException;

import com.scottejames.advent.utils.Utils;

public class SandN {

	public static void main(String[] args) throws IOException {
		final int word_length = 8;
		String[] list = Utils.readFile("/advent/daysix/signalsnnoise/DaySixData.txt");
		// Initialise the hash - assume all strings are of equal length
		int[][] freq = new int[word_length][26];
		for (int x = 0; x < word_length; x++)
			for (int y = 0; y < 26; y++) {
				freq[x][y] = 0;
			}
		for (String s : list)
			for (int i = 0; i < word_length; i++) {
				// System.out.println("checking String " + s + " char " +
				// s.charAt(i) + " i " + i + " index " + (s.charAt(i) - 'a'));
				freq[i][s.charAt(i) - 'a']++;
			}
		for (int x = 0; x < word_length; x++) {
			for (int y = 0; y < 26; y++)
				System.out.print(" [" + freq[x][y] + "] ");
			System.out.println("");
		}
		for (int x = 0; x < word_length; x++) {
			int maxChar = 0;
			int max = 0;
			int minChar = 0;
			int min = Integer.MAX_VALUE;
			for (int y = 0; y < 26; y++) {
				if (freq[x][y] > max) {
					max = freq[x][y];
					maxChar = y;
				}
				if (freq[x][y] < min) {
					min = freq[x][y];
					minChar = y;
				}
			}
			char c = (char) (minChar + 'a');
			System.out.print(c);
		}
		System.out.println("");
	}
}
