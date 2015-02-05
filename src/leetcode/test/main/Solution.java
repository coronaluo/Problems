package leetcode.test.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class Solution {
	public static void main(String []args) {
		// String []a = {"asdas", ".,sdf", "asd2"};
//		// String []strs = new String[a.length];
//		String s = " 1   1 ";
//		String rtn = reverseWords(s);
//		System.out.println("rtn = " +rtn);
//		int a[] = {2,3,3,3,4,1};
//		System.out.println(candy(a));
//		System.out.println(12^2);
		
		UndirectedGraphNode origin = new UndirectedGraphNode(0);
		UndirectedGraphNode n1 = new UndirectedGraphNode(1);
		UndirectedGraphNode n2 = new UndirectedGraphNode(2);
		UndirectedGraphNode n3 = new UndirectedGraphNode(3);
		UndirectedGraphNode n4 = new UndirectedGraphNode(4);
		
		origin.neighbors.add(n1);
		origin.neighbors.add(n2);
		n1.neighbors.add(n3);
		n3.neighbors.add(n4);
		traverse(origin);
		traverse(cloneGraph(origin));
	}
	
	public static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	}
	
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	    if (node == null) {
	      return null;
	    
	    } else if (node.neighbors == null || node.neighbors.isEmpty()) {
	       return new UndirectedGraphNode(node.label);
	    
	    } else {
	      UndirectedGraphNode origin = new UndirectedGraphNode(node.label);
	      for (UndirectedGraphNode nn : node.neighbors) {
	        origin.neighbors.add(cloneGraph(nn));  
	      }
	      return origin;
	    }
	        
	  }
	
	public static void traverse(UndirectedGraphNode node) {
		if (node == null) return;
		HashSet<Integer> visited = new HashSet<Integer>();
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.add(node);

		System.out.println("----START----");
		
		while(!queue.isEmpty()) {
			UndirectedGraphNode curNode = queue.poll();
			System.out.println("node " + curNode.label);
			visited.add(curNode.label);
			
			for (UndirectedGraphNode nn : curNode.neighbors) {
				if (!visited.contains(nn.label))
					queue.add(nn);
			}
		}
		
		System.out.println("----OVER----");
	}
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		 if (gas == null || cost == null || gas.length != cost.length || gas.length == 0) return -1;
		    else if (gas.length == 1) return 0;

		    int sIdx = 0;
		    int sum = 0;
		    for (int i = 0; i < gas.length; i++) {
		      if (gas[i] < cost[i]) sIdx = i+1;
		      sum += (gas[i] - cost[i]);
		    }
		    
		    return (sum < 0) ? -1 : sIdx;
	  }
	  
	public static int candy(int[] ratings) {
      if (ratings == null || ratings.length == 0) return 0;
      
      int candies[] = new int[ratings.length];
      candies[0] = 1;
      
      for (int i = 1; i < ratings.length; i++) {
        candies[i] = (ratings[i] > ratings[i-1]) ? (candies[i-1]+1) : 1;
      }
      
      int sum=0;
      for (int i = ratings.length-2; i >= 0; i--) {
   		sum += candies[i+1];
        candies[i] = (ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) ? (candies[i+1]+1) : candies[i];
      }
      sum += candies[0];
      return sum;
    }
	
	public static int singleNumberII(int[] A) {
      if (A == null || A.length%3 != 1) return 0;
      
      int count[] = new int[32];
      int rtn = 0;
      
      for (int bi = 0; bi < 32; bi++) {
        for (int ni = 0; ni < A.length; ni++) {
          if (((A[ni] >> bi) & 1) == 1) {
            count[bi]++; 
          }
        }
        
        rtn |= (count[bi]%3 << bi);
      }
      
      return rtn;
    }
	
	public static int singleNumberXOR(int[] A) {
      if (A == null || A.length%2 == 0) return 0;
    
      int rtn = 0;
      for (int i = 0; i < A.length; i++) {
        rtn ^= A[i];
      }
      
      return rtn;
    }
	
	public static int singleNumber(int[] A) {
      if (A == null || A.length%2 == 0) return -1;
      if (A.length == 1) return A[0];
      
      HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
      for (int i = 0; i < A.length; i++) {
        int curValue = A[i];
        if (hm.containsKey(curValue)) hm.remove(curValue);
        else hm.put(curValue, curValue);
      }
      
      int rtn = -1;
      for (int key : hm.keySet()) {
        rtn = key;
      }
      
      return rtn; 
    }

	public static String reverseWords(String s) {
		if (s == null) return s;
        
        String []splited = s.split(" ");
        StringBuilder reversed = new StringBuilder();
        
        for (int i = (splited.length-1); i >= 0; i--) {
            if (!splited[i].matches("")) {
            	System.out.println("splited[i] = "+splited[i]);
            	if (reversed != null && reversed.length() != 0) {
            		reversed.append(" ");
            	}
                reversed.append(splited[i]);
            } else {
            	System.out.println(i);
            }
        }
        
        return reversed.toString();
    }
}
