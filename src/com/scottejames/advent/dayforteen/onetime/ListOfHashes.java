package com.scottejames.advent.dayforteen.onetime;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListOfHashes {
	public static final String start = "ngcjuoqr";
	private String threePatternText = "([a-z0-9])(\\1)(\\1)";
	private String fivePatternText = "([a-z0-9])(\\1)(\\1)(\\1)(\\1)";
	private Pattern threePattern = null;
	private Pattern fivePattern = null;
	private Map<Integer, String> cache = new HashMap<Integer, String>();
	private Map<Integer, String> threeCache = new HashMap<Integer, String>();
	private Map<Integer, String> fiveCache = new HashMap<Integer, String>();

	public ListOfHashes() {
		threePattern = Pattern.compile(threePatternText);
		fivePattern = Pattern.compile(fivePatternText);

	}

	// Note going to have some duplicated work here if three is not a match...
	// ERK.. lets fix if required.
	public String matchForThree(int count) {
		if (count < 0)
			return null; // saves having to check this before calling
		String result = threeCache.get(count);
		if (result == null) {
			String data = getHashForIndex(count);
			Matcher threeMatcher = threePattern.matcher(data);
			if (threeMatcher.find()) {
				result = threeMatcher.group(0);
			}
		}
		threeCache.putIfAbsent(count, result);
		return result;
	}

	// Note going to have some duplicated work here if three is not a match...
	// ERK.. lets fix if required.
	public String matchForFive(int count) {
		if (count < 0)
			return null; // saves having to check this before calling
		String result = fiveCache.get(count);
		if (result == null) {
			String data = getHashForIndex(count);
			Matcher fiveMatcher = fivePattern.matcher(data);
			if (fiveMatcher.find()) {
				result = fiveMatcher.group(0);
			}
		}
		fiveCache.putIfAbsent(count, result);
		return result;
	}

	public String getHashForIndex(int count) {
		String result = cache.get(count);
		if (result == null)
			result = privateGetHashForIndex(count);
		cache.putIfAbsent(count, result);
		return result;
	}

	private String privateGetHashForIndex(int count) {
		String test = start + count;
		String result = null;
		try {
			java.security.MessageDigest md;
			md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(test.getBytes());
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			result = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}
}
