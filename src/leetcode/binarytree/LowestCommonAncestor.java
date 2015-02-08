package leetcode.binarytree;

import java.util.HashMap;

import leetcode.BST.ColumnWisePrintOut;
import leetcode.BST.TreeNode;

public class LowestCommonAncestor {
	public static void main(String[] args) {
    	TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		
		TreeNode A = root.right;
		TreeNode B = root.right.right;
				
		System.out.println(new LowestCommonAncestor().lowestCommonAncestor(root, A, B).val);
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        // assume no duplicates
        if (root == null) return null;
        if ((A == null) ^ (B == null)) return (A == null) ? B : A;
        if ((A == null) && (B == null)) throw new RuntimeException("given nodes are all nulls");
        
        return helper(root, A, B);
    }
    
    private TreeNode helper(TreeNode root, TreeNode A, TreeNode B) {
       if (root == null) return null;
       
       if (root.val == A.val || root.val == B.val) return root;
       
       TreeNode leftLCA = helper(root.left, A, B);
       TreeNode rightLCA = helper(root.right, A, B);
       
       if (leftLCA != null && rightLCA != null) return root;
       return (leftLCA == null) ? rightLCA : leftLCA; 
    }
}
