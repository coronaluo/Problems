package leetcode.array;

import java.util.HashMap;

public class LongestSubarrySum {
	public static void main(String[] args) {
		int[] array = new int[]{1,2,3,4,2,55,-43};
		System.out.println(new LongestSubarrySum().findLongestSubarray(7, array));
	}
	// find longest subarry sum up to K
	public int findLongestSubarray(int K, int[] array) {
		if (array == null || array.length == 0) return -1;

		int sum = 0;
		int len = array.length;
		int[] partialSum = new int[len+1];
		partialSum[0] = 0;
		for (int i = 1; i <= array.length; i++) {
			partialSum[i] = partialSum[i-1] + array[i-1];
		}

		int maxLen = Integer.MIN_VALUE;
		// naive O(n^2)
//		for (int left = 0; left <= len; left++) {
//			for (int right = left; right <= len; right++) {
//				if (partialSum[right] - partialSum[left] == K) {
//					maxLen = Math.max(maxLen, right - left);
//				}
//			}
//		}
		
		// better O(n)
		// S(i) - S(j) = k -> S(j) + k = S(i)
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i <= len; i++) {
			if (!hm.containsKey(partialSum[i])) {
				hm.put(partialSum[i]+K, i);
			} else {
				int left = hm.get(partialSum[i]);
				maxLen = Math.max(maxLen,  i - left);
			}
		}
		
		// assume len of input is less than max_value
		if (maxLen == Integer.MIN_VALUE) {
			return -1;
		}
		return maxLen;
	}
}
