package leetcode.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PalindromePartition {
	public static void main(String[] args) {
//		System.out.println(new PalindromePartition().minCut("ab"));
		String a = "";
		String b = "";
		System.out.println((a == b));
		int[][] v = new int[0][0];
	}
	
	public List<List<String>> partition(String s) {
        if (s == null) return new ArrayList<List<String>>();
        
        int len = s.length();
        boolean[][] psubstrs = new boolean[len][len];
        
        // traverse all substring mark all palindromes
        for (int c = 0; c < len; c++) {
            for (int r = 0; r <= c; r++) {
                if (s.charAt(c) == s.charAt(r) && (c <= r+2 || psubstrs[r+1][c-1])) {
                    psubstrs[r][c] = true;
                    
                } else {
                    psubstrs[r][c] = false;
                }
            }
        }
        
        // dp: S(k) = S(i) U A[i+1,k] if psubstrs[i+1, k]==true
        List<List<List<String>>> dp = new ArrayList<List<List<String>>>();
        for (int i = 0; i < len; i++) {
        	dp.add(new ArrayList<List<String>>());
            for (int j = i; j >= 0; j--) {
                if (psubstrs[j][i]) {
                    if (j == 0) {
                        List<String> list = new ArrayList<String>();
                        list.add(s.substring(0,i+1));
                        dp.get(i).add(list);
                    } else {
                        for (List<String> list : dp.get(j-1)) {
                        	list.add(s.substring(j,i+1));
                        }
                    }
                }
            }
        }
        return dp.get(len-1);
	}
	public int minCut(String s) {
if (s == null || s.length() == 0) return 0;
        
        int len = s.length();
        boolean[][] palindSubstrs = new boolean[len][len];
        
        // init
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                if (r >= c) palindSubstrs[r][c] = true;
                else palindSubstrs[r][c] = false;
            }
        }
        
        // find all palindrome substrings
        // S(i, j) = (A[i] == A[j]) && S(i+1, j-1)
        for (int c = 0; c < len; c++) {
            for (int r = c-1; r >= 0; r--) {
                palindSubstrs[r][c] = (s.charAt(r) == s.charAt(c)) && palindSubstrs[r+1][c-1];
            }
        }
        
        // dp2
        // C(i) = min{C(j) + 1} if s(j,i)==true
        int[] cut = new int[len];
        for (int i = 0; i < len; i++) cut[i] = i;
        
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (palindSubstrs[j][i]) {
                	System.out.println("j="+j+",i="+i);
                    cut[i] = (j >= 1) ? Math.min(cut[i], cut[j-1]+1) : 0;   
                }
            }
        }

        return cut[len-1];
    }
    
    private int connectedComponent(boolean[][] graph) {
        // adj table -> adj list
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for (int i = 0; i < graph.length; i++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int j = i+1; j < graph.length; j++) {
                if (graph[i][j]) list.add(j);
            }
            adj.add(list);
        }
        
        // find connected component
        boolean[] visited = new boolean[adj.size()];
        int rtn = 0;
        for (int i = 0; i < adj.size(); i++) {
        	if (!visited[i]) {
        		rtn++;
        		explore(adj, i, visited);
        	}
        }
        
        return (rtn-1);
    }
    
    private void explore(List<List<Integer>> adj, int i, boolean[] v) {
    	v[i] = true;
    	if (adj.get(i) == null || adj.get(i).isEmpty()) return;
    	
    	for (int neighbour: adj.get(i)) {
    		if (!v[neighbour]) explore(adj, neighbour, v);
    	}
    }
}
