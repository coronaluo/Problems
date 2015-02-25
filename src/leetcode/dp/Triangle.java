package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
	public static void main(String[] args) {
		Integer[][] t = new Integer[][]{
		             {2},
		             {3,4},
		            {6,5,7},
		           {4,1,8,3}
		};
		
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		for (int i = 0; i < t.length; i++) {
			List<Integer> list = new ArrayList<Integer>(Arrays.asList(t[i]));
			lists.add(list);
		}
		
		System.out.println(new Triangle().minimumTotal(lists));
	}
	
	public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty() || triangle.get(0).isEmpty()) return 0;
        int row = triangle.size();
        
        List<Integer> curRow = new ArrayList<Integer>();
        for (int i : triangle.get(row-1)) {
            curRow.add(i);
        }
        
        List<Integer> lastRow = new ArrayList<Integer>();
        for (int r = row-2; r >= 0; r--) {
            lastRow = curRow;
            curRow = new ArrayList<Integer>();
            for (int c = 0; c < triangle.get(r).size(); c++) {
                int min = Math.min(lastRow.get(c), lastRow.get(c+1));
                curRow.add(min+triangle.get(r).get(c));
            }
        }
        return curRow.get(0);
    }
	
//	public int minimumTotal(List<List<Integer>> triangle) {
//        if (triangle == null || triangle.isEmpty() || triangle.get(0).isEmpty()) return 0;
//        return helper(triangle, 0, 0);
//    }
//    
//    private int helper(List<List<Integer>> triangle, int sr, int sc) {
//        int row = triangle.size();
//        if (sr >= row) return 0;
//        int col = triangle.get(sr).size();
//        if (sc >= col || sc < 0) return Integer.MAX_VALUE;
//        
//        int left = helper(triangle, sr+1, sc);
//        int right = helper(triangle, sr+1, sc+1);
//        return (triangle.get(sr).get(sc)+Math.min(left, right));
//    }
}
