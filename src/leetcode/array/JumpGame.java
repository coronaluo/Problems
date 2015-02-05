package leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.parentheses.GenerateParenthesis;

public class JumpGame {
	public static void main(String []args) {
		 System.out.println(new JumpGame().jump2(new int[]{1,0,1,1,1,1,4}));
	}
	
	public boolean canJump(int[] A) {
		if (A == null || A.length == 0) throw new RuntimeException();

        int farthest = A[0];
        for (int i = 1; i < A.length-1; i++) {
        	if (farthest < i) return false;
            if (farthest < (i+A[i])) farthest = (i+A[i]);
            if (farthest >= A.length-1) return true;
        }
        return (farthest >= A.length-1);
    }
	
	public int jump2(int[] A) {
		if (A == null || A.length <= 1) return 0;
        
		int curmaxstep = 0, farthestIdx = 0, nextfarthestIdx = 0;
        for (int i = 0; i < A.length; i++) {
            if (i == farthestIdx) {
            	// reach the end
                if (farthestIdx >= A.length-1) return curmaxstep;
                
                farthestIdx = Math.max(nextfarthestIdx, i+A[i]);
                nextfarthestIdx = farthestIdx;
                curmaxstep += 1;
                
                // no more updates -> cant reach the end
                if (farthestIdx == i) return -1; 
                
            } else {
                nextfarthestIdx = Math.max(nextfarthestIdx, i+A[i]);
            }
            System.out.println("idx="+i+"/curmaxstep="+curmaxstep+"/farthestIdx="+farthestIdx+"/nextfarthestIdx="+nextfarthestIdx);
        }
        return curmaxstep;
    }
	
}
