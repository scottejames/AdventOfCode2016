package com.scottejames.advent.dayfive.chess;

import java.security.NoSuchAlgorithmException;

public class GameOfChessSecondStar {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String start = "uqwqemis";
		int count = 0;
		boolean resultFound = false;
		char[] result = new char[8];
		for (int x = 0;x< result.length;x++) result[x] = '-';
		boolean passwordComplete = false;
		while (!passwordComplete) {
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
					String hash = sb.toString();

					System.out.println("Answer is " + count);
					System.out.println("Hash is " + hash);
					
					int position = Character.getNumericValue(hash.charAt(5));
					
					System.out.println("position : " + position);
					char value = hash.charAt(6);
					System.out.println("value : " + value);
					if ((position >= 0) && (position<=7)){
						if (result[position]=='-'){
							result[position] = value;
							System.out.println(result);
						} else System.out.println("value already found");
					} else System.out.println("position out of range");
					
				}
				count++;
			}
			resultFound = false;
			passwordComplete = true;
			for (char c : result){
				if (c=='-') passwordComplete =false;
			}
		}
		for (char c : result){
			System.out.print(c);
		}
		System.out.println("");
	}
}
