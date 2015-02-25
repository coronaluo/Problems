package leetcode.array;

import java.util.HashMap;

public class LongestConsecutiveSeq {
	public static void main(String[] args) {
		System.out.println(new LongestConsecutiveSeq().longestConsecutive(new int[]{-1,-2, -3, 0, 100, 200, -29}));
	}
	
	public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) return 0;
        
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < num.length; i++) {
            hm.put(num[i], i);
        }
        
        int max = 0;
        while (!hm.isEmpty()) {
            int key =  hm.entrySet().iterator().next().getKey();
            hm.remove(key);
            int low = key-1, high = key+1;
            while (hm.containsKey(low)) hm.remove(low--);
            while (hm.containsKey(high)) hm.remove(high++);
            max = Math.max(max, (high - low - 1));
        }
        return max;
    }
}
