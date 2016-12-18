package com.scottejames.advent.dayseventeen.twosteps;

import java.util.LinkedList;
import java.util.Queue;

public class TwoStepsForward {
	static class Room {
		public Room parent = null;
		public static boolean[][] visited = new boolean[4][4];
		public static final char[] dirs = { 'U', 'D', 'L', 'R' };
		boolean[] doors;
		int x;
		int y;
		String hash;
		static {
			// reset visted list
			for (int x=0;x<visited.length;x++)
				for (int y = 0; y<visited[x].length;y++)
					visited[x][y] = false;
		}
		public Room(int x, int y,String hash,Room parent){
//			System.out.println("Created room " + x + ", " + y);
			this.doors = calcDoors(hash);
			this.parent = parent;
			this.hash = hash;
			this.x = x;
			this.y = y;
			//if (Room.visited[x][y] == true) System.err.println("Looping " + this);
			//Room.visited[x][y] = true;

		}
		
		private boolean[] calcDoors(String hash) {
			boolean[] doors = new boolean[4];

			for (int i = 0; i < dirs.length; i++) {
				final char c = MD5(hash).charAt(i);

				if (c >= 'b' && c <= 'f') {
					doors[i] = true;
				} else {
					doors[i] = false;
				}
			}
			return doors;
		}
		public boolean isExit(){
			if ((x==3) && (y==3)){
				return true;
			}else
				return false;
		}
		public boolean visited(int x, int y){
			return visited[x][y];
		}
		public Room goUp() {
			Room result = null;
			if (y==0) return null;
			if ((doors[0]) && (!visited[x][y-1])){
				result = new Room(x,y-1,hash + 'U',this);
//				System.out.println("UP");

			} 
			return result;
				
		}
		public Room goDown() {

			Room result = null;
			if (y==3) return null;
			if ((doors[1])&& (!visited[x][y+1])){
				result = new Room(x,y+1,hash + 'D',this);
//				System.out.println("DOWN");

			} 
			return result;
				
		}
		public Room goLeft() {

			Room result = null;
			if (x==0) return null;
			if ((doors[2])&& (!visited[x-1][y])){
				result = new Room(x-1,y,hash + 'L',this);
//				System.out.println("LEFT");

			} 
			return result;
				
		}
		public Room goRight() {

			Room result = null;
			if (x==3) return null;
			if ((doors[3])&&(!visited[x+1][y])){
				result = new Room(x+1,y,hash + 'R',this);
//				System.out.println("RIGHT");

			} 
			return result;
				
		}
		
	}

	private static Queue<Room> path = new LinkedList<Room>();
	private static LinkedList<Room> successfulPaths = new LinkedList<Room>();

	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}


	public static void main(String[] args) {

		
		
		String hash = "rrrbmfta";
		Room start = new Room(0,0,hash,null);
		path.add(start);
		int maxDistance = 0;
		while (!path.isEmpty())
		{
//			System.out.println("Queue depth " + path.size());
			Room currentRoom = path.remove();
			if (currentRoom.isExit()) {
//				System.out.println("Exit Found " + currentRoom.hash);
				int distance = currentRoom.hash.length() - hash.length();
				if (distance > maxDistance) maxDistance = distance;
//				System.out.println("Distance " + distance);
			} else
			{
				Room up = currentRoom.goUp();
				Room down = currentRoom.goDown();
				Room left = currentRoom.goLeft();
				Room right = currentRoom.goRight();
				if (up!=null) path.add(up);
				if (down!=null) path.add(down);
				if (left!=null) path.add(left);
				if (right!=null) path.add(right);
			}
		}
		System.out.println("Longest distance "+ maxDistance);
		
	}

}
