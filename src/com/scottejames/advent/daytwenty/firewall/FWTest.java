package com.scottejames.advent.daytwenty.firewall;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import com.scottejames.advent.utils.Utils;

public class FWTest {

	public static void main(String[] args) throws IOException {
		String[] data = Utils.readFile("/advent/daytwenty/firewall/Data.txt");

		TreeMap<Long, Long> blocked = new TreeMap<>();

		for (String line : data) {
			String[] fw = line.split("-");
			blocked.put(Long.parseLong(fw[0]), Long.parseLong(fw[1]));
		}

		Iterator<Long> i = blocked.navigableKeySet().iterator();
		Long first = i.next();
		Long last = 0L;
		Long count = 0L;

		while (i.hasNext()) {
			Long current = i.next();
			if ((blocked.get(first) + 1) > last)
				last = blocked.get(first) + 1;
			if (current > last) {
				System.out.println("unblocked " + last + " : current " + current);
				count += current - last;
			}
			first = current;

		}

		System.out.println(count);

	}
}
