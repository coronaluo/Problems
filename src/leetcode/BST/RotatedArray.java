package leetcode.BST;

public class RotatedArray {
	public static void main(String []args) {
System.out.println(new RotatedArray().search(new int[]{3, 1}, 1));
    }
	
	public int search(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        int minIdx = findMin(A);
        System.out.println("minIdx="+minIdx);
        if (target <= A[A.length-1]) return bs(A, minIdx, A.length-1, target);
        else return bs(A, 0, (minIdx > 0) ? minIdx-1 : minIdx, target);
    }
    
    private int bs(int[] A, int l, int r, int target) {
        while (l <= r) {
            int mid = (l+r)/2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return -1;
    }
    
    private int findMin(int[] A) {
        int left = 0, right = A.length - 1;
    	
        while (left < right) {
            int mid = (left+right)/2;
            if (A[mid] > A[right]) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
