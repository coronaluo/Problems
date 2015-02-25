package leetcode.dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	public static void main(String[] args) {
		String[] input = new String[]{"hot","dot","dog","lot","log"};
		Set<String> dict = new HashSet<String>();
		for (int i = 0; i < input.length; i++) dict.add(input[i]);
		System.out.println(new WordLadder().ladderLength("logg", "dot", dict));
	}
	
	public int ladderLength(String start, String end, Set<String> dict) {
		if (start.length() != end.length()) throw new RuntimeException("ilegal input");
        dict.add(start);
        dict.add(end);
        // neighbours
        HashMap<String, Set<String>> neighbours = new HashMap<String, Set<String>>();
        for (String word1 : dict) {
            if (!neighbours.containsKey(word1)) neighbours.put(word1, new HashSet<String>());
            for (String word2 : dict) {
                if (getWordDiff(word1, word2) == 1) {
                    neighbours.get(word1).add(word2);
                }
            }
        }
        
        HashMap<String, Integer> dis = new HashMap<String, Integer>();
        for (String word : dict) {
            dis.put(word, Integer.MAX_VALUE);
        }
        dis.put(start, 1);
        
        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        while(!queue.isEmpty()) {
            String cur = queue.remove();
            visited.add(cur);
            int curdis = dis.get(cur);
            for (String n : neighbours.get(cur)) {
                if (!visited.contains(n)) queue.add(n);
                if ((curdis+1) < dis.get(n)) {
                    dis.put(n, curdis+1);
                }
            }
        }
        
        return (dis.get(end) == Integer.MAX_VALUE) ? 0 : dis.get(end);
    }
    
    private int getWordDiff(String w1, String w2) {
        int diff = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) diff++;
        }
        return diff;
    }
}
