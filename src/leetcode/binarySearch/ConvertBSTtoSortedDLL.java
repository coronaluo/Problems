package leetcode.binarySearch;

public class ConvertBSTtoSortedDLL {
	public static void main(String[] args) {
    	TreeNode root = new TreeNode(8);
		root.left = new TreeNode(6);
		root.right = new TreeNode(10);
		root.left.left = new TreeNode(4);
		
		root.left.right = new TreeNode(7);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(12);
		
//		TreeNode n = root.left.left;
//		n.left = new TreeNode(3);
//		n.right = new TreeNode(5);
		displayDLL(new ConvertBSTtoSortedDLL().convertBSTtoSortedDLL(root));
	}
	
	public static void displayDLL(TreeNode head) {
		TreeNode p = head;
		while (p != null) {
			System.out.println(p.val);
			p = p.right;
		}
	}
	
	public TreeNode convertBSTtoSortedDLL (TreeNode root) {
		if (root == null) return null;
		TreeNode head = root;
		while (head.left != null) head = head.left;
		helper(root, null);
		return head;
	}
	
	// previous node refers to the last visited node during an inorder traverse
	private void helper(TreeNode node, TreeNode prev) {
		if (prev == null)System.out.println("pre = null");
		else System.out.println("pre = " + prev.val);
		if (node == null) return ;

		helper(node.left, prev);
		node.left = prev;
		if (prev != null) prev.right = node;
		prev = node;
		helper(node.right, prev);
	}
}
