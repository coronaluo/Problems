package leetcode.dp;

public class LongestIncreasingSubsequence {
	public static void main(String []args) {
		System.out.println("result="+getLISS2(new int[]{2,1,4,3,6,5,7}));
	}
	
	// n^2
	public static int getLISS(int a[]) {
		if (a == null || a.length == 0) return 0;
		
		int dp[] = new int[a.length];
		dp[0] = 1;
		for (int i = 1; i < a.length; i++) {
			int max = -1;
			for (int j = 0; j < i; j++) {
				if (a[j] <= a[i] && dp[j] > max) {
					max = dp[j];
				}
			}
			dp[i] = max+1;
		}
		
		return dp[a.length-1];
	}
	
	// nlogn
	public static int getLISS2(int a[]) {
		if (a == null || a.length == 0) return 0;
		
		int dp[] = new int[a.length+1];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		
		int curMaxLen = 0;
		for (int i = 0; i < a.length; i++) {
			int len = bs(dp, curMaxLen, a[i]);
			if (dp[len+1] > a[i]) {
				if (len+1 > curMaxLen) curMaxLen++;
				dp[len+1] = a[i];
			}
		}
		return curMaxLen;
	}
	
	// return the index of x, where 
	// x is the largest value that is smaller than or equal to v
	// all inclusive
	private static int bs(int a[], int len, int v) {
		int left = 1, right = len;
		while (left < right) {
			// System.out.println("JIAN: left="+left+"/right="+right);
			int mid = (left+right)/2;
			if (a[mid] == v) return mid;
			else if (a[mid] > v) right = mid-1;
			else left = mid + 1;
		}
		if (a[left] > v) return 0;
		return left;
	}
}
