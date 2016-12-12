package com.scottejames.advent.daytwelve.mono;

import java.io.IOException;

import com.scottejames.advent.utils.Utils;

public class MonoRail {
	public static boolean isInteger(String str) {
		try {
			int d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	public static int getValue(int [] registers, String s){
		int nCheck = 0;
		int result = 0;
		if (!isInteger(s)){
			result = registers[s.charAt(0) - 'a'];
		} else {
			result = Integer.parseInt(s);
		}
		return result;
	}
	public static void main(String[] args) throws IOException {

		int [] registers = new int[26];
		for (int i = 0; i < 4 ; i++) registers[i] = 0;
		registers['c'-'a'] = 1;
		String[] data = Utils.readFile("/advent/daytwelve/mono/DayTwelveData.txt");
		int programCounter = 0;
		int programTerminator = data.length;
		int count = 0;
		while ( programCounter < programTerminator)
		{
//			System.out.println("Program Counter " + programCounter + " Instruction " + data[programCounter]);
			String [] instruction = data[programCounter].split(" ");
			
			// Process instrution.
//			cpy 41 a

			if (instruction[0].equals("cpy")){
				char c = instruction[2].charAt(0);
				registers[c-'a'] = getValue(registers, instruction[1]);
//				inc a
//				inc a
//				dec a	
			} else if (instruction[0].equals("inc")) {
				char c = instruction[1].charAt(0);
				registers[c - 'a']++;
			} else if (instruction[0].equals("dec")) {
				char c = instruction[1].charAt(0);
				registers[c - 'a']--;	
				
//				jnz a 2
			} else if (instruction[0].equals("jnz")) {
				int check = getValue(registers, instruction[1]);
				if (check>0) {
					int jmp = Integer.parseInt(instruction[2]) -1;
					programCounter+=jmp;
				}
			} else {
				System.out.println("Invalid instuctions " + instruction[0]);
			}
				
			programCounter++;
			
//			for (int i = 0; i < 4 ; i++) {
//				char c = (char) (i + 'a');
//				System.out.print(c + " - " + registers[i] +" ");			
//			}
//			System.out.println("");
		}
		for (int i = 0; i < 4 ; i++) {
			char c = (char) (i + 'a');
			System.out.println(c + " - " + registers[i] );
		}

	}

}
