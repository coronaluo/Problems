package leetcode.binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import leetcode.util.TreeNode;

public class BSTIterator {

    private TreeNode next = null;
    private Stack<TreeNode> stack = null;
    
    static int factorialRemainder(int n) {
        // X = 1 or prime
        boolean[] composite = new boolean[n+1];
        composite[0] = true; // 0 is composite
        composite[1] = false; // 1 satisfied
        
        for (int i = 2; i <= n; i++) {
            if (composite[i]) continue;
            int factor = (i + i); 
            while (factor <= n) {
                composite[factor] = true;
                factor += i;
            }
        }
        int rtn = 0;
        for (int i = 0; i <= n; i++) {
            if (!composite[i]) rtn++;
        }
        return rtn;
    }
    
    private static boolean isAnagrams(List<String> words) {
    	if (words == null || words.size() == 0) throw new RuntimeException("empty input");
    	if (words.size() == 1) throw new RuntimeException("at least 2 words should be presented");
    	
    	HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
    	String first = words.get(0);
    	for (int i = 0; i < first.length(); i++) {
    		char c = first.charAt(i);
    		if (!hm.containsKey(c)) hm.put(c, 0);
    		int freq = hm.get(c);
    		hm.put(c, freq+1);
    	}
    	int len = first.length();
    	for (String word : words) {
    		System.out.println(word);
    		if (word.length() != len) return false;
    		HashMap<Character, Integer> hmt = new HashMap<Character, Integer>(hm);
    		for (int i = 0; i < word.length(); i++) {
    			char c = word.charAt(i);
    			if (!hmt.containsKey(c)) return false;
    			int freq = hmt.get(c);
    			System.out.println(c+", f="+freq);
    			if (freq == 1) hmt.remove(c);
    			else hmt.put(c, freq-1);
    		}
    	}
    	return true;
    }
    
    
    
    public static void main(String []args) {
    	
    	/* Enter your code here. Read input from STDIN. Print output to STDOUT */
    	Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        list.add("armmy");
        list.add("maryy");
//        while(sc.hasNextLine()) {
//        	list.add(sc.nextLine());
//        }
        System.out.println(isAnagrams(list));
        
//    	TreeNode root = new TreeNode(3);
//		root.left = new TreeNode(2);
//		root.right = new TreeNode(5);
//		root.left.left = new TreeNode(1);
//		root.right.left = new TreeNode(4);
//		
//    	BSTIterator i = new BSTIterator(null);
//    	while (i.hasNext()) {
//    		System.out.println(i.next());
//    	}
    }
    
    public BSTIterator(TreeNode root) {
        TreeNode p = root;
        stack = new Stack<TreeNode>();
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        setNextNode();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (next != null);
    }

    /** @return the next smallest number */
    public int next() {
        int rtn = next.val;
        setNextNode();
        return rtn;
    }
    
    private void setNextNode() {
        next = (stack == null || stack.isEmpty()) ? null : stack.pop();
        TreeNode p = next;
        if (p != null && p.right != null) {
            p = p.right;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }
    }
}
