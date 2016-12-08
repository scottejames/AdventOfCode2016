package com.scottejames.dayone.notimetaxi;

import java.util.HashSet;
import java.util.Set;

public class NoTimeTaxi {

	public static void main(String[] args) {
		 String route = "R4, R3, L3, L2, L1, R1, L1, R2, R3, L5, L5, R4, L4, R2, R4, L3, R3, L3, R3, R4, R2, L1, R2, L3, L2, L1, R3, R5, L1, L4, R2, L4, R3, R1, R2, L5, R2, L189, R5, L5, R52, R3, L1, R4, R5, R1, R4, L1, L3, R2, L2, L3, R4, R3, L2, L5, R4, R5, L2, R2, L1, L3, R3, L4, R4, R5, L1, L1, R3, L5, L2, R76, R2, R2, L1, L3, R189, L3, L4, L1, L3, R5, R4, L1, R1, L1, L1, R2, L4, R2, L5, L5, L5, R2, L4, L5, R4, R4, R5, L5, R3, L1, L3, L1, L1, L3, L4, R5, L3, R5, R3, R3, L5, L5, R3, R4, L3, R3, R1, R3, R2, R2, L1, R1, L3, L3, L3, L1, R2, L1, R4, R4, L1, L1, R3, R3, R4, R1, L5, L2, R2, R3, R2, L3, R4, L5, R1, R4, R5, R4, L4, R1, L3, R1, R3, L2, L3, R1, L2, R3, L3, L1, L3, R4, L4, L5, R3, R5, R4, R1, L2, R3, R5, L5, L4, L1, L1";
//		String route = "R8, R4, R4, R8";
		String[] directions = route.split(",");
		int heading = 0;
		int distX = 0;
		int distY = 0;
		Set<String> locations = new HashSet<String>();
		for (int i = 0; i < directions.length; i++) {
			directions[i].trim();
			if (directions[i].charAt(0) == ' ')
				directions[i] = directions[i].substring(1);
			char turn = directions[i].charAt(0);
			int distance = Integer.parseInt(directions[i].substring(1));
			if (turn == 'L')
				heading--;
			if (turn == 'R')
				heading++;
			if (heading > 3)
				heading = 0;
			if (heading < 0)
				heading = 3;
			for (int j = 0; j < distance; j++) {
				if (heading == 0)
					distX ++;
				if (heading == 2)
					distX --;
				if (heading == 1)
					distY ++;
				if (heading == 3)
					distY --;
				if (locations.contains("" + distX + " - " + distY)) {
					System.out.println("returning to " + distX + ", " + distY);
					int HQdistance = Math.abs(distX) + Math.abs(distY);
					System.out.println("Distance = " + HQdistance);

				}
				locations.add("" + distX + " - " + distY);
			}
		}
		int distance = Math.abs(distX) + Math.abs(distY);
		// System.out.println("Distance = " + distance);
	}

}
