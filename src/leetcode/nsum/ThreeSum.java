package leetcode.nsum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ThreeSum {
	public static void main(String []args) {
		ThreeSum test = new ThreeSum();
		int a[] = new int[]{1,2,-2,-1}; // -4, -1, -1, 0, 1, 2,  
		int b[] = new int[]{13,5,-4,-9,1,-3,10,-7,7,3,1,-13,-11,7,-10,12,-15,13,5,-8,-11,-12,-15,-13,-3,-13,5,-4,6,1,-10,4,13,-7,3,-9,-3,-2,-1,12,9,-15,14,5,0,-10,-5,-8,-12,-15,-1,-8,11,-9,-14,-7,-6,7,-4,-15,-15,-7,-4,14,1,6,12,14,12,-11,11,-2,11,2,-12,-4,7,-2,-5,10,-9,10,9,-13,-14,11,-13,-13,3,-1,9,3,7,-9,-6,-14,4,-8,7,1,-12,-5,14,14,12,10,-12,-3,-7,-2,-8,-9,-7,9,-7,-13,5,-12,-11,-7,2,14,3,-14};
		
		
		
//		test.qsort(a, 0, a.length-1);
//        for (int j = 0; j < a.length; j++) {
//			System.out.print(a[j]+",");
//		}
//        
        
        long t0 = Calendar.getInstance().getTimeInMillis();
		List<List<Integer>> rtn = test.threeSum(b);
		long t1 = Calendar.getInstance().getTimeInMillis();
		List<List<Integer>> rtn2 = test.threeSum2(b);
		long t2 = Calendar.getInstance().getTimeInMillis();
		
		System.out.println("t1 = " + (t1 - t0));
		System.out.println("t2 = " + (t2 - t1));
		
//		for (int i = 0; i < rtn.size(); i++) {
//			List<Integer> e = rtn.get(i);
//			System.out.println("{");
//			for (int j = 0; j < e.size(); j++) {
//				System.out.print(e.get(j)+",");
//			}
//			System.out.println("}");
//		}
	}
	
	// method 2
    public List<List<Integer>> threeSum2(int[] num) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) return rtn;
        
        qsort(num, 0, num.length-1);
                
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i] == num[i-1]) continue;
            
            int k = num.length-1;
            for (int j = i+1; j < k; j++) {
            	if (num[i]+num[j] > 0) break;
            	if (j > i+1 && num[j] == num[j-1]) continue;
            	
            	while (k > j+1 && (num[i] + num[j] + num[k] > 0)) {
                    k--;
                }
            	
                if (num[i] + num[j] + num[k] == 0) {
                    List<Integer> triplet = new ArrayList<Integer>();
                    triplet.add(num[i]);
                    triplet.add(num[j]);
                    triplet.add(num[k]);
                    rtn.add(triplet);
                }
            }
            
        }
        return rtn;
    }

    // {-1, 0, 1, 2, -1, -4}
 // {-1, 0, 1, -4, -1,| 2}
 // {-1, 0, 1, -4, -1}
    
    // {-1, 0, -4, -1, 2, 1}
    // all inclusive
    private void qsort(int[] a, int start, int end) {
        if (a == null || a.length == 0 || start >= end) return ;
        int left = start, right = end;
        int p = a[(left+right)/2];
        while (left <= right) {
            while(a[left] < p) left++;
            while(a[right] > p) right--;
            if (left <= right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++;
                right--;
            }
        }
        qsort(a, start, right);
        qsort(a, left, end);
    }   
    
	public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) return rtn;
        
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < num.length; i++) {
            if (!hm.containsKey(num[i])) {
                rtn.addAll(twoSum(num, 0-num[i], i));
            }
            hm.put(num[i], i);
        }
        
        return rtn;
    }
    
    private List<List<Integer>> twoSum(int[] num, int sum, int sumIdx) {
        List<List<Integer>> rtn = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) return rtn;
        
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> hmSol = new HashMap<Integer, Integer>();
        int e1 = 0-sum;
        for (int i = 0; i < num.length; i++) {
            if (i == sumIdx) continue;
            int e2 = num[i], e3 = sum-num[i];
            if (!hm.containsKey(e2)) {
                hm.put(e3, i);
                
            } else if (!hmSol.containsKey(e2) && Math.min(e2, e3) >= e1) {
                List<Integer> triplet = new ArrayList<Integer>();
                triplet.add(e1);
                triplet.add(Math.min(e2, e3));
                triplet.add(Math.max(e2, e3));
                rtn.add(triplet);
                hmSol.put(e2, i);
                hmSol.put(e3, i);
            }
        }
        return rtn;
    }
}
