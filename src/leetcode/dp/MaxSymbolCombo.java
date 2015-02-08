package leetcode.dp;

//given an array
// you are allowed to add +, *, (, ) between each adjacent elements in the array
// find the maximum results generated
// example
// [1,1,2,1] 
// output 6
// because (1+1) * (2+1)
public class MaxSymbolCombo {
	public static void main(String[] args) {
		System.out.println(new MaxSymbolCombo().findMaxRst(new int[]{-1,4,3,5}));
		
	}

	// assume no overflow/underflow
	// f(i, j) = max {max(f(i, k) + f(k+1, j), f(i, k)*f(k+1, j)) | k = [i,j]}
	// obj: f(0, len-1)
	public int findMaxRst(int[] array) {
		if (array == null || array.length == 0) return 0;
		int len = array.length;
		
		int[][] dp = new int[len][len];
		for (int i = 0; i < len; i++) {
			dp[i][i] = array[i];
		}
		
		for (int i = len-2; i >= 0; i--) {
			for (int j = i+1; j < len; j++) {
				int max = dp[i][i];
				for (int k = i; k < j; k++) {
					int l= dp[i][k], r = dp[k+1][j]; 
					int curmax = Math.max(l+r, l*r); 
					if (curmax > max) max = curmax;  
				}
				dp[i][j] = max;
			}
		}
		
		return dp[0][len-1];
	}
	
}
