package com.scottejames.daytwo.bathroomsecurity;

import java.io.IOException;

import com.scottejames.advent.utils.Utils;

public class BathRoomSec {

	public static void main(String[] args) throws IOException {
		String[] keys = { "--1--", "-234-", "56789", "-ABC-", "--D--" };
		int x = 0;
		int y = 3;

		String[] data = Utils.readFile("daytwo/bathroomsecurity/DayTwoData.txt");
		for (String s : data) {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if ((c == 'U') && (y != 0) && keys[y - 1].charAt(x) != '-')
					y--;
				if ((c == 'D') && (y != 4) && keys[y + 1].charAt(x) != '-')
					y++;
				if ((c == 'L') && (x != 0) && keys[y].charAt(x - 1) != '-')
					x--;
				if ((c == 'R') && (x != 4) && keys[y].charAt(x + 1) != '-')
					x++;
			}
			System.out.println(" Key : " + keys[y].charAt(x));
		}
	}

}
