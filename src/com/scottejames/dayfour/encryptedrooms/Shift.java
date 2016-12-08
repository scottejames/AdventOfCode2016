package com.scottejames.dayfour.encryptedrooms;

public class Shift {
	public static void main(String[] args) {
		String data = "qzmt-zixmtkozy-ivhz";
		int sector = 343;
		System.out.println(incrString(data,sector));
	}

	public static String incrString(String s, int c){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++)
				sb.append(incrChar(s.charAt(i),c));
		return sb.toString();
	}

	public static char incrChar(char c, int count) {
		if (c=='-') return ' ';
		for (int j = 0; j < count; j++) {
			
			c = (char) ((int) c + 1);
			if (c > 'z') {
				c = 'a';
			}
		}
		return c;
	}
}
