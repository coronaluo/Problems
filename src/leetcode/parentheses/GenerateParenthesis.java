package leetcode.parentheses;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
	public static void main(String []args) {
		new GenerateParenthesis().generate(3);
	}
	
	public void generate(int n) {
		List<String> rtn = new ArrayList<String>();
		dfs(n, 0, 0, "", rtn);
		System.out.println(rtn);
	}
	
	public void dfs(int n, int left, int right, String cur, List<String> rtn) {
	    if (left == n && right == n) {
	    	rtn.add(cur);
	        return ;
	    }
	    
	    if (left < right) return;
	    
	    if (left < n)
	    	dfs(n, left+1, right, cur+'(', rtn);
	    
	    if (right < n)
	    	dfs(n, left, right+1, cur+')', rtn);
	}
}
