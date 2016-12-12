package com.scottejames.advent.daynine.explosive;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scottejames.advent.utils.Utils;

public class SimpleRegexp {
	static final Pattern MARKER = Pattern.compile("[(][0-9]+x[0-9]+[)]");

	static long countString(String s) {
		Matcher matcher = MARKER.matcher(s);
        int pointer = 0;
        long count = 0L;
        if (matcher.find()) {
        	
            int start = matcher.start();
            count += start;
            int len = Integer.parseInt(s.substring(s.indexOf("(") + 1, s.indexOf("x")));
            int num = Integer.parseInt(s.substring(s.indexOf("x") + 1, s.indexOf(")")));
            pointer = start + matcher.group().length();
            String dupeText = s.substring(pointer, pointer + len);
            //decompress 
            long dupeTextCount = countString(dupeText);
            count += (dupeTextCount *num);
//            count += (len * num);

            String remainder = s.substring(pointer + len);
            count += countString(remainder);
        }else {
            count += s.length();
        }


		return count;
	}

	public static void main(String[] args) throws IOException {
		String [] data = Utils.readFile("/advent/daynine/explosive/DayNineData.txt");
		long count = 0;
		for (String s: data)
		{
			count+= countString( s);
		}
		System.out.println(count);
	}

}
