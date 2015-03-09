package leetcode.sort;

public class QuickSelect {
	public static void main(String[] args) {
		 System.out.println(new QuickSelect().quickSelect(new int[]{4,2,5,7,6,3,3,1}, 6));
//		System.out.println(new QuickSelect().quickSelect(new int[]{1,2,4,3}, 3));
		 System.out.println(Long.MAX_VALUE);
		 System.out.print(Integer.MAX_VALUE+""+Integer.MAX_VALUE);
	}
	
	// return kth smallest element
	public int quickSelect(int[] a, int k) {
		if (a == null || a.length == 0 || k < 1 || k > a.length) throw new RuntimeException("illegal input"); 
		return quickSelect(a, 0, a.length-1, k);
	}
	
	private int quickSelect(int[] a, int s, int e, int k) {
		if (s == e) return a[s];
		
		int pIdx = partition(a, s, e);
		if (pIdx - s > k) return quickSelect(a, s, pIdx-1, k);
		else return quickSelect(a, pIdx, e, k-(pIdx-s));
	}
	
	private int partition(int[] a, int left, int right) {
//		System.out.println()
		int value = a[(left+right)/2];
		while (left <= right) {
			while (a[left] < value) left++;
			while (a[right] > value) right--;
			if (left <= right) {
				int tmp = a[left];
				a[left] = a[right];
				a[right] = tmp;
				left++;
				right--;
			}
		}
	
		return right+1;
	}
}
