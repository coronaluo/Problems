package leetcode.array;

import java.util.HashMap;
import java.util.TreeMap;

public class FindDuplicatesFollowUps {
	public static void main(String[] args) {
		int[] array = new int[]{1,3,2,25,6,3,2,6,1};
		System.out.println(new FindDuplicatesFollowUps().ifDuplicatesExist2(array, 4));
		System.out.println(new FindDuplicatesFollowUps().ifDuplicatesExist3(array, 1, 0));
	}
	
	// 1) Given an unsorted list of integers,
	// return true if the list contains any duplicates and
	// false if it does not contain any duplicates. You do not have to identify the duplicate value.
	//
	// 2) Given an unsorted list of integers, return true if the list contains any duplicates within k indices of each element.
	// Do it faster than O(n^2).
	//
	// 3) Given an unsorted list of integers, return true if the list contains any fuzzy duplicates within k indices of each element.
	// A fuzzy duplicate is another integer within d of the original integer.
	// Example: If d=4, then 6 is a fuzzy duplicate of 3 but 8 is not. Do it faster than O(n^2).

	public boolean ifDuplicatesExist(int[] array) {
		// int xor = 0;
		// for (int i = 0; i < array.length; i++) xor ^= array[i];
		// return (xor != 0);

		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			if (!hm.containsKey(array[i])) hm.put(array[i], i);
			else return true;
		}
		return false;
	}
	
	public boolean ifDuplicatesExist2(int[] array, int k) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		 for (int i = 0; i < array.length; i++) {
		 	if (!hm.containsKey(array[i])) hm.put(array[i], i);
		 	else {
		 		int dis = Math.abs(hm.get(array[i])-i);
		 		if (dis <= k) return true;
		 		hm.put(array[i], i);
		 	}
		 }
		
		// sliding window
		// window size = k+1
//		int left = 0, wSize = k+1, right = left;
//		while (right < array.length) {
//			if (right - left + 1 > wSize) {
//				hm.remove(array[left++]);
//			}
//
//			if (!hm.containsKey(array[right])) hm.put(array[right], right);
//			else return true;
//
//			right++;
//		}

		return false;
	}
	
	public boolean ifDuplicatesExist3(int[] array, int k, int d) {
		if (array == null || array.length < 2) return false;
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();

		int left = 0, wSize = k+1, right = left;
		while (right < array.length) {
			if (right - left + 1 > wSize) tm.remove(array[left++]);

			int low = array[right] - d, high = array[right] + d;
			if ((tm.ceilingKey(array[right]) != null && tm.ceilingKey(array[right]) <= high) 
				|| (tm.floorKey(array[right]) != null && tm.floorKey(array[right]) >= low)) {
				return true;
			}
			if (!tm.containsKey(array[right])) tm.put(array[right], right);
			right++;
		}
		return false;
	}

}
