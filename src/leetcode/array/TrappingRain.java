package leetcode.array;

public class TrappingRain {
	public static void main(String[] args) {
		System.out.println(new TrappingRain().trap(new int[]{1,1,1,1,1}));
	}
	
	public int trap(int[] A) {
		if (A ==  null || A.length == 0) return 0;
        // O(n)
        int[] leftmaxs = new int[A.length];
        int[] rightmaxs = new int[A.length];
        
        int cur = A[0];
        for (int i = 0; i < A.length; i++) {
            cur = Math.max(cur, A[i]);
            leftmaxs[i] = cur;
        }
        
        cur = A[A.length-1];
        for (int i = A.length-1; i >= 0; i--) {
            cur = Math.max(cur, A[i]);
            rightmaxs[i] = cur;
        }
        
        int sum = 0;
        for (int i = 0; i < A.length-1; i++) {
            int water = (Math.min(leftmaxs[i], rightmaxs[i]) - A[i]);
            if (water > 0) sum += water;
        }
        return sum;
    }
}
