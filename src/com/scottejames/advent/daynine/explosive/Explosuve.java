package com.scottejames.advent.daynine.explosive;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scottejames.advent.utils.Utils;

public class Explosuve {

	public static void main(String[] args) throws IOException {
		String [] data = Utils.readFile("/advent/daynine/explosive/Simple.txt");
		// (1x3)
		Pattern tokenPattern = Pattern.compile("^\\((\\d+)x(\\d)\\)(.*)$");
		for (String s: data){
			System.out.println("CONSIDERING : " + s);
			StringBuffer result = new StringBuffer();
			int counter = 0;
			while (s.length()!=0){
				
				Matcher matcher = tokenPattern.matcher(s);
				if (matcher.matches()){
					System.out.println("Found at " + s.substring(counter));
					int len = Integer.parseInt(matcher.group(1));
					int count = Integer.parseInt(matcher.group(2));
					System.out.println("len : " + len + " count : " + count);
					System.out.println(matcher.group(3));
					String repString=matcher.group(3).substring(0,len);
					for (int j = 0; j < count ;j ++){
						result.append(repString);
					}
					s = matcher.group(3).substring(len);
					
				} else { 
					result.append("" + s.charAt(0));
					s=s.substring(1);
				}
				counter++;
			}
			System.out.println("ANSWER: " + result.toString());

		}
	}

}
