package com.scottejames.advent.dayforteen.onetime;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OneTiePad {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		List<Integer> keys = new ArrayList<>();
		int count = 0;
		ListOfHashes list = new ListOfHashes();
		while (keys.size() < 64) {
			String threeMatch = list.matchForThree(count);
			if (threeMatch != null)
				for (int i = 1; i < 1000; i++) {
					int fiveCount = i + count;
					String fiveMatch = list.matchForFive(fiveCount);
					if ((fiveMatch != null) && (threeMatch.charAt(0) == fiveMatch.charAt(0))) {
//						System.out.println("Found a key @ " + count);
//							System.out.println( "  Key (" + count + ") " + list.getHashForIndex(count));
//							System.out.println( "  Match (" + fiveCount + ") " + list.getHashForIndex(fiveCount));
						keys.add(count);
						break;

					}

				}

			count++;
		}
		System.out.println(keys);

	}

}
