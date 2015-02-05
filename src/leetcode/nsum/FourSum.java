package leetcode.nsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FourSum {
	public static void main(String []args) {
		FourSum test = new FourSum();
		int a[] = new int[]{0,2,1,-3}; // -4, -1, -1, 0, 1, 2,  
		int b[] = new int[]{13,5,-4,-9,1,-3,10,-7,7,3,1,-13,-11,7,-10,12,-15,13,5,-8,-11,-12,-15,-13,-3,-13,5,-4,6,1,-10,4,13,-7,3,-9,-3,-2,-1,12,9,-15,14,5,0,-10,-5,-8,-12,-15,-1,-8,11,-9,-14,-7,-6,7,-4,-15,-15,-7,-4,14,1,6,12,14,12,-11,11,-2,11,2,-12,-4,7,-2,-5,10,-9,10,9,-13,-14,11,-13,-13,3,-1,9,3,7,-9,-6,-14,4,-8,7,1,-12,-5,14,14,12,10,-12,-3,-7,-2,-8,-9,-7,9,-7,-13,5,-12,-11,-7,2,14,3,-14};
		int c[] = new int[]{1,0,-1,0,-2,2}; // -4, -1, -1, 0, 1, 2, 
		int d[] = new int[]{0,0,0,0};
	
		HashMap<String, List<Integer>> hm = new HashMap<String, List<Integer>>();
		hm.put("asd", new ArrayList<Integer>());
		for (Map.Entry<String, List<Integer>> entry : hm.entrySet()) {
			System.out.println(entry.getKey() +", " + entry.getValue());
		}
		
//		test.qsort(a, 0, a.length-1);
//		for (int j = 0; j < a.length; j++) {
//			System.out.print(a[j]+",");
//		}
      
		
//		System.out.println(test.fourSum(d, 0));
//		System.out.println(test.generateParenthesis(3));
	}
	
	public List<String> generateParenthesis(int n) {
        if (n < 0) throw new RuntimeException("n should be a positive integer");
        
        List<String> rtn = new ArrayList<String>();
        dfs(0,0,n,"", rtn);
        return rtn;
    }
   
	public void dfs(int left, int right, int n, String str, List<String> curRst) {

	  	if (right > left || left > n || right > n || n == 0) return;
	  
	    if (left == n && right == n) {
	        curRst.add(str);
	        return ;
	    } 

	    if (left < n) {
	        dfs(1+left, right, n, str+"(", curRst);
	    }

	    if (right < left) {
	        dfs(left, 1+right, n, str+")", curRst);
	    } 
	}
	
	public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        if (num == null || num.length < 4) return rtn;
        
        qsort(num, 0, num.length-1);
        
        HashMap<Integer, List<Pair>> hm = new HashMap<Integer, List<Pair>>();
        Set<List<Integer>> sols = new HashSet<List<Integer>>();
        for (int i = 0; i < num.length-1; i++) {
            
            for (int j = i+1; j < num.length; j++) {
                if (j > i+1 && num[j] == num[j-1]) continue;
                
                // generate solutions
                int sum = num[i]+num[j];
                if (hm.containsKey(sum)) {
                	for (Pair p : hm.get(sum)) {
                		if (!p.overlap(i, j)) {
                        	List<Integer> s = p.getJointSol(num, i,j);
                        	if (!sols.contains(s)) sols.add(s);
                        }
                	}
                }
                
                // building hash table
                if (!hm.containsKey(target - sum)) {
                	hm.put(target - sum, new ArrayList<Pair>());
                }
                hm.get(target - sum).add(new Pair(i,j));
            }
        }
        
        rtn.addAll(sols);
        return rtn;
    }
    
    // all inclusive
    public void qsort(int a[], int s, int e) {
        if (a == null || a.length == 0 || s >= e) return ;
        
        int l = s, r = e, p = a[(l+r)/2];
        
        while (l <= r) {
            while (a[l] < p) l++;
            while (a[r] > p) r--;
            
            if (l <= r) {
                int tmp = a[l];
                a[l] = a[r];
                a[r] = tmp;
                l++;
                r--;
            }
        }
        qsort(a, s, r);
        qsort(a, l, e);
    }
    
    private class Pair {
        int f, s;
        public Pair(int a, int b) {
            f = a;
            s = b;
        }
        
        public boolean overlap(int i, int j) {
            return (f == i || f == j || s == i || s == j);
        }
        
        public List<Integer> getJointSol(int[] a, int i, int j) {
            // f <= s, f <= i <= j
            List<Integer> rtn = new ArrayList<Integer>();
            rtn.add(a[f]);
            int max = Math.max(a[j], a[s]);
            int sec = (max == a[j]) ? Math.min(a[s], a[i]):a[i];
            int trd = (max == a[j]) ? Math.max(a[s], a[i]):a[j];
            rtn.add(sec);
            rtn.add(trd);
            rtn.add(max);
            return rtn;
        }
    }
}
