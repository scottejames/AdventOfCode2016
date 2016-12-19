package com.scottejames.advent.dayninteen.josephus;

import java.util.LinkedList;

public class JosephusVariantBFFAIL {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		int count = 3017957;
		for (int i = 1; i <= count; i++) {
			list.add(i);
		}
		int shooter = 0;
		int shot = 0;
		int c = 1000;
		while (list.size() > 1) {
			c--;
			if (c == 0) {
				c = 1000;
				System.out.println(list.size());
			}

			int size = list.size() / 2;
			shot = shooter + size;
			if (shot >= list.size())
				shot = shot - list.size();
			// System.out.println("Shooting " + shot);
			list.remove(shot);
			shooter++;
			if (shooter > list.size())
				shooter = 0;
		}
		System.out.println(list);
	}
}
