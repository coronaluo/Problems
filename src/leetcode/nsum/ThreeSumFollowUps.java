package leetcode.nsum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSumFollowUps {
	// Which numbers in an unordered list can be represented as a sum of 3 other numbers in the list.

	public static void main(String []args) {
		ThreeSum test = new ThreeSum();
		int a[] = new int[]{1,2,-2,-1}; // -4, -1, -1, 0, 1, 2,
		// int a[] = new int[]{1,2,3,4,5,6};
		int b[] = new int[]{13,5,-4,-9,1,-3,10,-7,7,3,1,-13,-11,7,-10,12,-15,13,5,-8,-11,-12,-15,-13,-3,-13,5,-4,6,1,-10,4,13,-7,3,-9,-3,-2,-1,12,9,-15,14,5,0,-10,-5,-8,-12,-15,-1,-8,11,-9,-14,-7,-6,7,-4,-15,-15,-7,-4,14,1,6,12,14,12,-11,11,-2,11,2,-12,-4,7,-2,-5,10,-9,10,9,-13,-14,11,-13,-13,3,-1,9,3,7,-9,-6,-14,4,-8,7,1,-12,-5,14,14,12,10,-12,-3,-7,-2,-8,-9,-7,9,-7,-13,5,-12,-11,-7,2,14,3,-14};
		
		for (int i : new ThreeSumFollowUps().find3SumNumber(a)) {
			System.out.println(i);
		}
	}
	
	class Pair {
		int id1, id2;
		public Pair(int i, int j) {
			id1 = i;
			id2 = j;
		}

		public boolean contains(int id) {
			return (id1 == id || id2 == id);
		}
	}

	public Set<Integer> find3SumNumber(int[] a) {
		if (a == null || a.length == 0) throw new RuntimeException("illegal input");
		Set<Integer> rst = new HashSet<Integer>();
		HashMap<Integer, List<Pair>> hm = new HashMap<Integer, List<Pair>>();
		// a + b = c - X
		
		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				int sum = a[i] + a[j];
				if (!hm.containsKey(sum)) hm.put(sum, new ArrayList<Pair>());
				hm.get(sum).add(new Pair(i, j));
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				int subtract = a[i] - a[j];
				if (hm.containsKey(subtract)) {
					for (Pair pair : hm.get(subtract)) {
						if (!pair.contains(j)) {
							rst.add(a[i]);
							break;
						}
					}
				}
			}
		}

		return rst;
	}

	
}
