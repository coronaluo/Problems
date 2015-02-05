package leetcode.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
	public static void main(String[] args) {
		System.out.println(new BinarySearch().searchInsert(new int[]{1}, 0));
		
	}
	
	public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        // if cant find
        // find the smaller number that is bigger than target
        
        int l = 0, r = A.length-1;
        
        while (l < r) {
            int mid = (l+r)/2;
            if (A[mid] >= target) r = mid;
            else l = mid + 1;
        }
        
        if (l == A.length-1 && A[l] < target) return (l+1);
        return l;
    }
}
