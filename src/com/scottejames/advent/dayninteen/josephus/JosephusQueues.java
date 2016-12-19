package com.scottejames.advent.dayninteen.josephus;

import java.util.LinkedList;

public class JosephusQueues {
	public static void main (String [] args){
		LinkedList<Integer> left = new LinkedList<>();
		LinkedList<Integer> right = new LinkedList<>();
		
        int size = 5;
        for(int i = 1; i<=size; i++) {
            if(i<=size/2) left.addLast(i);
            else right.addLast(i);
        }
        System.out.println(left);
        System.out.println(right);
        while(left.size() + right.size() != 1) {
            int x = left.removeFirst();
            int removed = 0;
            if(left.size() == right.size()) {
                removed = left.removeLast();
            }else {
                removed = right.removeFirst();
            }
            System.out.println("Removed : " + removed);
            right.add(x);
            int a = right.pollFirst();
            left.addLast(a);
            System.out.println(left);
            System.out.println(right);
        }
        System.out.println(left.pollFirst());


	}
}
