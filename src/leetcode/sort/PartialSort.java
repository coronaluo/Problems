package leetcode.sort;

import java.util.PriorityQueue;

public class PartialSort {
	// Sort an array where each item is at most k indices from its position in the sorted array. What's the run time?
	// Given a number k, and an array of integers A, find two integers in the array which sum to k.

	public static void main(String[] args) {
		int[] array = new int[]{3,2,6,2,5,8,9,9};
		// 22356899
		new PartialSort().partialSort(array, 2);
		for (int i : array) {
			System.out.print(i);
		}
	}
	
	public void partialSort(int[] array, int k) {
		// for (int i = 1; i < array.length; i++) {
		// 	int j = i-1;
		// 	int value = array[i];
		// 	while (j >= 0 && array[j] > value && (i-j) < k) {
		// 		array[j+1] = array[j];
		// 		j--;
		// 	}
		// 	array[j+1] = value;
		// }
		// 
		
		// Heap
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (i > k) {
				array[index++] = heap.poll();
			}
			heap.offer(array[i]);
		}
		while (!heap.isEmpty()) {
			array[index++] = heap.poll();
		}
	}
}
