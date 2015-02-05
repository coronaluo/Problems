package leetcode.parentheses;

import java.util.Stack;

public class LongestValidParentheses {
	public static void main(String[] args) {
		String a = "))))))(";
		System.out.println(new LongestValidParentheses().longestValidParentheses(a));
	}
	
	public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        
        Stack<Pair> stack = new Stack<Pair>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '(' && c != ')') throw new RuntimeException("input has to be parentheses");
            if (!stack.isEmpty() && stack.peek().c == '(' && c == ')') {
            		stack.pop();
            } else {
                stack.push(new Pair(c, i));
            }
        }
        
        int maxlen = 0, cur = s.length()-1;
        while (!stack.isEmpty() && cur >= 0) {
            Pair p = stack.pop();
            int dis = cur - p.idx;
            maxlen = Math.max(dis, maxlen);
            cur = p.idx-1;
        }
        if (cur != 0) maxlen = Math.max(cur-0+1, maxlen);
        return maxlen;
    }
    
    private class Pair {
        char c;
        int idx;
        public Pair(char c, int i) {
            this.c = c;
            idx = i;
        }
    }
}
