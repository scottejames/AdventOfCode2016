package com.scottejames.advent.daytwenty.firewall;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

import com.scottejames.advent.utils.Utils;

public class FireWall {

	public static void main(String[] args) throws IOException {
		String[] data = Utils.readFile("/advent/daytwenty/firewall/SimpleData.txt");
		LinkedList<Long> openFirewall = new LinkedList<Long>();
		// 5-8
		// 0-2
		// 4-7
		TreeMap<Long, Long> treeMap = new TreeMap<>();

		for (String line : data) {
			String[] fw = line.split("-");
			treeMap.put(Long.parseLong(fw[0]), Long.parseLong(fw[1]));
		}
		Iterator<Long> i = treeMap.navigableKeySet().iterator();
		int count = 0;
		long priorLhs = i.next();
		long priorRhs = treeMap.get(priorLhs);
		Long last = 0L;

		while (i.hasNext()) {
			long currentLhs = i.next();
			long currentRhs = treeMap.get(currentLhs);
			
			
			if (priorRhs + 1 > last) {
				last = priorRhs;
			} 
			
			if (currentLhs > last) {
				System.out.println("unblocked " + last + " : current " + currentLhs);
				count += currentLhs - last;
			}

			priorLhs = currentLhs;
			priorRhs = currentRhs;

		}
		System.out.println("Count " + count);

	}

}
