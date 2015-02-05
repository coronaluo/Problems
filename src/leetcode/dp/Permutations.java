package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
	public static void main (String[] args) {
		//System.out.println(new CombinationSum().combinationSum2(new int[]{13,23,25,11,7,26,14,11,27,27,26,12,8,20,22,34,27,17,5,26,31,11,16,27,13,20,29,18,7,14,13,15,25,25,21,27,16,22,33,8,15,25,16,18,10,25,9,24,7,32,15,26,30,19}, 25));
		System.out.println(new Permutations().permuteUnique(new int[]{}));		
	}
	
	public List<List<Integer>> permuteUnique(int[] num) {
        if (num == null || num.length == 0) return new ArrayList<List<Integer>>();
        
        qsort(num, 0, num.length-1);
        
        return permuteUniqueWrapper(num);
        
    }
    
    private List<List<Integer>> permuteUniqueWrapper(int[] num) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        int len = num.length;
        
        if (len == 0) return rtn;
        if (len == 1) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(num[0]);
            rtn.add(list);
            return rtn;
        }
        
        for (int i = 0; i < len; i++) {
            if (i > 0 && num[i] == num[i-1]) continue;
            List<List<Integer>> lists = permuteUniqueWrapper(subArray(num, i));
            List<List<Integer>> curlists = new ArrayList<List<Integer>>(lists);
            for (List<Integer> list : curlists) {
                list.add(0, num[i]);
            }
            
            rtn.addAll(curlists);
        }
        
        return rtn;
    }
    
    // remove element with idx = ri
    private int[] subArray(int[] a, int ri) {
    	if (a == null || a.length == 0 || ri >= a.length) return a;
    	int[] rtn = new int[a.length-1];
    	int j=0;
    	for (int i = 0; i < a.length; i++) {
    		if (i != ri) rtn[j++] = a[i];
    	}
    	return rtn;
    }
    
    private void qsort(int[] num, int s, int e) {
    	if (num == null || num.length == 0 || s >= e) return ;
        int l = s, r = e, p = num[(l+r)/2];
        while (l <= r) {
            while (num[l] < p) l++;
            while (num[r] > p) r--;
            
            if (l <= r) {
                int t = num[l];
                num[l] = num[r];
                num[r] = t;
                l++;
                r--;
            }
        }
        qsort(num, s, r);
        qsort(num, l, e);
    }
    
	// recursion
    public List<List<Integer>> permute(int[] num) {
        if (num == null || num.length == 0) return new ArrayList<List<Integer>>();
        List<Integer> numlist = new ArrayList<Integer>();
        for (int i = 0; i < num.length; i++) {
        	numlist.add(num[i]);
        }
        return permuteWrapper(numlist);
    }
    
    // all inclusive
    private List<List<Integer>> permuteWrapper (List<Integer> num) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        
        if (num == null || num.size() == 0) return rtn;
        
        if (num.size() == 1) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(num.get(0));
            rtn.add(list);
            return rtn;
        }
        
        for (int i = 0; i < num.size(); i++) {
            List<Integer> sublist = new ArrayList<Integer>();
            
            if (i == 0) {
                sublist = new ArrayList<Integer>(num.subList(i+1, num.size()));
            } else if (i == num.size()-1) {
                sublist = new ArrayList<Integer>(num.subList(0, num.size()-1));
            } else {
                sublist = new ArrayList<Integer>(num.subList(0,i));
                sublist.addAll(new ArrayList<Integer>(num.subList(i+1, num.size())));
            }
            
            List<List<Integer>> lists = permuteWrapper(sublist);
            for (List<Integer> list : lists) {
                list.add(0, num.get(i));
            }
            
            rtn.addAll(lists);
        }
        
        return rtn;
    }
    
//	public List<List<Integer>> permute(int[] num) {
//        if (num == null || num.length == 0) return new ArrayList<List<Integer>>();
//        
//        List<List<Integer>> pre = new ArrayList<List<Integer>>();
//        List<List<Integer>> cur = new ArrayList<List<Integer>>();
//        
//        // init
//        for (int i = 0; i < num.length; i++) {
//            List<Integer> list = new ArrayList<Integer>();
//            list.add(num[i]);
//            pre.add(list);
//        }
//        
//        // dp
//        for (int dp = 1; dp < num.length; dp++) {
//            for (List<Integer> list : pre) {
//                for (int i = 0; i < num.length; i++) {
//                    if (!list.isEmpty() && list.contains(num[i])) continue;
//                    List<Integer> newlist = deepCopy(list);
//                    newlist.add(num[i]);
//                    cur.add(newlist);
//                }
//            }
//            pre = cur;
//            cur = new ArrayList<List<Integer>>();
//        }
//        
//        return pre;
//    }
//    
//    private List<Integer> deepCopy(List<Integer> list) {
//        List<Integer> rtn = new ArrayList<Integer>();
//        for (Integer i : list) {
//            rtn.add(i);
//        }
//        return rtn;
//    }
}
