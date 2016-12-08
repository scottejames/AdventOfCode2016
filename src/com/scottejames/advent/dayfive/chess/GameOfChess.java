package com.scottejames.advent.dayfive.chess;

import java.security.NoSuchAlgorithmException;

public class GameOfChess {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String start = "uqwqemis";
		int count = 0;
		boolean resultFound = false;
		StringBuffer result = new StringBuffer();
		for (int j = 0; j < 8; j++) {
			while (!resultFound) {
				String test = start + count;
				java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
				byte[] array = md.digest(test.getBytes());

				StringBuffer sb = new StringBuffer();

				for (int i = 0; i < array.length; ++i) {
					sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
				}
				if (sb.toString().substring(0, 5).equals("00000")) {
					resultFound = true;
					System.out.println("Answer is " + count);
					System.out.println("Hash is " + sb.toString());
					result.append(sb.toString().charAt(5));
				}
				count++;
			}
			resultFound = false;
		}
		System.out.println("Password " + result.toString());
	}
}
