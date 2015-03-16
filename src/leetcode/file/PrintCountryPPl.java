package leetcode.file;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;


//San Fran	CA	123
//San Jose	CA	10
//Boston	DC	1000
//Washton	DC	12

public class PrintCountryPPl {
	public static void main(String[] args) {
		new PrintCountryPPl().printStatePpl();
	}
	// Warmup: Read input in format: city, state, population (tab delimited file.
	// population is of form: integer number always followed by a 'k'). Note that
	// city name can contain spaces. Output the total population in each state in
	// descending order
	class State implements Comparable<State>{
		String name;
		int ppl;
		public State(String n) {
			name = n;
			ppl = 0;
		}

		public void addPpl(int a) {
			ppl += a;
		}
		
		@Override
		public int compareTo(State s) {
			return (ppl - s.ppl);
		}
	}

	public void printStatePpl() {
		Scanner scanner = new Scanner(System.in);
		int numOfEntries = Integer.parseInt(scanner.nextLine());
		HashMap<String, State> hm = new HashMap<String, State>();
		while ((numOfEntries--) > 0) {
			String[] entry = scanner.nextLine().split("\t");
			if (entry.length != 3) {
				throw new RuntimeException("illegal input " +  entry);
			}
			String stateName = entry[1];
			int cityPpl = Integer.parseInt(entry[2]);
			if (!hm.containsKey(stateName)) hm.put(stateName, new State(stateName));
			hm.get(stateName).addPpl(cityPpl);
		}
		PriorityQueue<State> pq = new PriorityQueue<State>();
		for (Entry<String, State> entry : hm.entrySet()) {
			pq.offer((State)entry.getValue());
		}
		while (!pq.isEmpty()) {
			State s = pq.poll();
			System.out.println(s.name + ": " + s.ppl);
		}
	}
}
