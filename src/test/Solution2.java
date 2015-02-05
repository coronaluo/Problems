package test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution2 {
	static class MinComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    }
    
    static class MaxComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return -i1.compareTo(i2);
        }
    }
    public static final String EXCEPTION = "Wrong!";
        
    /* Head ends here*/
    static void median(String a[],int x[]) {
        if (a.length == 0 || x.length == 0) return ;
        int len = a.length;
        String []output = new String[len];
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(len/2+1, new MinComparator()); 
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(len/2+1, new MaxComparator()); 
        for (int i = 0; i < len; i++) {
            String o = a[i];
            int v = x[i];
            int minHeapLeft = minHeap.isEmpty() ? Integer.MIN_VALUE : minHeap.peek();
            int maxHeapRight = maxHeap.isEmpty() ? Integer.MAX_VALUE : maxHeap.peek();
            
            String rst = "";
            if ("a".equals(o)) {
            	if (v >= minHeapLeft) minHeap.add(v);
            	else maxHeap.add(v);
            } else if ("r".equals(o)) {
            	boolean success = minHeap.remove(v);
                if (!success) success = maxHeap.remove(v);
                if (!success) rst = EXCEPTION;
            } 
            adjust(minHeap, maxHeap);
            if (!EXCEPTION.equals(rst)) rst = getMedian(minHeap, maxHeap);
            output[i] = rst;
        }
        
        for (String str : output) {
        	System.out.println(str);
        }
    }
    
    private static String getMedian(PriorityQueue<Integer> minh, PriorityQueue<Integer> maxh) {
        if (minh.isEmpty() && maxh.isEmpty()) {
            return EXCEPTION;
        } else {// (!minh.isEmpty())
            if (minh.size() > maxh.size()) return Integer.toString(minh.peek());
            else {
            	long sum = (long) minh.peek()+ maxh.peek();
            	if (sum % 2 == 0) return Long.toString(sum/2);
            	else return (sum/2 + ".5");
            }
        }
    }
    
    // size of minHeap should be equal or at most one more than size of maxHeap
    private static void adjust (PriorityQueue<Integer> minh, PriorityQueue<Integer> maxh) {
    	int diff = minh.size() - maxh.size();
        if (diff <= 1 && diff >= 0) return;
        
    	if (diff < 0) {
            int removed = maxh.poll();
            minh.add(removed);
        } else if (diff > 0) {
            int removed = minh.poll();
            maxh.add(removed);
        }
    }
   
    
    /* Tail starts here*/
   public static void main( String args[] ){

      Scanner in = new Scanner(System.in);
      int N;
      N = in.nextInt();
      String s[] = new String[N];
      int x[] = new int[N];
      for(int i=0; i<N; i++){
         s[i] = in.next();
         x[i] = in.nextInt();
      }
      median(s,x);
    }
}
