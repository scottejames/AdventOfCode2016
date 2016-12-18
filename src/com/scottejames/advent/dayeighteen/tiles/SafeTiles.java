package com.scottejames.advent.dayeighteen.tiles;


public class SafeTiles {

//	Its left and center tiles are traps, but its right tile is not.
//	Its center and right tiles are traps, but its left tile is not.
//	Only its left tile is a trap.
//	Only its right tile is a trap.
	public static boolean itTileTrap(char l,char c, char r)
	{
		if((l=='^') && (c=='^') && (r=='.')) return true;
		if((c=='^') && (r=='^') && (l=='.')) return true;
		if((l=='^') && (r=='.') && (c== '.')) return true;
		if((r=='^') && (l=='.') && (c=='.')) return true;
		return false;
	}
	
	public static String nextRow(String row)
	{
		StringBuffer sb = new StringBuffer();
		String modifiedRow = '.' + row + '.';
		char[] rowChars = modifiedRow.toCharArray();
		for (int i = 0; i < modifiedRow.length()-2 ;i++){
			if (itTileTrap(rowChars[i], rowChars[i+1], rowChars[i+2])){
				sb.append("^");
			} else {
				sb.append(".");
			}
		}
		return sb.toString();
	}
	public static int countOccurrences(String haystack, char needle)
	{
	    int count = 0;
	    for (int i=0; i < haystack.length(); i++)
	    {
	        if (haystack.charAt(i) == needle)
	        {
	             count++;
	        }
	    }
	    return count;
	}
	public static void main(String [] args){
		int safeTiles = 0;
		String row = "...^^^^^..^...^...^^^^^^...^.^^^.^.^.^^.^^^.....^.^^^...^^^^^^.....^.^^...^^^^^...^.^^^.^^......^^^^";
		for (int i = 0; i < 400000; i++){
//			System.out.println(row);
			safeTiles += countOccurrences(row, '.');
			row = nextRow(row);
		}
		System.out.println("There are " + safeTiles + " safe tiles");
	}
}
