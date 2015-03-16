package leetcode.array;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	public static void main(String[] args) {
		new WordLadder().test();
	}
	public void test () {
		Set<String> dict = new HashSet<String>();
		dict.add("a");
		dict.add("b");
		dict.add("c");
		
		System.out.println(ladderLength("a", "c", dict));
	}
	
	class Node {
        String val;
        int level;
        public Node(String v, int l) {
            val = v;
            level = l;
        }
    }
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null || start.length() != end.length()) throw new RuntimeException("illegal input");
        if (start.equals(end)) return 0;
        
        Queue<Node> que = new LinkedList<Node>();
        que.offer(new Node(start, 0));
        dict.remove(start);
        while (!que.isEmpty()) {
            Node cur = que.poll();
            
            for (int i = 0; i < cur.val.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == cur.val.charAt(i)) continue;
                    String replaced = replace(cur.val, i, c);
                    if (!dict.contains(replaced)) continue;
                    if (replaced.equals(end)) {
                    	System.out.println(cur.val);
                    	return (cur.level + 1);
                    }
                    
                    que.offer(new Node(replaced, cur.level+1));
                    dict.remove(replaced);
                }
            }
        }
        return -1;
    }
    
    private String replace(String s, int i, char c) {
        String rst = s.substring(0, i);
        rst += c;
        rst += s.substring(i+1);
        return rst;
    }
}
