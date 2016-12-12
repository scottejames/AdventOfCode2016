package com.scottejames.advent.dayten.balance;

public class Bot {
	int name;
	int low;
	int high;
	public static void giveLow(Bot l, Bot r)
	{
		r.placeChip(l.low);
		l.low = 0;
	}
	public static void giveHigh(Bot l, Bot r)
	{
		r.placeChip(l.high);
		l.high = 0;
	}
	Bot(int name) {
		this.name = name;
	}

	Bot(int name, int chip) {
		this.name = name;
		this.high = chip;
	}
	boolean complete(){
		if ((low != 0)  && (high != 0)) return true;
		else return false;
	}
	void placeChip(int chip) {
		
		if (( high == 0) && (low ==0)){
			high = chip;
		} else {
			if (low == 0) {
				if (chip < high) {
					low = chip;
				} else {
					low = high;
					high = chip;
				}
			} else {
				if (chip > low) {
					high = chip;
				} else {
					high = low;
					low = chip;
				}
			}
		}
	}

	void checkWinner() {
		if (low == 17 && high == 61) {
			System.out.println("found ==>" + this);
		}
//		if (low == 2 && high == 5) {
//		System.out.println("found WINNER!!!!!!!!!!! ==>" + this);
//	}		
	}

	public String toString() {
		return "Bot " + name + " Low: " + low + " High: " + high;
	}

}