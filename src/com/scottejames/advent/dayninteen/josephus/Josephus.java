package com.scottejames.advent.dayninteen.josephus;

public class Josephus {

	public static void main (String [] args){
		int value = 3017957;
		int powerOfTwo = 0;
		for (int i = 0; powerOfTwo == 0 ; i++){
			if (Math.pow(2, i) > value){
				powerOfTwo = i-1;
			}
		}
		double l = value - Math.pow(2,powerOfTwo);
		double answer = (2*l) + 1;
		System.out.println("2l+1 = " + answer);
	}
}
