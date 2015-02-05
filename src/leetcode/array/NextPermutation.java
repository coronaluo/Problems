package leetcode.array;

import leetcode.dp.CombinationSum;

public class NextPermutation {
	public static void main (String[] args) {
		int[] num = new int[]{2,3,1};
		new NextPermutation().nextPermutation(num);
		for (int i : num) {
			System.out.println(i);
		}
		
	}
	
	public void nextPermutation(int[] num) {
        if (num == null || num.length < 2) return ;
        int i = num.length-2;
        while (i >= 0 && num[i] >= num[i+1]) {
            i--;
        }

        if (i >= 0) {
            int v = num[i], min = i+1; 
            for (int j = i+1; j < num.length;j++) {
                if (num[j] <= num[min] && num[j] > v) min = j;
            }
            
            // swap min and i
            num[i] = num[min];
            num[min] = v;
        } 
        
        // reverse a[i+1, len-1]
        reverse(num, i+1, num.length-1);
    }
    
    // all inclusive
    private void reverse(int[] num, int s, int e) {
        int len = e-s+1;
        if (num == null || s >= num.length || len < 2) return ;
        
        for (int i = 0; i < len/2; i++) {
            int t = num[s+i];
            num[s+i] = num[e-i];
            num[e-i] = t;
        }
    }
}
