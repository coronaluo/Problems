package leetcode.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ColumnWisePrintOut {
	
	public static void main(String[] args) {
    	TreeNode root = new TreeNode(8);
		root.left = new TreeNode(6);
		root.right = new TreeNode(10);
		root.left.left = new TreeNode(4);
		
		root.left.right = new TreeNode(7);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(12);
		
		TreeNode n = root.left.left;
		n.left = new TreeNode(3);
		n.right = new TreeNode(5);
		new ColumnWisePrintOut().traverse(root);
	}
	// print a tree top down (column wise)
	public void traverse(TreeNode root) {
	  TreeMap<Integer, List<Integer>> rst = new TreeMap<Integer, List<Integer>>();
	  if (root == null) return ;
	  helper(rst, root, 0);
	  for (Map.Entry entry : rst.entrySet()) {
	    for (int i : (List<Integer>)entry.getValue()) {
	      System.out.print(i+" ");
	    }
	    System.out.println();
	  }
	}

	public void helper(TreeMap<Integer, List<Integer>> rst, TreeNode root, int col) {
	  if (root == null) return ;
	  
	  if (!rst.containsKey(col)) rst.put(col, new ArrayList<Integer>());
	  rst.get(col).add(root.val); 
	  if (root.left != null) helper(rst, root.left, col-1);
	  if (root.right != null) helper(rst, root.right, col+1);
	}
}
