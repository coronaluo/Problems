package leetcode.BST;

import java.util.ArrayList;
import java.util.List;

public class RecoverBST {
	public static void main(String[] args) {
//		TreeNode root = new TreeNode(2);
//		root.right = new TreeNode(1);
////		root.right = new TreeNode(10);
////		root.left.left = new TreeNode(12);
////		
////		root.left.right = new TreeNode(7);
////		root.right.left = new TreeNode(9);
////		root.right.right = new TreeNode(4);
//		
//		traverseBST(root);
//		new RecoverBST().recoverTree(root);
//		System.out.println("results:");
//		traverseBST(root);
		
		List<TreeNode> roots = new ArrayList<TreeNode>();
		roots.add(null);
		roots.add(new TreeNode(1));
		for (TreeNode n : roots) {
			if (n == null)
				System.out.println("null element");
			else
				System.out.println(n.val);
		}
		
	}
	
	public static void traverseBST(TreeNode root) {
		if (root == null) {
			System.out.println("#");
			return;
		}
		traverseBST(root.left);
		System.out.println(root.val);
		traverseBST(root.right);
	}
	
	public static TreeNode prev = null, node1 = null, node2 = null;
    public void recoverTree(TreeNode root) {
        helper(root);
        if (node1 == null && node2 == null) throw new RuntimeException("illegal input");
        swap(node1, node2);
    }
    
    // swap elements
    private void swap(TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }
    
    // all inclusive
    private void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (prev != null && prev.val > root.val) {
            if (node1 == null) node1 = prev;
            node2 = root;
        }
        prev = root;
        helper(root.right);
    }
}
