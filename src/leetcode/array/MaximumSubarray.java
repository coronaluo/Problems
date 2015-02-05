package leetcode.array;

public class MaximumSubarray {
	public static void main (String[] args) {
		int[] num = new int[]{-1};
		System.out.println(new MaximumSubarray().maxSubArray2(num));
	}
	
	public int maxSubArray2(int[] A) {
        // S(k) = Max(S(k-1)+A[k], A[k])
        // Max = Max(S(0), S(1), S(2)....S(n-1))
        if (A == null || A.length == 0) return 0;
        
        int max = Integer.MIN_VALUE;
        int lastsum=Integer.MIN_VALUE, sum = Integer.MIN_VALUE;
        
        for (int i = 0; i < A.length; i++) {
        	long t = (long)lastsum + (long)A[i];
        	if (t < Integer.MIN_VALUE) t = Integer.MIN_VALUE;
            sum = Math.max((int)t, A[i]);
            max = Math.max(max, sum);
            lastsum = sum;
        }
        
        return max;
    }
	
	public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) return 0;
        
        int max = Integer.MIN_VALUE, sum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            if (sum >= 0) {
                if (a >= 0) {
                    sum += a;
                    
                } else {
                    max = Math.max(sum, max);
                    if (sum + a >= 0) sum += a;
                    else sum = a;
                }
                
            } else {
                if (a < 0) sum = Math.max(sum, a);
                else sum = a;
            }
        }
        
        return Math.max(sum, max);
    }
}
