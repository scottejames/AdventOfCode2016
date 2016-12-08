package com.scottejames.dayfour.encryptedrooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scottejames.advent.utils.Utils;

public class EncryptedRooms {

	public static void main(String[] args) throws IOException {

		String[] dataList = Utils.readFile("/dayfour/encryptedrooms/DayFourData.txt");
		int sectorcount = 0;

		for (String data : dataList) {
			String patternText = "([a-z\\-]+)(\\d+)\\[(.*?)\\]";
			Pattern pattern = Pattern.compile(patternText);
			Matcher matcher = pattern.matcher(data);
			matcher.matches();
			String name = matcher.group(1);
			int sectorId = Integer.parseInt(matcher.group(2));
			String hash = matcher.group(3);

//			System.out.println(" Name " + name + " Sector " + sectorId + " hash " + hash);
			System.out.println(incrString(name,sectorId) + " - " + sectorId);
			String checksum = getHash(name);
			if (checksum.equals(hash)) {
				sectorcount += sectorId;

//				System.out.println("Valid " + sectorcount);
			} //else
//				System.out.println("Not Valid " + sectorcount);
		}
		System.out.println(sectorcount);

	}

	private static String getHash(String name) {
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) != '-') {
				if (null == countMap.get(name.charAt(i))) {
					countMap.put(name.charAt(i), 1);
				} else {
					countMap.put(name.charAt(i), countMap.get(name.charAt(i)) + 1);
				}
			}
		}
		List<String> list = new ArrayList<String>();

		for (Character c : countMap.keySet()) {
			list.add(countMap.get(c) + " " + c);

		}
//		System.out.println(list);
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int count1 = Integer.parseInt(o1.split(" ")[0]);
				int count2 = Integer.parseInt(o2.split(" ")[0]);
				char char1 = o1.split(" ")[1].charAt(0);
				char char2 = o2.split(" ")[1].charAt(0);
				if (count1 != count2) {
					return count1 < count2 ? 1 : -1;
				} else
					return char1 >char2 ? 1 : -1;

			};
		});
//		System.out.println(list);

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 5; i++) {
			String[] split = list.get(i).split(" ");
			sb.append(split[1]);
		}
		String checksum = sb.toString();
		return checksum;
	}
	public static String incrString(String s, int c){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++)
				sb.append(incrChar(s.charAt(i),c));
		return sb.toString();
	}
	
	// Hack on toast, mod maths much better
	
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
