package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
	public static void main (String[] args) {
		new MergeIntervals().test();
	}
	
	public void test() {
		List<Interval> list = new ArrayList<Interval>();
		// [1,3],[2,6],[8,10],[15,18]
		list.add(new Interval(1,3));
		list.add(new Interval(4,6));
		list.add(new Interval(7,10));
		list.add(new Interval(11,18));
		for (Interval i : insert(list, new Interval(-1,7))) {
			System.out.println("("+i.start+","+i.end+")");
		}
		
	}
	
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null) return intervals;
        if (intervals == null) intervals = new ArrayList<Interval>();
        
        // insert
        int i = -1;
        for (i = 0; i < intervals.size(); i++) {
            Interval cur = intervals.get(i); 
            if (newInterval.start <= cur.start) break;
        }
        intervals.add(i, newInterval);
        
        // merge
        return merge(intervals);
    }
    
    private List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        List<Interval> rtn = new ArrayList<Interval>();
        Interval cur = null;
        for (Interval val : intervals) {
            if (cur == null) {
                cur = new Interval(val.start, val.end);
            } else if (cur.end < val.start) {
                rtn.add(new Interval(cur.start, cur.end));
                cur = new Interval(val.start, val.end);
            } else if (cur.end < val.end) {
                cur.end = val.end;
            }
        }
        rtn.add(new Interval(cur.start, cur.end));
        return rtn;
    }
    
    
//	public List<Interval> merge(List<Interval> intervals) {
//        List<Interval> rtn = new ArrayList<Interval>();
//        if (intervals == null || intervals.size() == 0) return rtn;
//        Collections.sort(intervals, new RangeComparator());
//        
//        Interval cur = null;
//        for (Interval i : intervals) {
//            if (cur == null) {
//                cur = new Interval(i.start, i.end);
//                
//            } else if (cur.end < i.start) {
//                rtn.add(new Interval(cur.start, cur.end));
//                cur.start = i.start;
//                cur.end = i.end;
//                
//            } else if (cur.end < i.end){
//                // overlap
//                cur.end = i.end;
//            }
//        }
//        
//        rtn.add(new Interval(cur.start, cur.end));  
//        
//        return rtn;
//    }
    
    private class RangeComparator implements Comparator<Interval> {
        // @override
        public int compare(Interval i1, Interval i2) {
        	if (i1.start > i1.end || i2.start > i2.end) throw new RuntimeException("illegal interval");
            return (i1.start == i2.start) ? (i1.end - i2.end) : (i1.start - i2.start);
        }
    }
    
     public class Interval {
         int start, end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }
}
