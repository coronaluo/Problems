package leetcode.array;

public class PermuteArray {
	public static int counter = 0;
	// given int array A, R, permute A to make output array O as O[R[i]]=A[i]
	// e.g A={1,2,3}, R={1,2,0}=> O{3,1,2}
	// O(1) space

	public static void main(String[] args) {
		int a[] = new int[]{2,5,4,6,7};
		//int r[] = new int[]{4,2,3,0,1};
		int r[] = new int[]{4,2,3,0,1};
//		int a[] = new int[]{1,2,3};
//		int r[] = new int[]{1,2,0};
		new PermuteArray().permute(a, r);
		for(int i : a) System.out.println(i);
		System.out.println("counter = " + counter);
		
	}

	public void permute(int[] a, int[] r) {
		for (int i = 0; i < a.length; i++) {
			while (r[i] != i) {
				swap(a, i, r[i]);
				swap(r, i, r[i]);
			}
		}
	}

	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
		counter++;
	}


}
