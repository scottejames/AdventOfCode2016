package com.scottejames.advent.dayten.balance;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.scottejames.advent.utils.Utils;

public class BalanceBotsLoop {
	public static Queue<String> instructions = new LinkedList<String>();
	public static Map<Integer, Bot> botMap = new HashMap<>();
	public static Map<Integer,Integer> outputMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		String[] data = Utils.readFile("/advent/dayten/balance/Data.txt");
		for (String s : data) {
			instructions.add(s);
		}
		int count = 0;
		while ( instructions.size() != 0) {
			count ++;
			String instruction = instructions.remove();
//			System.out.println("Processing .. " + instruction);
//			for (Integer key : botMap.keySet()) {
//				
//				System.out.println("  BEFORE <" + key + "> = " + botMap.get(key));
//			}
			String [] split = instruction.split(" ");
			if (split.length == 6) {
//				System.out.println("Simple insertion");
				Integer botNumber = Integer.parseInt(split[5]);
				Integer chip = Integer.parseInt(split[1]);

				if (botMap.containsKey(botNumber)) {
					botMap.get(botNumber).placeChip(chip);
				} else {
					Bot bot = new Bot(botNumber, chip);
					botMap.put(botNumber, bot);
				}
			} else
			{
				// bot 1 gives low to output 1 and high to bot 0
				// bot 0 gives low to output 2 and high to output 0
				int botNumber = Integer.parseInt(split[1]);
				int lowBotNumber = Integer.parseInt(split[6]);
				int highBotNumber = Integer.parseInt(split[11]);
				
				// Treat outputs as a special sort of bot
				if (split[5].equals("output"))
					lowBotNumber += 10000;
				if (split[10].equals("output"))
					highBotNumber += 10000;
				
				Bot bot = botMap.getOrDefault(botNumber, new Bot(botNumber));
				if (bot.complete()){
					bot.checkWinner();
//					System.out.println("Found complete bot " + bot);
					Bot lowBot = botMap.getOrDefault(lowBotNumber, new Bot(lowBotNumber));
					Bot highBot = botMap.getOrDefault(highBotNumber, new Bot(highBotNumber));
					
					Bot.giveLow(bot, lowBot);
					Bot.giveHigh(bot,  highBot);
//					System.out.println("LB " + lowBot);
//					System.out.println("HB " + highBot);

					botMap.putIfAbsent(lowBotNumber, lowBot);
					botMap.putIfAbsent(highBotNumber, highBot);
				} else{
//					System.out.println("--Unprocessed");
					instructions.add(instruction);
				}
			
			}
//			for (Integer key : botMap.keySet()) {
//				
//				System.out.println("  AFTER <" + key + "> = " + botMap.get(key));
//			}
			
			
		}
		for (Integer key : botMap.keySet()) {
			
			System.out.println(" <" + key + "> = " + botMap.get(key));
		}

	}
}
