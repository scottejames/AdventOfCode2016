package com.scottejames.advent.dayfifteen.discs;

import java.util.Arrays;
import java.util.List;

public class Timing {

	private static class Disc {
		int positions;
		int pos;

		public Disc(int positions, int pos) {
			this.pos = pos;
			this.positions = positions;
		}
	}

	private static final List<Disc> discs = Arrays.asList(new Disc(17, 15), new Disc(3, 2), new Disc(19, 4),
			new Disc(13, 2), new Disc(7, 2), new Disc(5, 0), new Disc(11, 0));


	public static void main(String[] args) {
		boolean winner = false;
		int time = 0;
		while (!winner) {
			// System.out.println("Time : " + time);
			int dropcount = 0;
			for (int j = 0; j < discs.size(); j++) {
				Disc d = discs.get(j);
				if ((d.pos + (time + j + 1)) % d.positions == 0) {
					dropcount++;
				}

			}
			if (dropcount == discs.size()) {
				winner = true;
			}
			time++;
		}
		System.out.println(time - 1);

	}// = 3045959
}
