package com.scottejames.advent.daytwentyone.scrambled;

import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Collections2;

public class Scrambled {
	public static String[] readFile(String fileName) throws IOException {
		Path filePath = new File("target/classes/com/scottejames/" + fileName).toPath();
		System.out.println(filePath);
		Charset charset = Charset.defaultCharset();
		List<String> stringList = Files.readAllLines(filePath, charset);
		String[] stringArray = stringList.toArray(new String[] {});
		return stringArray;
	}

	static List<Character> password = null;

	public static void main(String[] args) throws IOException {
		// System.out.println("password : " + password);
		// swap(4, 0);
		// System.out.println("swap : " + password);
		// swapChars('d', 'b');
		// System.out.println("swapChars : " + password);
		// reverse(0, 4);
		// System.out.println("reverse : " + password);
		// reverse(0, 2);
		// System.out.println("reverse : " + password);
		//
		// rotate(1);
		// System.out.println("rotate : " + password);
		// move(1, 4);
		// System.out.println("move : " + password);
		// move(3, 0);
		// System.out.println("move : " + password);
		// rotateChar('b');
		// System.out.println("rotate : " + password);
		// rotateChar('d');
		// System.out.println("rotate : " + password);

		String[] data = readFile("/advent/daytwentyone/scrambled/Data.txt");
		Character[] rawPassword = new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
		List<Character> seedPassword = new ArrayList<>(Arrays.asList(rawPassword)); // good
		Collection<List<Character>> permutations = Collections2.permutations(seedPassword);

		for (List<Character> trialPW : permutations) {

			password = new ArrayList<Character>(trialPW);
			for (String instruction : data) {
				String[] tokens = instruction.split(" ");
				// System.out.println("Instruction: " + instruction);
				// System.out.println("Password before: " + password);
				switch (tokens[0]) {
				case "swap":
					if (tokens[1].equals("position"))
						swap(parseInt(tokens[2]), parseInt(tokens[5]));
					else
						swapChars(tokens[2].charAt(0), tokens[5].charAt(0));
					break;
				case "rotate":
					if (tokens[1].equals("based"))
						rotateChar(tokens[6].charAt(0));
					else {
						int distance = 0;
						if (tokens[1].equals("left"))
							distance = parseInt(tokens[2]);
						else
							distance = -1 * parseInt(tokens[2]);
						rotate(distance);
					}
					break;
				case "move":
					move(parseInt(tokens[2]), parseInt(tokens[5]));
					break;
				case "reverse":
					reverse(parseInt(tokens[2]), parseInt(tokens[4]));
					break;
				default:
					System.err.println("Parse error!");

				}

			}
			if (checkPassword(password)) {
				System.out.println("Password before: " + trialPW);
				System.out.println("Password after: " + password);
			}
		}
	}

	private static void move(int i, int j) {
		char c = password.remove(i);
		password.add(j, c);
	}

	private static void rotateChar(char c) {
		int index = findIndex(c);
		int count = index >= 4 ? index + 1 : index;
		rotate(-1 * (count + 1));
	}

	private static void rotate(int i) {
		Collections.rotate(password, i * -1);
	}

	private static void reverse(int x, int y) {
		// 1,2,3,4,5
		// 5,2,3,4,1
		// 5,4,3,2,1

		for (int i = 0; i <= (y - x) / 2; i++) {
			swap(x + i, y - i);
		}
	}

	static int findIndex(int c) {
		for (int i = 0; i < password.size(); i++) {
			if (password.get(i) == (char) c)
				return i;
		}
		return -1;
	}

	private static void swapChars(char x, char y) {
		Collections.swap(password, findIndex(x), findIndex(y));

	}

	private static void swap(Integer x, Integer y) {
		Collections.swap(password, x, y);
	}

	private static boolean checkPassword(List<Character> trial) {
		String test = "fbgdceah";
		boolean result = true;
		for (int i = 0; i < trial.size(); i++) {
			if (trial.get(i) != test.charAt(i)) {
				result = false;
			}
		}
		return result;
	}
}
