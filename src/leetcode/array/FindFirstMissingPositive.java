package leetcode.array;

public class FindFirstMissingPositive {
	public static void main (String[] args) {
		int[] num = new int[]{0,1,1,1,1,3,3,4};
		String a = "Asd";
		System.out.println(new FindFirstMissingPositive().firstMissingPositive(num));
	}
	
	public int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) return 1;
        for (int i = 0; i < A.length; i++) {
            int t = A[i];
            while (t > 0 && t <= A.length && A[t-1] != t) {
                A[i] = A[t-1];
                A[t-1] = t;
                t = A[i];
            }
        }
 
        int i = 0;
        for (i = 1; i <= A.length; i++) {
            if (A[i-1] != i) break;
        }   
        return (i > A.length) ? A.length+1 : i;
    }
}
