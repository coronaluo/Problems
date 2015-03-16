package leetcode.array;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FindPeak {
	
	public static void main(String[] args) {
		int[] a = new int[]{1,3,4,6,8,7,5,4,3,1};
		FindPeak fp = new FindPeak();
		System.out.println(fp.findMax(a));
	}
	// Given an array of integers which is initially increasing and then decreasing
	// , find the maximum value in the array.

	public int findMax(int[] a) {
		if (a == null || a.length == 0) return -1;
		return helper(a, 0, a.length-1);
	}

	private int helper(int[] a, int l, int r){
		int s = l,  e = r;
		if (e <= s) return a[s];
		if ((e-s+1) == 2) return Math.max(a[s], a[e]);
		
		int mid = (s+e)/2;
		int pre = (mid - 1 < 0) ? Integer.MIN_VALUE : a[mid-1];
		int next = (mid + 1 > a.length) ? Integer.MIN_VALUE : a[mid+1];
		if (a[mid] > pre && a[mid] > next) return a[mid];
		else if (a[mid] > pre) return helper(a, mid, e);
		else if (a[mid] > next) return helper(a, s, mid);
		else throw new RuntimeException("illegal input");
	}
}
