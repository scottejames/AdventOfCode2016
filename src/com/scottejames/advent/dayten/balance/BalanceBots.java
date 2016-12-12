//package com.scottejames.advent.dayten.balance;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.scottejames.advent.utils.Utils;
//
//public class BalanceBots {
//	public static void main(String[] args) throws IOException {
//		String[] data = Utils.readFile("/advent/dayten/balance/Simple.txt");
//
//		Map<Integer, Bot> botMap = new HashMap<>();
//		// value 5 goes to bot 2
//		// bot 2 gives low to bot 1 and high to bot 0
//		// value 3 goes to bot 1
//		// bot 1 gives low to output 1 and high to bot 0
//		// bot 0 gives low to output 2 and high to output 0
//		// value 2 goes to bot 2
//		for (String s : data) {
//			String[] split = s.split(" ");
//			// Simple assignment
//			if (split.length == 6) {
//				Integer key = Integer.parseInt(split[5]);
//				Integer chip = Integer.parseInt(split[1]);
//				if (botMap.containsKey(key)) {
//					botMap.get(key).placeChip(chip);
//				} else {
//					Bot bot = new Bot(key, chip);
//					botMap.put(key, bot);
//				}
//			} else // passing a chip
//			{
//				// bot 1 gives low to output 1 and high to bot 0
//				// bot 0 gives low to output 2 and high to output 0
//				int botNumber = Integer.parseInt(split[1]);
//				int lowBotNumber = Integer.parseInt(split[6]);
//				int highBotNumber = Integer.parseInt(split[11]);
//				Bot parent = botMap.getOrDefault(botNumber, new Bot(botNumber));
//
//				// Treat outputs as a special sort of bot
//				if (split[5].equals("output"))
//					lowBotNumber += 10000;
//				if (split[10].equals("output"))
//					highBotNumber += 10000;
//
//				Bot botLow = botMap.getOrDefault(lowBotNumber, new Bot(lowBotNumber));
//				Bot botHigh = botMap.getOrDefault(highBotNumber, new Bot(highBotNumber));
//
//				botMap.putIfAbsent(botNumber, parent);
//				botMap.putIfAbsent(lowBotNumber, botLow);
//				botMap.putIfAbsent(highBotNumber, botHigh);
//				parent.lowBot = botLow;
//				parent.highBot = botHigh;
//
//			}
//			 for (Integer key: botMap.keySet())
//			 {
//			 System.out.println(" <" +key + "> = " + botMap.get(key));
//			 }
//			
//			}
//		}
//
//	}
//
//}
