package leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.util.TreeNode;

class StackEntry {
    TreeNode node;
    boolean visited;
    StackEntry(TreeNode n, boolean v) {
        node = n;
        visited = v;
    }
}

public class Postorder {
	public static void main(String []args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.left = new TreeNode(5);
		List<Integer> order = postorderTraversal(root);
		System.out.println(order.toString());
	}
	
	public static List<Integer> postorderTraversal(TreeNode root) {
		if (root == null) return new ArrayList<Integer>();
        List<Integer> rtn = new ArrayList<Integer>();
        
        // recursively
        // List<Integer> rtn = new ArrayList<Integer>();
        // rtn.add(root.val);
            
        
        // if (root.left == null && root.right == null) {
        //     return rtn;
        // }
        
        // List<Integer> left = postorderTraversal(root.left);
        // List<Integer> right = postorderTraversal(root.right);
        // if (left == null) {
        // 	right.addAll(rtn);
        // 	return right;
        // } else if (right == null) {
        // 	left.addAll(rtn);
        // 	return left;
        // } else {
        // 	left.addAll(right);
        // 	left.addAll(rtn);
        // 	return left;
        // }
        
        // iteratively
        Stack<StackEntry> stack = new Stack<StackEntry>();
        stack.push(new StackEntry(root, false));
        
        while (!stack.isEmpty()) {
            StackEntry curEntry = stack.pop();
            TreeNode curNode = curEntry.node;
            boolean isCurNodeVisited = curEntry.visited;
            
            if (curNode == null) continue;
            
            if (isCurNodeVisited) {
                rtn.add(curNode.val);  
            } else {
                stack.push(new StackEntry(curNode, true));
                stack.push(new StackEntry(curNode.right, false));
                stack.push(new StackEntry(curNode.left, false));
            }
        }
        
        return rtn;
    }
}
