package leetcode.array;

import java.util.Collections;
import java.util.PriorityQueue;

// Given an array as input find the output array
// that has median of each sub array whose index starts from 0 to i(i = 1,2...array.length-1).
public class FindMedianFollowUps {
	
	public static void main(String[] args) {
		int[] array = new int[]{1,2,4,3,5,7,6,8};
//		System.out.println(new FindMedianFollowUps().findMedian(array, 0, array.length-1));
		double[] rst = new FindMedianFollowUps().findMedians(array);
		double[] rst2 = new FindMedianFollowUps().findMedians2(array);
		for (double i : rst) {
			System.out.println(i);
		}
		System.out.println("======");
		for (double i : rst2) {
			System.out.println(i);
		}
	}
	
	public double[] findMedians2(int[] array) {
		// maxheap...][...minHeap
		double[] rst = new double[array.length];
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(array.length, Collections.reverseOrder());
		minHeap.offer(array[0]);
		rst[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			if (minHeap.peek() < array[i]) minHeap.offer(array[i]);
			else maxHeap.offer(array[i]);
			
			int diff = minHeap.size() - maxHeap.size(); 
			if (Math.abs(diff) > 1) {
				if (diff > 0) maxHeap.offer(minHeap.poll());
				else minHeap.offer(maxHeap.poll());
			}
			
			if (minHeap.size() == maxHeap.size())  {
				rst[i] = (double)(minHeap.peek() + maxHeap.peek())/2; 
			} else {
				rst[i] = (minHeap.size() > maxHeap.size()) ? minHeap.peek() : maxHeap.peek();
			}
		}
		return rst;
	}
	
	// O(n^2)
	public double[] findMedians(int[] array) {
		double[] rst = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			rst[i] = findMedian(array, 0, i);
		}
		return rst;
	}
	
	public double findMedian(int[] array, int s, int e) {
		int len = e-s+1;
		if (len <= 0) throw new RuntimeException("empty array");

		if (len%2 == 1) {
			return (double)findKth(array, s, e, len/2);

		} else {
			double left = (int)findKth(array, s, e, len/2-1);
			double right = (int)findKth(array, s, e, len/2);
			return (left+right)/2;
		}
	}
	
	// k start from 0
	private int findKth(int[] array, int s, int e, int k) {
		if (s == e) return array[s];
		int len = e - s + 1;
	
		int pivotIdx = partition(array, s, e);
		if (pivotIdx - s > k) {
			return findKth(array, s, pivotIdx-1, k);
		} else {
			return findKth(array, pivotIdx, e, k - (pivotIdx - s));
		}
	}
	
	private int partition(int[] array, int s, int e) {
		if (e == s) return s;
		int left = s, right = e;
		int key = array[(left + right) / 2];
		while (left <= right) {
			while (array[left] < key) left++;
			while (array[right] > key) right--;

			if (left <= right) {
				int tmp = array[left];
				array[left] = array[right];
				array[right] = tmp;
				left++;
				right--;
			}
		}
		return left;
	}
}
