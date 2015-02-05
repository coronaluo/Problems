package leetcode.nsum;

import java.util.Calendar;
import java.util.List;

public class ThreeSumCloest {
	public static void main(String []args) {
		ThreeSumCloest test = new ThreeSumCloest();
		int a[] = new int[]{0,2,1,-3}; // -4, -1, -1, 0, 1, 2,  
		int b[] = new int[]{13,5,-4,-9,1,-3,10,-7,7,3,1,-13,-11,7,-10,12,-15,13,5,-8,-11,-12,-15,-13,-3,-13,5,-4,6,1,-10,4,13,-7,3,-9,-3,-2,-1,12,9,-15,14,5,0,-10,-5,-8,-12,-15,-1,-8,11,-9,-14,-7,-6,7,-4,-15,-15,-7,-4,14,1,6,12,14,12,-11,11,-2,11,2,-12,-4,7,-2,-5,10,-9,10,9,-13,-14,11,-13,-13,3,-1,9,3,7,-9,-6,-14,4,-8,7,1,-12,-5,14,14,12,10,-12,-3,-7,-2,-8,-9,-7,9,-7,-13,5,-12,-11,-7,2,14,3,-14};
		int c[] = new int[]{-3,0,1,2}; // -4, -1, -1, 0, 1, 2, 
		
//		test.qsort(c, 0, c.length-1);
//		for (int j = 0; j < c.length; j++) {
//			System.out.print(c[j]+",");
//		}
      
		
		System.out.println(test.threeSumClosest(a, 1));
		
	}
	
	public int threeSumClosest(int[] num, int target) {
        if (num == null || num.length < 3) throw new RuntimeException("array must contain more than 3 elements");
       
        qsort(num, 0, num.length-1);
        
        int dp[] = new int[num.length];
        dp[2] = num[0]+num[1]+num[2];
        for (int i=3; i < num.length; i++) {
            if (dp[i-1] == target) return target;
        	int target2 = target - num[i];
        	int close2 = twoSumClosest(num, 0, i-1, target2);
            if (Math.abs(close2-target2) <= Math.abs(dp[i-1]-target)) {
                dp[i] = close2 + num[i];
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[dp.length-1];
    }
	
	public int twoSumClosest(int[]sorted, int start, int end, int target) {
		int left = start, right=end, minDis = Integer.MAX_VALUE, closest = -1;
		
		while (left < right) {
			int sum = sorted[left]+sorted[right];
			if (Math.abs(sum-target) < minDis) {
				minDis = Math.abs(sum-target);
				closest = sum;
			}
			if (sum == target) return target;
			else if (sum < target) left++;
			else right--;
		}
		return closest;
	}
    
    // all inclusive
    private void qsort(int[] a, int start, int end) {
        if (a == null || a.length == 0 || start >= end) return;
        int left = start, right = end;
        
        int p = a[(left+right)/2];
        while (left <= right) {
            while (a[left] < p) left++;
            while (a[right] > p) right--;
            if (left <= right) {
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
                left++;
                right--;
            }
        }
        
        qsort(a, start, right);
        qsort(a, left, end);
    }
}
