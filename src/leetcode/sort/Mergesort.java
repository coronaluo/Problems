package leetcode.sort;

import java.util.ArrayList;
import java.util.List;

public class Mergesort {

	public static void main(String []args) {
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(4);
		list.add(2);
		list.add(1);
		int[] array = new int[]{3,4,2,1};
//		List<Integer> sorted = new Mergesort().mergeSort(array, 0, array.length-1);
		new Mergesort().mergeSort(array, 0, array.length-1);
		for (int i : array) {
			System.out.println(i);
		}
	}
	
	private void mergeSort(int[] a, int s, int e) {
        if (a == null || a.length <= 1 || e <= s) return ;
        int mid = (s+e)/2;
        mergeSort(a, s, mid);
        mergeSort(a, mid+1, e);
        merge(a, s, mid, mid+1, e);
    }
    
    private void merge(int[] a, int s1, int e1, int s2, int e2) {
        if (a == null || a.length <= 1) return;
        
        int len1 = e1-s1+1;
        int[] tmp = new int[len1];
        for (int k = 0; k < len1; k++) tmp[k] = a[s1+k];
        
        int tp = 0, ap = s2, i = s1;
        while (ap <= e2 && tp < len1) {
            a[i++] = (tmp[tp] > a[ap]) ? a[ap++] : tmp[tp++];
        }
        while (ap < e2) a[i++] = a[ap++];
        while (tp < len1) a[i++] = tmp[tp++];
    }
	
//	// assume input *null* return an empty arraylist
//	  public static List<Integer> mergesort(List<Integer> a) {
//	    if (a == null || a.size() == 0) {
//	      return new ArrayList<Integer>();
//	    }
//	    
//	    if (a.size() == 1) return a;
//	    
//	    int m = a.size()/2;
//	    List<Integer> sa = mergesort(a.subList(0,m));
//	    List<Integer> sb = mergesort(a.subList(m,a.size()));
//		return merge(sa, sb);
//	  }
//	  
//	  private static List<Integer> merge(List<Integer> a, List<Integer> b) {
//	    if ((a == null && b == null) || (a.size() == 0 && b.size() == 0)) {
//	      return new ArrayList<Integer>();
//	    }
//	    if (a == null || a.size() == 0) return b;
//	    if (b == null || b.size() == 0) return a;
//	    
//		List<Integer> merged = new ArrayList<Integer>();
//	    
//	    int ahead = 0, bhead = 0;
//	    while (ahead < a.size() && bhead < b.size()) {
//	      merged.add(Math.min(a.get(ahead), b.get(bhead)));
//	      if (a.get(ahead) < b.get(bhead)) {
//	        ahead++;
//	      } else {
//	        bhead++;
//	      }
//	    }
//	    
//	    if (ahead != a.size()) {
//		  merged.addAll(a.subList(ahead, a.size()));
//	    }
//	    if (bhead != b.size()) {
//	      merged.addAll(b.subList(bhead, b.size()));
//	    }
//	    
//	    return merged;
//	  }
}
