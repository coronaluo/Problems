package leetcode.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CombinationSum {

	public static void main (String[] args) {
		System.out.println(new CombinationSum().combinationSum3(new int[]{13,23,25,11,7,26,14,11,27,27,26,12,8,20,22,34,27,17,5,26,31,11,16,27,13,20,29,18,7,14,13,15,25,25,21,27,16,22,33,8,15,25,16,18,10,25,9,24,7,32,15,26,30,19}, 25));
		System.out.println(new CombinationSum().combinationSum3(new int[]{10,1,2,7,6,1,5}, 8));
	}
	public List<List<Integer>> combinationSum3(int[] num, int target) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		List<List<Integer>> rtn = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < num.length; i++) {
			if (!hm.containsKey(num[i])) hm.put(num[i], 0);
			int counter = hm.get(num[i]);
			hm.put(num[i], counter+1);
		}
		
		dfs(hm, new ArrayList<Integer>(), rtn, target);
		
		return rtn;
	}
	
	public void dfs (HashMap<Integer, Integer> hm, ArrayList<Integer> cur, List<List<Integer>> rtn, int remaining) {
		if (remaining == 0) {
			// Collections.sort(cur);
	    	rtn.add(cur);
	        return;
	    }
	    
	    boolean added = false;
	    for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
	    	int value = entry.getKey(), counter = entry.getValue(); // value and counter
	        if (value > remaining) continue;
	        
	    	if (counter > 0) {
	    		if (!cur.isEmpty() && cur.get(cur.size()-1) > value) continue;
	    		
	        	HashMap<Integer, Integer> hmnew = (HashMap<Integer, Integer>) hm.clone();
	            hmnew.put(value, (Integer)counter-1);
	            ArrayList<Integer> curnew = new ArrayList((ArrayList<Integer>)cur.clone());
	            curnew.add(value);
	            
	            dfs(hmnew, curnew, rtn, remaining-value);
	            added = true;
	        }
	    }
	    
	    if (!added) return;
	}
	
	// S(k, sum) = U{(S(k-1, sum-A[k]) U A[k])} U S(k-1, sum)
	public List<List<Integer>> combinationSum2(int[] num, int target) {
		if (num == null || num.length == 0) return new ArrayList<List<Integer>>();
        
        List<Set<List<Integer>>> pre = new ArrayList<Set<List<Integer>>>(); // S(k-1, sum)
        List<Set<List<Integer>>> cur = new ArrayList<Set<List<Integer>>>(); // S(k,sum)
        qsort(num, 0, num.length-1);
        // init dp table
        for (int sum = 0; sum <= target; sum++) {
            pre.add(new HashSet<List<Integer>>()); 
            cur.add(new HashSet<List<Integer>>()); 
        }
        
        for (int k = 0; k < num.length; k++) {
            for (int sum = 0; sum <= target; sum++) {
                Set<List<Integer>> tobeAdded = new HashSet<List<Integer>>();
                
                // part I
                if (num[k] <= sum) {
                    Set<List<Integer>> preLists = pre.get(sum-num[k]);
                    if (preLists.isEmpty() && num[k] == sum) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(num[k]);
                        tobeAdded.add(list);
                        
                    } else if (!preLists.isEmpty()) {
                        for (List<Integer> list : preLists) {
                            if (!list.isEmpty() && list.get(list.size()-1) <= num[k]) {
                                List<Integer> tmp = deepCopy(list);
                                tmp.add(num[k]);
                                if (!tobeAdded.contains(tmp)) tobeAdded.add(tmp);
                            }
                        }
                    }
                } 
                    
                // part II
                Set<List<Integer>> preLists = pre.get(sum);
                for (List<Integer> list : preLists) {
                	if (!tobeAdded.contains(list))
                		tobeAdded.add(list);
                }
                
                // joint
                for (List<Integer> list : tobeAdded) {
                	if (!cur.get(sum).contains(list))
                		cur.get(sum).add(list);
                }
                
            }

            pre = cur;
            cur = new ArrayList<Set<List<Integer>>>(); 
            for (int sum = 0; sum <= target; sum++) {
            	cur.add(new HashSet<List<Integer>>()); 
            }
        }
        return new ArrayList<List<Integer>>(pre.get(target));
    }
    
    private List<Integer> deepCopy(List<Integer> list) {
        List<Integer> rtn = new ArrayList<Integer>();
        for (Integer i : list) {
            rtn.add(i);
        }
        return rtn;
    }
    
    // all inclusive
    private void qsort(int[] num, int s, int e) {
    	if (num == null || num.length == 0 || s >= e) return;
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
    
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // s(k) = U{s(k-A[i]) U A[i]} i=[0, len-1]
        List<List<List<Integer>>> dp = new ArrayList<List<List<Integer>>>();
        
        for (int i = 0; i <= target; i++) {
            dp.add(new ArrayList<List<Integer>>());
        }
        
        for (int sum = 0; sum <= target; sum++) {
            for (int j = 0; j < candidates.length; j++) {
                if (sum < candidates[j]) continue;
                
                List<List<Integer>> tmp = new ArrayList<List<Integer>>();
                List<List<Integer>> tobeAdded = new ArrayList<List<Integer>>();
                
                for (List<Integer> list : dp.get(sum-candidates[j])) {
                	tmp.add(new ArrayList<Integer>(list));
                }
                if (tmp.isEmpty()) {
                	if (sum == candidates[j]) {
                		List<Integer> list = new ArrayList<Integer>();
                        list.add(candidates[j]);
                        tmp.add(list);
                        tobeAdded.addAll(tmp);
                	}
                    
                } else {
                    for (List<Integer> list : tmp) {
                    	if (!list.isEmpty() && candidates[j] >= list.get(list.size()-1)) {
                    		list.add(candidates[j]);
                    		tobeAdded.add(list);
                    	}
                    }
                }
                
                dp.get(sum).addAll(tobeAdded);
            }
        }
        
        return dp.get(target);
    }
}
