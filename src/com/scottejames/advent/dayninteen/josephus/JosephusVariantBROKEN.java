package com.scottejames.advent.dayninteen.josephus;

public class JosephusVariantBROKEN {
	// linked list node helper data type
	static class Elf {
		private Elf next;
		private Elf prev;
		private int name;

		public Elf(int name) {
			this.name = name;
			this.next = null;
			this.prev = null;
		}
	}

	static class ListOfElf {
		private Elf start = null;
		private int size = 0;

		public boolean isEmpty() {
			return (start == null);
		}

		public void add(Elf e) {
			size++;
			if (isEmpty()) {
				start = e;

			} else {
				e.next = start;
				start.prev = e;
				start = e;
			}
		}

		public void delete(Elf e) {
//			System.out.println("Deleteing "+ e.name);
			size--;
			// look for orner cases later
			if (e.prev != null)
				e.prev.next = e.next;
			else
				start = e.next;
			if (e.next != null)
				e.next.prev = e.prev;
			e.next = null;
			e.prev = null;
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();
			Elf loop = start;
			while (loop != null) {
				sb.append("[ " + loop.name + " ]");
				loop = loop.next;
			}
			return sb.toString();
		}
	}

	public static void main(String[] elf) {

		int size = 3017957;
		ListOfElf list = new ListOfElf();
		for (int i = size; i != 0; i--) {
			list.add(new Elf(i));
		}
		Elf shooter = list.start;
		Elf shot = list.start;
		int count = 10000;
		System.out.println(list.size);
		while (list.size > 1) {
//			System.out.println(list);
			count--;
			if (count ==0){
				count = 10000;
				System.out.println(size);
			}
			for (int i = 0; i < list.size / 2; i++) {
				shot = shot.next;
				if (shot == null)
					shot = list.start;
			}
			list.delete(shot);
			shooter = shooter.next;
			if (shooter == null)
				shooter = list.start;
			shot = shooter;
		}
		
		System.out.println(list);

	}

}
