package com.scottejames.advent.daynine.explosive;

import java.io.IOException;

import com.scottejames.advent.utils.Utils;

public class Stepper {


	public static void main(String[] args) throws IOException {
		String [] data = Utils.readFile("/advent/daynine/explosive/Simple.txt");
		for (String s: data)
		{
			countString( s);
		}
	}

	private static void countString(String s) {
		int count = 0;
		StringBuffer result = new StringBuffer();
		System.out.println("Processing " + s);
		for (int i = 0; i < s.length(); i ++){
			System.out.println("Considering " + s.charAt(i));
			if (s.charAt(i) == '('){
				int length = Integer.parseInt(s.substring(i+1, s.indexOf("x",i)));
				int copyNum = Integer.parseInt(s.substring(s.indexOf("x",i) + 1, s.indexOf(")",i)));
				i = s.indexOf(")",i);
				System.out.println("Length = " + length + " copyNum " + copyNum);
				StringBuffer repeat = new StringBuffer();
				int offset = 0;
				for (int j = 1; j <= length; j++){
					if (s.charAt(offset+i+j) == '('){
						offset = s.indexOf(")",i+j);
						repeat.append(s.substring(i+j, i+j+offset));
					} else{
						repeat.append(s.charAt(i+j+offset));
					}
				}
				for (int j = 0; j < copyNum; j++){
					result.append(repeat.toString());
				}
			}
			result.append(s.charAt(i));
			
		}
		System.out.println("Result : " + result.toString());
	}

}
