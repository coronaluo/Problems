package leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

import leetcode.util.TreeNode;

public class PathSumII {
	
	public static void main(String []args) {
		TreeNode root = new TreeNode(-2);
//		root.left = new TreeNode(4);
		root.right = new TreeNode(-3);
//		root.left.left = new TreeNode(11);
//		root.right.left = new TreeNode(13);
//		root.right.right = new TreeNode(4);
//		TreeNode tmp = root.left.left;
//		tmp.left = new TreeNode(7);
//		tmp.right = new TreeNode(2);
//		
//		tmp = root.right.right;
//		tmp.left = new TreeNode(5);
//		tmp.right = new TreeNode(1);
		
		List<List<Integer>> lists = new PathSumII().pathSum(root, -5);
		for (List<Integer> list : lists) {
			for (int i : list) {
				System.out.print(i+",");
			}
			System.out.println();
		}
	}
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		helper(root, 0, new ArrayList<Integer>(), rst, sum);
		return rst;
    }
    
    private void helper(TreeNode root, int cursum, List<Integer> curPath, List<List<Integer>> rst, int sum) {
        if (root == null) return ;
        if (root.left == null && root.right == null) {
            if ((cursum + root.val) == sum) {
                curPath.add(root.val);
                rst.add(curPath);
            }
            return ;
        }
        
        if (root.left != null) {
        	List<Integer> updatedPath = new ArrayList<Integer>(curPath);
        	updatedPath.add(root.val);
        	helper(root.left, (cursum+root.val), updatedPath, rst, sum);
        }
        if (root.right != null) {
        	List<Integer> updatedPath = new ArrayList<Integer>(curPath);
        	updatedPath.add(root.val);
        	helper(root.right, (cursum+root.val), updatedPath, rst, sum);
        }
    }
}
