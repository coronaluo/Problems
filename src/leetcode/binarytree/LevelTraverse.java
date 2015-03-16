package leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import leetcode.util.TreeNode;

public class LevelTraverse {
	public static void main(String []args) {
		TreeNode root = new TreeNode(-2);
		root.left = new TreeNode(4);
		root.right = new TreeNode(-3);
		root.left.left = new TreeNode(11);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
//		TreeNode tmp = root.left.left;
//		tmp.left = new TreeNode(7);
//		tmp.right = new TreeNode(2);
//		
//		tmp = root.right.right;
//		tmp.left = new TreeNode(5);
//		tmp.right = new TreeNode(1);
		
		new LevelTraverse().printTreeByLevel(root);
	}

class ListNode {
	TreeNode node;
	int level;
	public ListNode(TreeNode n, int l) {
		node = n;
		level = l;
	}
}

public void printTreeByLevel(TreeNode root) {
	Queue<ListNode> queue = new LinkedList<ListNode>();
	queue.offer(new ListNode(root, 0));

	int levelOfLastNode = -1;
	while (!queue.isEmpty()) {
		ListNode cur = queue.poll();
		if (levelOfLastNode == -1) {
			System.out.print("");
		} else if (levelOfLastNode == cur.level) {
			System.out.print(" ");
		} else {
			System.out.println();
		}
		System.out.print(cur.node.val);
		
		if (cur.node.left != null) queue.offer(new ListNode(cur.node.left, cur.level+1));
		if (cur.node.right != null) queue.offer(new ListNode(cur.node.right, cur.level+1));
		levelOfLastNode = cur.level;
	}
}
}
