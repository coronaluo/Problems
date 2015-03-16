package leetcode.binarySearch;

import leetcode.binarytree.LowestCommonAncestor;
import leetcode.util.ListNode;

public class ConvertSortedLinkedListToBST {
	// 3.Convert a sorted linkedlist of integers to a balanced binary search tree

	public static void main(String[] args) {
    	ListNode head = new ListNode(0);
    	ListNode p = head;
    	for (int i = 1; i < 10; i++) {
    		ListNode n = new ListNode(i);
    		p.next = n;
    		p = p.next;
    	}
    	new ConvertSortedLinkedListToBST().displayTree(new ConvertSortedLinkedListToBST().convert(head));
	}
	
	public void displayTree(TreeNode node) {
		if (node == null) return;
		displayTree(node.right);
		System.out.println(node.val);
		displayTree(node.left);
	}
	
	public TreeNode convert (ListNode list) {
		return helper(list, 0, getLen(list)-1);
	}

	private TreeNode helper(ListNode listNode, int start, int end) {
		if (listNode == null || end < start) return null;
		
		int k = 0;
		int len = end - start + 1;
		ListNode mid = listNode;
		while (k < len / 2) {
			mid = mid.next;
			k++;
		}

		TreeNode left = helper(listNode, start, start + k - 1);
		TreeNode root = new TreeNode(mid.val);
		TreeNode right = helper(mid.next, 0, len-k-2);
		root.left = left;
		root.right = right;
		return root;
	}

	private int getLen(ListNode head) {
		ListNode p = head;
		int counter = 0;
		while (p != null) {
			p = p.next;
			counter++;
		}
		return counter;
	}
}
