package leetcode.BST;

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
    
    public static void main(String []args) {
    	/* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        System.out.println(factorialRemainder(92143));
        
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
